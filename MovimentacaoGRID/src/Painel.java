import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Painel extends JPanel implements KeyListener {

    private final int passo = 20; // movimento em pixels
    private Image fundo, coelho, cenoura, pedra;

    // posição inicial do personagem
    private int personagemX = 200;
    private int personagemY = 180;

    private int pontos = 0; // contador de pontos

    private int recorde = 0; // declara a variável que guarda o maior recorde em memória

    // listas com posições de objetos
    private final ArrayList<Point> cenouras = new ArrayList<>();
    private final ArrayList<Point> pedras = new ArrayList<>();
    private final Random random = new Random();

    public Painel() {
        try {
            fundo = new ImageIcon(getClass().getResource("/img/floresta1.jpeg")).getImage();
            coelho = new ImageIcon(getClass().getResource("/img/coelho.jpg")).getImage();
            cenoura = new ImageIcon(getClass().getResource("/img/cenoura.jpeg")).getImage();
            pedra = new ImageIcon(getClass().getResource("/img/pedra.jpeg")).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagens. Verifique /src/img/ e as extensões corretas (.jpg/.png).");
        }

        // ========== carregar recorde do arquivo ==========
        carregarRecorde(); // lê o recorde salvo em "recorde.txt" e carregar o valor para "recorde"

        gerarCenouras(35); // cria cenouras iniciais
        gerarPedras(5); // cria pedras iniciais

        setFocusable(true);
        addKeyListener(this);
    }

    // ======= Método pra carregar recorde do arquivo =======
    // Lê recorde salvo em "recorde.txt" (conteúdo é um único número inteiro)
    private void carregarRecorde() {
        try { // tenta abrir o arquivo
            java.io.File arquivo = new java.io.File("recorde.txt"); // referencia o arquivo na pasta do projeto

            if (arquivo.exists()) { // se o arquivo existe
                java.util.Scanner leitor = new java.util.Scanner(arquivo); // scanner para ler o arquivo
                if (leitor.hasNextInt()) { // se houver um inteiro no arquivo
                    recorde = leitor.nextInt(); // lê o valor e armazena em "recorde"
                }
                leitor.close(); // fecha o scanner
            }
        } catch (Exception e) { // se der erro, exibe mensagem
            System.out.println("Erro ao carregar recorde.");
        }
    }

    // ======= Método pra salvar recorde no arquivo =======
    // Sobrescreve "recorde.txt" com o valor atual do recorde
    private void salvarRecorde() { // salva o valor atual de "recorde" no arquivo
        try { // tenta abrir o arquivo para escrita
            java.io.FileWriter fw = new java.io.FileWriter("recorde.txt"); // cria FileWriter para sobrescrever o arquivo
            fw.write("" + recorde); // escreve o valor do recorde como string
            fw.close(); // fecha o FileWriter
        } catch (Exception e) { // se der erro, exibe mensagem
            System.out.println("Erro ao salvar recorde.");
        }
    }

    private void gerarCenouras(int quantidade) {
        // repete até gerar a quantidade desejada
        for (int i = 0; i < quantidade; i++) {
            // gera coordenadas aleátorias no grid
            int x = random.nextInt(30) * passo; // gera um número entre 0 e 29, (para o eixo X)
            int y = random.nextInt(20) * passo; // gera um número entre 0 e 19, (para o eixo Y)

            boolean invalido = (x == personagemX && y == personagemY);
            for (Point p : pedras)
                if (p.x == x && p.y == y) invalido = true;

            if (invalido) {
                i--; continue;
            }

            cenouras.add(new Point(x, y));
        }
    }

    private void gerarPedras(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            int x = random.nextInt(30) * passo;
            int y = random.nextInt(20) * passo;

            if (x == personagemX && y == personagemY) {
                i--; continue;
            }

            pedras.add(new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fundo != null) {
            g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.GREEN.darker());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        for (Point p : pedras) {
            if (pedra != null)
                g.drawImage(pedra, p.x, p.y, passo, passo, this);
            else {
                g.setColor(Color.GRAY);
                g.fillRect(p.x, p.y, passo, passo);
            }
        }

        for (Point c : cenouras) {
            if (cenoura != null)
                g.drawImage(cenoura, c.x, c.y, passo, passo, this);
            else {
                g.setColor(Color.ORANGE);
                g.fillRect(c.x + 3, c.y + 3, passo - 6, passo - 6);
            }
        }

        if (coelho != null)
            g.drawImage(coelho, personagemX, personagemY, passo, passo, this);
        else {
            g.setColor(Color.WHITE);
            g.fillRect(personagemX, personagemY, passo, passo);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Pontuação: " + pontos, 10, 20);

        g.drawString("Recorde: " + recorde, 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // ======== Guardar posição antiga para impedir passar pedra após teleporte ========
        int antigoX = personagemX;
        int antigoY = personagemY;

        int novoX = personagemX;
        int novoY = personagemY;

        int codigo = e.getKeyCode();
        if (codigo == KeyEvent.VK_LEFT) {
            novoX -= passo;
        } else if (codigo == KeyEvent.VK_RIGHT) {
            novoX += passo;
        } else if (codigo == KeyEvent.VK_UP) {
            novoY -= passo;
        } else if (codigo == KeyEvent.VK_DOWN) {
            novoY += passo;
        }

        // ---------- teletransporte nas bordas ----------
        if (novoX < 0) {
            novoX = getWidth() - passo;
        } else if (novoX >= getWidth()) {
            novoX = 0;
        }
        if (novoY < 0) {
            novoY = getHeight() - passo;
        } else if (novoY >= getHeight()) {
            novoY = 0;
        }

        // Agora verificamos as pedras DEPOIS do teleporte,
        // senão o coelho atravessa as pedras ao passar a borda.
        boolean bateuEmPedra = false;
        for (Point p : pedras) {
            if (p.x == novoX && p.y == novoY) {
                bateuEmPedra = true;
            }
        }

        // se bateu na pedra, não move (volta pro antigoX/Y)
        if (!bateuEmPedra) {
            personagemX = novoX;
            personagemY = novoY;
        }

        // ========= checar cenoura depois do teleporte ==============
        ArrayList<Point> removidas = new ArrayList<>();
        for (Point c : cenouras) {
            if (c.x == personagemX && c.y == personagemY) {
                pontos++;
                removidas.add(c);

                if (pontos > recorde) { // se a pontuação atual for maior que o recorde
                    recorde = pontos; // atualiza o recorde
                    salvarRecorde(); // salva o novo recorde no arquivo
                }
                gerarCenouras(1); // gera uma nova cenoura
                break; // só pode pegar uma cenoura por movimento
            }
        }
        cenouras.removeAll(removidas);
        repaint();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
