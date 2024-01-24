package telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.HistoricoPeso;

public class TelaHistorico extends JFrame {
    private DefaultTableModel modelo;
    private String cpf;
    private String nome;

    public TelaHistorico(String cpf, String nome) {
        super("Histórico de peso para " + nome);
        this.cpf = cpf;
        this.nome = nome;

        setSize(500, 500);

        JLabel pesoLabel = new JLabel("Peso");
        pesoLabel.setPreferredSize(new Dimension(100, 50));

        String[] colunas = {"Data", "Peso"};
        Object[][] data = null;
        modelo = new DefaultTableModel(data, colunas);
        JTable tabela = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JButton imcButton = new JButton("Calcular IMC");
        imcButton.setPreferredSize(new Dimension(200, 50));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Defina o espaço entre a tabela e a parte superior da célula
        gbc.insets.top = 20;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(scrollPane, gbc);

        // Defina a posição vertical da tabela ajustando o valor de gbc.gridy
        gbc.gridy = 1;
        panel.add(imcButton, gbc);

        panel.add(pesoLabel, gbc);

        imcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calcularIMC();
                } catch (IOException ex) {
                    Logger.getLogger(TelaHistorico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        add(panel);

        setVisible(true);
    }

    public void adicionarRegistroTabela(String data, String peso) {
        modelo.addRow(new Object[]{data, peso});
    }

    private void calcularIMC() throws IOException {
        int linhaSelecionada = modelo.getRowCount() - 1;

        if (linhaSelecionada >= 0) {
            String data = (String) modelo.getValueAt(linhaSelecionada, 0);
            String peso = (String) modelo.getValueAt(linhaSelecionada, 1);

            double pesoDouble = Double.parseDouble(peso);
            double altura = 1.75; // Altere para a altura desejada

            String resultadoIMC = HistoricoPeso.calcIMC(pesoDouble, altura);
            salvarArquivo(nome, data, peso, resultadoIMC);
        }
    }

private String salvarArquivo(String nome, String data, String peso, String resultadoIMC) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String dataAtual = dateFormat.format(new Date());

        String nomeArquivo = "historico_imc_" + nome + "_" + dataAtual + ".txt";
        try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
            writer.write("Nome: " + nome + "\n");
            writer.write("Data do Cálculo: " + dataAtual + "\n");
            writer.write("Data do Peso Registrado: " + data + "\n");
            writer.write("Peso: " + peso + " kg\n");
            writer.write("Resultado IMC: " + resultadoIMC + "\n\n");
        }

        String caminhoArquivo = new java.io.File(nomeArquivo).getCanonicalPath();
        JOptionPane.showMessageDialog(null, "IMC calculado e salvo no arquivo:\n" + caminhoArquivo);
        return caminhoArquivo;
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo: " + e.getMessage());
        return null;
    }
}




}
    

