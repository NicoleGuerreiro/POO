// imports gráficos e de evento do mouse
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
  PainelHanoi.java
  - JPanel simples que desenha as 3 torres (retângulos) e os discos (retângulos)
  - Detecta cliques do mouse para o modo jogador
  - Pede repaint quando o jogo muda
*/
public class PainelHanoi extends JPanel {
    // referência para a lógica do jogo
    private final JogoHanoi jogo;

    // construtor recebe o objeto de lógica
    public PainelHanoi(JogoHanoi jogo) {
        // salva a referência
        this.jogo = jogo;
        // cor de fundo branca
        setBackground(Color.WHITE);

        // adiciona listener para clique do mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // converte a coordenada X do clique em índice de torre (0,1,2)
                int torre = qualTorre(e.getX());
                // só processa cliques no modo jogador
                if (torre != -1 && !jogo.isModoAutomatico()) {
                    // pede ao jogo selecionar ou mover conforme lógica
                    jogo.selecionarOuMover(torre);
                    // atualiza desenho
                    repaint();
                    // se finalizou, mostra mensagem de vitória
                    if (jogo.jogoConcluido()) {
                        JOptionPane.showMessageDialog(PainelHanoi.this,
                                "Parabéns! Você completou a Torre de Hanói.");
                    }
                }
            }
        });
    }

    // identifica qual terço da largura foi clicado => devolve 0,1 ou 2
    private int qualTorre(int x) {
        int largura = getWidth();              // largura atual do painel
        if (largura <= 0) return -1;           // proteção
        int terc = largura / 3;                // largura de cada terceira parte
        if (x < terc) return 0;                // clique na área esquerda
        if (x < 2 * terc) return 1;            // clique na área do meio
        return 2;                              // clique na área direita
    }

    // método de desenho do Swing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);               // limpa o painel
        Graphics2D g2 = (Graphics2D) g;        // converte para Graphics2D para facilidade
        int w = getWidth();                    // largura do painel
        int h = getHeight();                   // altura do painel

        // desenha três torres (apenas as bases retangulares)
        for (int i = 0; i < 3; i++) {
            // posição X central de cada torre: centro de cada terço
            int cx = (i * w / 3) + w / 6;
            // 'chão' onde os discos ficarão empilhados
            int chao = h - 80;
            // desenha a haste como um retângulo fino
            int hasteW = 8;
            int hasteH = 200;
            int hx = cx - hasteW / 2;
            int hy = chao - hasteH;
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(hx, hy, hasteW, hasteH);
            // desenha a base retangular da torre
            int baseW = 160;
            int bx = cx - baseW / 2;
            g2.fillRect(bx, chao, baseW, 10);
        }

        // pede às torres/discos do jogo para serem desenhados
        Torre[] torres = jogo.getTorres();
        for (int i = 0; i < 3; i++) {
            // define centro e chão para cada torre (o Jogo/Torre usará isso para desenhar discos)
            int cx = (i * w / 3) + w / 6;
            int chao = h - 80;
            torres[i].setPosCentro(cx);
            torres[i].setChao(chao);
            // desenha os discos que estão empilhados nessa torre
            torres[i].desenhar(g2);
        }

        // se houver um disco selecionado (fora das torres), desenha ele no topo central como feedback
        Disco s = jogo.getDiscoSelecionado();
        if (s != null) {
            int cx = w / 2;      // centro do painel
            int y = 30;          // posição fixa no topo para mostrar disco selecionado
            s.desenharNoPonto(g2, cx, y);
        }
    }
}
