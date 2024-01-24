package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import Conexao.Conector;
import java.util.ArrayList;
import modelos.Aluno;
import modelos.HistoricoPeso;

public class DAO {
    private static Connection conexao;


    //C
    public void addAluno(Aluno aluno) /*throws SQLException*/ {
        String sql = "insert into HistoricoPeso(cpf, nome, datanasc, peso, altura) values(?, ?, ?, ?, ?)";

        try (Connection conexao = new Conector().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNasc());
            stmt.setFloat(4, aluno.getPeso());
            stmt.setFloat(5, aluno.getAltura());

            stmt.execute();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    //D
    public void removeAluno(String cpf) /*throws SQLException*/ {
        String sql = "delete from HistoricoPeso where cpf = ?";

        try (Connection conexao = new Conector().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            stmt.execute();
        } 
        catch (SQLException u) {throw new RuntimeException(u);}
    }
    //U
    public void updateAluno(Aluno aluno) /*throws SQLException*/ {
        String sql = "update HistoricoPeso set cpf = ?, nome = ?, datanasc = ?, peso = ?, altura = ? where cpf = ?";
        
        try (Connection conexao = new Conector().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNasc());
            stmt.setFloat(4, aluno.getPeso());
            stmt.setFloat(5, aluno.getAltura());
            stmt.setString(6, aluno.getCpf());

            stmt.execute();
            stmt.close();
        }
        catch(SQLException u){throw new RuntimeException(u);}
    }
    //R
    public static ArrayList<Aluno> consultaAluno() throws SQLException {
    ArrayList<Aluno> listaAlunos = new ArrayList<>();
    Connection conexao = new Conector().getConnection();

    if (conexao != null) {
        ResultSet resultado = null;
        String sql = "select * from HistoricoPeso";  

        try (PreparedStatement consulta = conexao.prepareStatement(sql)) {
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                Aluno aluno = new Aluno();

                aluno.setCpf(resultado.getString("cpf"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setDataNasc(resultado.getString("datanasc"));
                aluno.setPeso(resultado.getFloat("peso"));
                aluno.setAltura(resultado.getFloat("altura"));

                listaAlunos.add(aluno);
            }
        } finally {
            if (resultado != null) {
                resultado.close();
            }
        }
    }

    return listaAlunos;
}

    
    public String buscarCpf(String cpf) throws SQLException {
    String sql = "select altura, nome from Aluno where cpf = ?";
    try (Connection conexao = new Conector().getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            String altura = resultSet.getString("altura");
            String nome = resultSet.getString("nome");

            return altura + " " + nome;
        } else {
            return "não foi encontrado.";
        }
    }
    }
}


    




/*
resultado = consulta.executeQuery();
            
            while(resultado.next()){
                HistoricoPeso hp = new HistoricoPeso();
                
                hp.setData(resultado.getString("hist_data"));
                hp.setPeso(resultado.getDouble("hist_peso"));
                hp.setCpf(resultado.getString("hist_cpf"));
                
                listaAlunos.add(hp);
*/