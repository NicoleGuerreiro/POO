// importa todas as classes de diálogo e utilitários do Swing
import javax.swing.*;

/*
  Main.java
  - Pergunta ao usuário qual modo (trecho do professor incluído exatamente)
  - Pergunta quantos discos (mínimo 4)
  - Cria a janela principal do jogo
*/
public class Main {
    // ponto de entrada do programa
    public static void main(String[] args) {
        // pede para executar o código de GUI na thread correta do Swing (EDT)
        SwingUtilities.invokeLater(() -> {
            Object[] opcoes = {"Modo Jogador", "Modo Automático"};
            int op = JOptionPane.showOptionDialog(
                    null,
                    "Escolha o modo de execução:",
                    "Modo de execução",
                    0,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (op == 0){ /* modo jogador */ }
            else { /* modo automático */ }

            // interpreta retorno: se op == 0 é jogador, senão automático
            boolean modoAutomatico = (op != 0);

            // pergunta ao usuário quantos discos ele quer (mostra 4 por padrão)
            String entrada = JOptionPane.showInputDialog(null,
                    "Quantos discos? (mínimo 4)", "4");

            // variável que guardará o número de discos
            int n;
            try {
                // tenta converter o texto digitado para inteiro
                n = Integer.parseInt(entrada);
                // se o número for menor que 4, força para 4 (requisito da obrigátorio)
                if (n < 4) n = 4;
            } catch (Exception e) {
                // se ocorrer erro (cancelou ou digitou texto), usa 4 por padrão
                n = 4;
            }

            // cria a janela do jogo com as opções escolhidas
            JanelaHanoi janela = new JanelaHanoi(modoAutomatico, n);
            // mostra a janela
            janela.setVisible(true);
        });
    }
}
