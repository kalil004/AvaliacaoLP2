package telas;


import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import modelos.Aluno;
import DAO.DAO;

public class TelaAluno extends JFrame {

    private final DAO dao;
    private TelaHistorico telaHistorico;

    public TelaAluno() {
        dao = new DAO();

        setSize(500, 500);
        setLayout(null);

        JLabel textoCPF = new JLabel("CPF:");
        textoCPF.setBounds(230, 25, 100, 100);

        JTextField cpf = new JTextField();
        cpf.setBounds(150, 85, 200, 30);

        JLabel textoNome = new JLabel("Nome:");
        textoNome.setBounds(225, 75, 100, 100);

        JTextField nome = new JTextField();
        nome.setBounds(150, 135, 200, 30);

        JLabel textoDataNasc = new JLabel("Data de");
        JLabel textoDataNasc2 = new JLabel("Nascimento:");
        JLabel textoDataNasc3 = new JLabel("(yyyy-mm-dd)");
        textoDataNasc.setBounds(150, 125, 100, 100);
        textoDataNasc2.setBounds(200, 125, 100, 100);
        textoDataNasc3.setBounds(276, 125, 100, 100);

        MaskFormatter maskFormatter;
        try {
            maskFormatter = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            throw new RuntimeException("Erro ao criar a máscara para a data", ex);
        }
        JFormattedTextField datanasc = new JFormattedTextField(/*maskFormatter*/);
        datanasc.setBounds(150, 185, 200, 30);

        JLabel textoPeso = new JLabel("Peso:");
        textoPeso.setBounds(230, 175, 100, 100);

        JTextField peso = new JTextField();
        peso.setBounds(150, 235, 200, 30);

        JLabel textoAltura = new JLabel("Altura:");
        textoAltura.setBounds(223, 225, 100, 100);

        JTextField altura = new JTextField();
        altura.setBounds(150, 285, 200, 30);
//----------------------------------------------------------------------------//
        JButton registrar = new JButton("Registrar");
        registrar.setBounds(25, 330, 200, 50);
        
        JButton removerBtn = new JButton("Remover registro");
        removerBtn.setBounds(25, 400, 200, 50);
        
        JButton consultarBtn = new JButton("Consultar");
        consultarBtn.setBounds(255, 400, 200, 50);
        
        JButton atualizarBtn = new JButton("Atualizar");
        atualizarBtn.setBounds(255, 330, 200, 50);
//----------------------------------------------------------------------------//
    //REGISTRAR
registrar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String cpfText = cpf.getText();
        String nomeText = nome.getText();
        String dataNascText = datanasc.getText();
        try {
            float pesoValue = Float.valueOf(peso.getText().replace(",", "."));
            float alturaValue = Float.valueOf(altura.getText().replace(",", "."));

            if (cpfText.isEmpty() || nomeText.isEmpty() || dataNascText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios.");
            } else {
                Aluno aluno = new Aluno(cpfText, nomeText, dataNascText);
                aluno.setPeso(pesoValue);
                aluno.setAltura(alturaValue);
                
                Date dataAtual = new Date();
                aluno.setDataAtual(new SimpleDateFormat("yyyy-MM-dd").format(dataAtual));

                dao.addAluno(aluno);

                JOptionPane.showMessageDialog(null, "Registrado com sucesso");

                if (telaHistorico == null) {
                            telaHistorico = new TelaHistorico(cpfText, nomeText);
                            telaHistorico.setVisible(true);
                        }

                        telaHistorico.adicionarRegistroTabela(new SimpleDateFormat("yyyy-MM-dd").format(dataAtual), peso.getText());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Os campos de peso e altura devem conter números válidos.");
                }
            }
        });
    
        //REMOVER
        removerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                boolean campoVazio = cpf.getText().isEmpty();
                if(campoVazio) {JOptionPane.showMessageDialog(null, "O campo de cpf não pode estar vazio");}
                
                else {var dao = new DAO(); 
                      dao.removeAluno(cpf.getText());
                      JOptionPane.showMessageDialog(null, "Excluído com sucesso");}
            }
        });
        
        //ATUALIZAR
        atualizarBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean campoVazio = cpf.getText().isEmpty();
                
                
                if(campoVazio){JOptionPane.showMessageDialog(null, "O campo de cpf não pode estar vazio");}
                else{
                    var dao = new DAO();
                    
        try {
            float pesoValue = Float.valueOf(peso.getText().replace(",", "."));
            float alturaValue = Float.valueOf(altura.getText().replace(",", "."));

            Aluno aluno = new Aluno(cpf.getText(), nome.getText(), datanasc.getText());
            aluno.setPeso(pesoValue);
            aluno.setAltura(alturaValue);

            dao.updateAluno(aluno);

                JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Os campos de peso e altura devem conter números válidos.");
            }
        }
    }
});
        
       consultarBtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaBancoAlunos().setVisible(true);
        });
    }
});

        add(textoCPF);
        add(cpf);
        add(textoNome);
        add(nome);
        add(textoDataNasc);
        add(textoDataNasc2);
        add(textoDataNasc3);
        add(datanasc);
        add(textoPeso);
        add(peso);
        add(textoAltura);
        add(altura);
        add(registrar);
        add(removerBtn);
        add(atualizarBtn);
        add(consultarBtn);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAluno().setVisible(true);
            }
        });
    }
}
