package avaliacaolp2;

import telas.TelaAluno;

public class Principal {
    public static void main(String[] args) {
        // Exemplo de uso da TelaAluno
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAluno().setVisible(true);
            }
        });
    }
}
