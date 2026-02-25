import javax.swing.*;      // JFrame, JOptionPane, Timer, SwingUtilities.
import java.awt.*;         // Dimension.
import java.awt.event.*;   // KeyListener, ActionListener, KeyEvent, ActionEvent.

/*
 * Jogo.java
 * Classe principal: cria a janela, inicia o Timer e trata o teclado.
 * KeyListener é registrado no painel (painel tem foco garantido).
 */
public class Jogo extends JFrame implements KeyListener, ActionListener { // JFrame é a janela, KeyListener escuta o teclado, ActionListener escuta o Timer.

    // painel que desenha o jogo e guarda comida/obstáculos
    private PainelCobra painel; // Referência ao painel onde o jogo acontece.

    // timer do jogo (passo automático)
    private Timer timer; // Responsável por gerar o "tick" de tempo do jogo.

    // flag de fim de jogo
    private boolean gameOver = false; // Sinaliza quando o jogo deve parar.

    // velocidade em ms
    private final int VELOCIDADE_MS = 150; // O tempo (em milissegundos) entre cada passo/tick do jogo.

    // construtor: configura janela, painel e timer
    public Jogo() {
        // configurações do JFrame
        setTitle("Jogo Snake");            // Título da janela.
        setSize(800, 600);                 // Tamanho da janela (coincide com o tamanho do painel).
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Define que fechar a janela encerra o programa.
        setResizable(false);               // Impede que o usuário redimensione a janela.
        setLocationRelativeTo(null);       // Centraliza a janela na tela.

        // cria painel e adiciona ao frame
        painel = new PainelCobra(); // Cria o PainelCobra (o conteúdo do jogo).
        add(painel);                // Adiciona o painel à janela.

        // registra KeyListener no painel (painel receberá as teclas)
        painel.addKeyListener(this); // Define que esta classe (Jogo) vai escutar os eventos de teclado do PainelCobra.

        // configura o timer que chama actionPerformed
        timer = new Timer(VELOCIDADE_MS, this); // Cria o Timer: a cada 150ms, chama o método actionPerformed desta classe.

        // mostra a janela
        setVisible(true); // Torna a janela visível.

        // garante foco no painel após a janela se tornar visível e então inicia o timer
        SwingUtilities.invokeLater(new Runnable() { // Código que deve rodar APÓS a interface gráfica ser mostrada.
            @Override
            public void run() {
                painel.requestFocusInWindow(); // Pede para o painel ganhar o foco (para receber as teclas).
                timer.start();                 // Inicia o Timer (inicia o jogo).
            }
        });
    }

    // método chamado a cada tick do timer
    @Override
    public void actionPerformed(ActionEvent e) { // Chamado a cada VELOCIDADE_MS (150ms).
        if (!gameOver) { // Se o jogo não acabou:
            // 1) mover a cobra
            painel.cobra.andar(painel.largura, painel.altura); // Move a cobra para a próxima posição.

            // 2) pega posição da cabeça
            Point cabeca = new Point(painel.cobra.pos_x, painel.cobra.pos_y); // Posição atual da cabeça (após mover).

            // 3) verificar se comeu alguma comida
            for (int i = 0; i < painel.comida.size(); i++) { // Loop para verificar CADA item de comida.
                Point c = painel.comida.get(i);
                if (cabeca.equals(c)) { // Se a posição da cabeça é igual à posição da comida 'c'.
                    // comeu: aumenta pontos e faz crescer a cobra
                    painel.pontos++;
                    painel.cobra.crescer();

                    // tenta reposicionar a comida (gera nova posição livre)
                    Point nova = painel.gerarPosicao(); // Pede uma nova posição livre para a comida.
                    if (nova != null) {
                        // troca a comida atual pela nova posição
                        painel.comida.set(i, nova); // Substitui a comida comida pela nova posição.
                    } else {
                        // se não encontrou posição livre, remove a comida
                        painel.comida.remove(i); // Se não achou lugar, remove a comida (o número total diminui).
                    }
                    break; // Sai do loop 'for' de comida, pois só pode comer uma por tick.
                }
            }

            // 4) verificar colisões com obstáculos
            for (Point o : painel.obstaculos) { // Percorre todos os obstáculos.
                if (cabeca.equals(o)) {         // Se a cabeça colidiu com o obstáculo 'o'.
                    gameOver = true;            // Fim de jogo.
                    break;
                }
            }

            // 5) verificar colisão com o próprio corpo
            if (!gameOver && painel.cobra.bateuNoProprioCorpo()) { // Se não houve colisão antes, checa o próprio corpo.
                gameOver = true;
            }

            // 6) redesenha a tela
            painel.repaint(); // Pede ao PainelCobra para chamar o método paintComponent() e redesenhar tudo.
        } else {
            // se game over, para o timer e informa o jogador
            timer.stop(); // Para o loop principal do jogo.
            JOptionPane.showMessageDialog(this, "GAME OVER! Pontos: " + painel.pontos); // Exibe a mensagem de fim de jogo.
        }
    }

    // ---------------- KeyListener ----------------
    // Métodos que tratam a entrada do teclado

    @Override
    public void keyPressed(KeyEvent e) { // Chamado quando uma tecla é pressionada.
        int k = e.getKeyCode(); // Obtém o código da tecla pressionada

        if (k == KeyEvent.VK_UP && painel.cobra.orientacao != 2) {   // Se pressionou Cima E a direção atual não é Baixo (2).
            painel.cobra.orientacao = 0;                              // Define a direção para Cima (0).
        } else if (k == KeyEvent.VK_RIGHT && painel.cobra.orientacao != 3) { // Se pressionou Direita E a direção atual não é Esquerda (3).
            painel.cobra.orientacao = 1;                              // Define a direção para Direita (1).
        } else if (k == KeyEvent.VK_DOWN && painel.cobra.orientacao != 0) { // Se pressionou Baixo E a direção atual não é Cima (0).
            painel.cobra.orientacao = 2;
        } else if (k == KeyEvent.VK_LEFT && painel.cobra.orientacao != 1) { // Se pressionou Esquerda E a direção atual não é Direita (1).
            painel.cobra.orientacao = 3;
        }

        // reforça o foco no painel
        painel.requestFocusInWindow(); // Garante que o painel continue escutando o teclado.
    }

    @Override public void keyReleased(KeyEvent e) {} // Não usado.
    @Override public void keyTyped(KeyEvent e) {}    // Não usado.
}