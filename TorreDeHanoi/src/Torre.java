// import para desenho simples
import java.awt.*;
import java.util.ArrayList;

/*
  Torre.java
  - Representa uma torre como uma lista (pilha) simples de discos.
  - Mantém posição (centro X e chao Y) para desenhar discos no painel.
  - Implementação intencionalmente simples: usamos ArrayList para simular pilha.
*/
public class Torre {
    // lista que guarda os discos (o último elemento é o topo)
    private final ArrayList<Disco> pilha;
    // posição X central (definida pelo painel)
    private int posCentro;
    // coordenada Y do chao (definida pelo painel)
    private int chao;

    // construtor inicializa a lista
    public Torre() {
        pilha = new ArrayList<>();
        posCentro = 0;
        chao = 0;
    }

    // define o centro X onde essa torre será desenhada
    public void setPosCentro(int x) {
        this.posCentro = x;
    }

    // define o Y do chao (base) onde os discos se empilham
    public void setChao(int y) {
        this.chao = y;
    }

    // retorna true se a torre estiver vazia
    public boolean isEmpty() {
        return pilha.isEmpty();
    }

    // empilha um disco (no topo)
    public void push(Disco d) {
        pilha.add(d);
    }

    // desempilha (remove e retorna) o topo; retorna null se vazia
    public Disco pop() {
        if (pilha.isEmpty()) return null;
        return pilha.remove(pilha.size() - 1);
    }

    // retorna o topo sem remover; null se vazia
    public Disco peek() {
        if (pilha.isEmpty()) return null;
        return pilha.get(pilha.size() - 1);
    }

    // quantidade de discos na torre
    public int size() {
        return pilha.size();
    }

    // checa se a torre pode receber o disco (não pode colocar maior sobre menor)
    public boolean podeReceber(Disco d) {
        Disco topo = peek();
        if (topo == null) return true; // vazia aceita qualquer disco
        // retorna true apenas se o tamanho do topo for maior (ou seja, topo > d)
        return topo.getTamanho() > d.getTamanho();
    }

    // desenha todos os discos empilhados nessa torre
    public void desenhar(Graphics2D g2) {
        // altura fixa de disco (deve ser a mesma usada em Disco)
        int altura = Disco.getAlturaConst();
        // percorre do índice 0 (base) até o topo
        for (int i = 0; i < pilha.size(); i++) {
            // o disco em posição i (base = i=0)
            Disco d = pilha.get(i);
            // calcula a coordenada Y: chao - 10 (base) - altura*(i+1)
            int y = chao - 10 - altura * (i + 1);
            // desenha o disco centrado em posCentro e na coordenada y
            d.desenharNoPonto(g2, posCentro, y);
        }
    }
}
