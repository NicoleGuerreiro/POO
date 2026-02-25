import javax.swing.*;      // Importa a biblioteca Swing para componentes gráficos (como JPanel, JFrame).
import java.awt.*;         // Importa a biblioteca AWT para gráficos (cores, fontes, Point).
import java.util.ArrayList; // Importa ArrayList para as listas dinâmicas (cobra, comida, obstáculos).
import java.util.Random;   // Importa Random para gerar posições aleatórias de comida e obstáculos.

/*
 * PainelCobra.java
 * JPanel simplificado que guarda comida, obstáculos e desenha tudo.
 * setFocusable + setFocusTraversalKeysEnabled(false) garante que as setas cheguem.
 */
public class PainelCobra extends JPanel { // PainelCobra herda de JPanel, o que permite desenhar sobre ele.

    // passo do grid (tamanho de cada célula)
    public final int passo = 20; // Define o tamanho de cada bloco/célula em pixels (20x20).

    // referência para a Snake
    public Snake cobra; // Objeto que representa a cobra, com seu corpo e lógica de movimento.

    // listas de comida e obstáculos (posições no grid)
    public ArrayList<Point> comida = new ArrayList<>();   // Lista de posições (x, y) onde há comida.
    public ArrayList<Point> obstaculos = new ArrayList<>(); // Lista de posições (x, y) onde há obstáculos.

    // gerador aleatório
    private final Random rnd = new Random(); // Gerador usado para sortear novas posições.

    // pontuação
    public int pontos = 0; // Armazena a pontuação atual do jogador.

    // largura/altura da área de jogo (coincide com Jogo.java)
    public final int largura = 800; // Largura total da área de jogo em pixels.
    public final int altura = 600;  // Altura total da área de jogo em pixels.

    // construtor: configura painel e cria elementos iniciais
    public PainelCobra() {
        // define preferências de tamanho (ajuda alguns gerenciadores)
        this.setSize(largura, altura);                        // Define o tamanho real do painel.

        // permite receber foco e garante que as teclas de navegação não roubem o foco
        this.setFocusable(true);                              // Permite que o painel receba o foco do teclado (importante para o KeyListener).
        this.setFocusTraversalKeysEnabled(false);             // Impede que teclas como TAB/Setas roubem o foco, garantindo que o KeyListener as receba.

        // cria a cobra no centro
        this.cobra = new Snake(largura, altura, passo); // Instancia a cobra, passando o tamanho da tela e o passo do grid.

        // gera itens iniciais: 5 comidas e 5 obstáculos
        gerarComida(1);     // Chama o método para gerar 5 posições iniciais de comida.
        gerarObstaculos(5); // Chama o método para gerar 5 posições iniciais de obstáculos.
    }

    /**
     * gerarPosicao:
     * gera uma posição alinhada ao grid que não esteja sobre a cobra,
     * sobre comida ou sobre obstáculo. Retorna null se falhar.
     * Publico porque Jogo.java chama para reposicionar comida.
     */
    public Point gerarPosicao() {
        int tentativas = 0; // Contador de tentativas para evitar loops infinitos.
        while (tentativas < 500) { // Tenta no máximo 500 vezes encontrar uma posição livre.
            tentativas++;
            // Gera um X alinhado ao grid: sorteia uma célula (0 a largura/passo - 1) e multiplica por passo.
            int x = rnd.nextInt(largura / passo) * passo;
            // Gera um Y alinhado ao grid: sorteia uma célula (0 a altura/passo - 1) e multiplica por passo.
            int y = rnd.nextInt(altura / passo) * passo;

            boolean ocupado = false; // Indica se a posição sorteada está ocupada.

            // verifica sobre a cobra
            for (Point s : cobra.corpo) { // Percorre todos os segmentos do corpo da cobra.
                if (s.x == x && s.y == y) { // Se a posição sorteada for igual à posição do segmento 's'.
                    ocupado = true; break;  // Marca como ocupado e interrompe o loop (já achou a colisão).
                }
            }
            if (ocupado) continue; // Se ocupado, pula para a próxima iteração do 'while' (próxima tentativa).

            // verifica sobre comida
            for (Point c : comida) { // Percorre todas as posições de comida.
                if (c.x == x && c.y == y) {
                    ocupado = true; break;
                }
            }
            if (ocupado) continue;

            // verifica sobre obstáculos
            for (Point o : obstaculos) { // Percorre todas as posições de obstáculos.
                if (o.x == x && o.y == y) {
                    ocupado = true; break;
                }
            }
            if (ocupado) continue;

            // posição livre encontrada
            return new Point(x, y); // Se chegou aqui, a posição está livre: retorna o novo ponto.
        }
        return null; // Se o while terminou (atingiu 500 tentativas) sem achar, retorna nulo.
    }

    // gera quantidade de comidas iniciais (usa gerarPosicao)
    public void gerarComida(int qtd) {
        comida.clear(); // Limpa a lista de comidas existentes.
        for (int i = 0; i < qtd; i++) { // Loop para gerar a quantidade pedida (qtd).
            Point p = gerarPosicao(); // Tenta gerar uma posição livre.
            if (p != null) comida.add(p); // Se a posição foi encontrada (não é null), adiciona à lista.
            else break; // Se não encontrou, para o loop.
        }
    }

    // gera quantidade obstáculos iniciais (usa gerarPosicao)
    public void gerarObstaculos(int qtd) {
        obstaculos.clear();
        for (int i = 0; i < qtd; i++) {
            Point p = gerarPosicao();
            if (p != null) obstaculos.add(p);
        }
    }

    // desenha grid, comida, obstáculos e cobra
    @Override
    protected void paintComponent(Graphics g) { // Método chamado para desenhar o painel. 'g' é o pincel gráfico.
        super.paintComponent(g); // Chama o método original para manter o fundo padrão do JPanel.

        // desenha grade leve (linhas)
        g.setColor(new Color(200, 200, 200)); // Define a cor do pincel (cinza claro).
        for (int x = 0; x < largura; x += passo) g.drawLine(x, 0, x, altura); // Desenha linhas verticais a cada 'passo'.
        for (int y = 0; y < altura; y += passo) g.drawLine(0, y, largura, y); // Desenha linhas horizontais a cada 'passo'.

        // desenha comida (verde, pequeno oval)
        g.setColor(new Color(0, 160, 0)); // Define a cor para verde.
        for (Point c : comida) g.fillOval(c.x + 2, c.y + 2, passo - 6, passo - 6);
        // Desenha um oval preenchido (comida): a adição de +2 e subtração de -6 serve para desenhar um oval menor dentro da célula de 20x20.

        // desenha obstáculos (vermelho)
        g.setColor(new Color(160, 0, 0)); // Define a cor para vermelho escuro.
        for (Point o : obstaculos) g.fillRect(o.x, o.y, passo, passo); // Desenha um retângulo preenchido (obstáculo) do tamanho exato da célula.

        // desenha a cobra (usa o método da classe Snake)
        cobra.desenhar(g); // Chama o método de desenho da própria cobra.

        // desenha pontuação no canto superior esquerdo
        g.setColor(Color.BLACK); // Define a cor do texto para preto.
        g.setFont(new Font("Arial", Font.BOLD, 18)); // Define a fonte e o tamanho.
        g.drawString("Pontos: " + pontos, 10, 20); // Desenha o texto da pontuação nas coordenadas (10, 20).
    }
}