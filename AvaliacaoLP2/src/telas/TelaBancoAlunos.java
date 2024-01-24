/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

import DAO.DAO;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Aluno;

public class TelaBancoAlunos extends JFrame {

    public TelaBancoAlunos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Consultar Alunos");

        JPanel panel = new JPanel();
        DefaultTableModel model = new DefaultTableModel();

        JTable tabela = new JTable(model);
        tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));

        model.addColumn("CPF");
        model.addColumn("Nome");
        model.addColumn("Data de Nascimento");
        model.addColumn("Peso");
        model.addColumn("Altura");

        try {
            ArrayList<Aluno> lista = DAO.consultaAluno();

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        for (Aluno aluno : lista) {
                Object[] data = {
                    aluno.getCpf(),
                    aluno.getNome(),
                    aluno.getDataNasc(),
                    decimalFormat.format(aluno.getPeso()),
                    decimalFormat.format(aluno.getAltura())
                };
                model.addRow(data);
            }
        } catch (SQLException ex) {
            // Trate a exceção de acordo com a sua lógica
            ex.printStackTrace();
        }

        panel.add(new JScrollPane(tabela));
        add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaBancoAlunos().setVisible(true);
        });
    }
}
    