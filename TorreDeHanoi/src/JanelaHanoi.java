// import simples do Swing e AWT para layout
import javax.swing.*;
import java.awt.*;

/*
  JanelaHanoi.java
  - Classe que cria a janela (JFrame) principal e adiciona o painel onde o jogo será desenhado.
*/
public class JanelaHanoi extends JFrame {
    // painel que desenha as torres e discos
    private PainelHanoi painel;

    // construtor recebe se é modo automático e quantos discos
    public JanelaHanoi(boolean modoAutomatico, int numDiscos) {
        // define o título da janela
        setTitle("Torre de Hanói - Prática");
        // fecha a aplicação quando a janela for fechada
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // tamanho inicial da janela
        setSize(800, 600);
        // centraliza a janela na tela
        setLocationRelativeTo(null);

        // cria a lógica do jogo (estado)
        JogoHanoi jogo = new JogoHanoi(numDiscos, modoAutomatico);
        // cria o painel que desenha o jogo e recebe cliques
        painel = new PainelHanoi(jogo);
        // adiciona o painel ao centro da janela
        add(painel, BorderLayout.CENTER);

        // se for modo automático, inicia a resolução automaticamente
        if (modoAutomatico) {
            jogo.iniciarResolucaoAutomatica(painel);
        }
    }
}
