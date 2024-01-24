/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

import DAO.DAO;
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

public class TelaRemoverAluno extends JFrame {
    
    public TelaRemoverAluno(){
        DAO dao = new DAO();
        
        setSize(500, 500);
        
        setLayout(null);
        
        JLabel textoCPF = new JLabel("CPF do aluno a ser removido:");
        textoCPF.setBounds(200, 25, 100, 100);
        
        JTextField cpf = new JTextField();
        cpf.setBounds(150, 85, 200, 30);
        
        JButton remover = new JButton();
        remover.setBounds(150, 100, 200, 30);
        
        remover.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String cpfText = cpf.getText();
                
                if(cpfText.isEmpty()){JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.");}
                else{dao.removeAluno(cpfText);JOptionPane.showMessageDialog(null, "O campo não pode estar vazio.");}
            }
        });
        
        add(textoCPF);
        add(cpf);
        add(remover);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRemoverAluno().setVisible(true);
            }
        });
    }
    
}
