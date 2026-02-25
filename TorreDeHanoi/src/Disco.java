// imports básicos para desenho e cor
import java.awt.*;

/*
  Disco.java
  - Representa um disco simples com tamanho lógico (1..n)
  - Calcula largura em pixels proporcional ao seu "tamanho" lógico
  - Possui método para desenhar um retângulo no ponto solicitado
  - Implementação propositalmente simples: retângulo preenchido com cor fixa por tamanho
*/
public class Disco {
    // tamanho lógico: 1 = menor, numDiscos = maior
    private final int tamanho;
    // largura em pixels para desenhar
    private final int largura;
    // cor do disco
    private final Color cor;
    // altura fixa em pixels de todos os discos
    private static final int ALTURA = 20;

    // construtor recebe o 'tamanho' e o total de discos para calcular escala
    public Disco(int tamanho, int totalDiscos) {
        this.tamanho = tamanho;
        // largura mínima e máxima (em pixels)
        int minW = 40;
        int maxW = 140;
        // calcula fração de 0 (menor) a 1 (maior)
        double frac = (double) (tamanho - 1) / Math.max(1, totalDiscos - 1);
        // mapeia frac para largura entre minW e maxW
        this.largura = minW + (int) Math.round(frac * (maxW - minW));
        // escolhe cor simples baseada no tamanho (tons diferentes)
        float hue = 0.6f - (float) (0.5 * frac); // varia o matiz para diferenciar discos
        this.cor = Color.getHSBColor(hue, 0.7f, 0.9f);
    }

    // retorna o tamanho lógico
    public int getTamanho() {
        return tamanho;
    }

    // retorna a altura fixa (usada pela torre para empilhar)
    public static int getAlturaConst() {
        return ALTURA;
    }

    // desenha o disco centrado em (cx, y)
    public void desenharNoPonto(Graphics2D g2, int cx, int y) {
        // calcula x do canto esquerdo para centralizar em cx
        int x = cx - largura / 2;
        // pinta o retângulo (disco)
        g2.setColor(cor);
        g2.fillRect(x, y, largura, ALTURA);
        // desenha borda escura para contraste
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(x, y, largura, ALTURA);
        // desenha o número do tamanho no centro do disco (ajuda a visualizar ordem)
        g2.setColor(Color.BLACK);
        String s = String.valueOf(tamanho);
        FontMetrics fm = g2.getFontMetrics();
        int textW = fm.stringWidth(s);
        int textH = fm.getAscent();
        // posição do texto centralizada horizontalmente e verticalmente
        g2.drawString(s, cx - textW / 2, y + ALTURA / 2 + textH / 2 - 4);
    }
}
