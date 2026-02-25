import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
            // Cria a janela principal
            JFrame frame = new JFrame("Movimentação em Grid - Coelhinho na Floresta");

            // Cria o Painel (onde a lógica do jogo acontece)
            Painel painelDoJogo = new Painel();
            frame.add(painelDoJogo);

            // Configurações da janela
            frame.setSize(640, 480); // Tamanho da janela
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
            frame.setLocationRelativeTo(null); // Centraliza a janela

            // Torna a janela visível
            frame.setVisible(true);

            // Solicita o foco para o Painel para que o KeyListener funcione
            painelDoJogo.requestFocusInWindow();
    }
}