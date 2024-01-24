package modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluno {
    private String cpf;
    private String nome;
    private String dataNasc;
    private float peso;
    private float altura;
    private String dataAtual;

    public Aluno(String cpf, String nome, String dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
    }
    
    public Aluno(){}

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getDataAtual() {

        return dataAtual;
    }
    
    public void setDataAtual(String dataAtual) {
        
        this.dataAtual = dataAtual;
    }
    
    public void setAtualData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.dataAtual = dateFormat.format(new Date());
    }
}
