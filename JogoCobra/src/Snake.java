import java.awt.*;          // Importa AWT para Point e Graphics.
import java.util.ArrayList; // Importa ArrayList para guardar o corpo.

/*
 * Snake.java
 * Implementação simples compatível com o estilo do professor,
 * mas com corpo (lista de segmentos) para permitir crescer.
 */
public class Snake {


    public int pos_x;      // Posição X da cabeça (cópia do primeiro elemento do corpo).
    public int pos_y;      // Posição Y da cabeça.
    public int tamanho;    // Número de segmentos (tamanho do corpo).
    public int largura;    // Tamanho em pixels de cada segmento (igual ao 'passo' do PainelCobra).
    public int orientacao; // 0=cima, 1=direita, 2=baixo, 3=esquerda.

    // lista de segmentos (cada Point é um bloco do corpo)
    public ArrayList<Point> corpo = new ArrayList<>(); // A lista que armazena as coordenadas de cada bloco da cobra.

    // construtor: define posição inicial e cria corpo simples de 3 blocos
    public Snake(int larguraTela, int alturaTela, int passo){
        this.largura = passo;
        // centraliza e alinha ao grid
        int cx = (larguraTela / 2 / passo) * passo; // Calcula o X central, alinhado à grade.
        int cy = (alturaTela / 2 / passo) * passo; // Calcula o Y central, alinhado à grade.

        this.pos_x = cx;
        this.pos_y = cy;
        this.tamanho = 3;      // A cobra começa com 3 blocos.
        this.orientacao = 1;   // Começa movendo-se para a direita (orientacao=1).

        // cria corpo (cabeça + 2 segmentos atrás)
        corpo.clear();
        corpo.add(new Point(pos_x, pos_y));        // 1º Bloco: A cabeça (na posição central).
        corpo.add(new Point(pos_x - passo, pos_y)); // 2º Bloco: Um bloco à esquerda da cabeça.
        corpo.add(new Point(pos_x - 2 * passo, pos_y)); // 3º Bloco: Dois blocos à esquerda da cabeça (cauda).
    }

    /**
     * anda:
     * - move cada segmento para a posição do anterior (do fim para a frente)
     * - atualiza a posição da cabeça conforme a orientação
     * - aplica teletransporte (wrap-around) quando ultrapassa os limites
     */
    public void andar(int lim_x, int lim_y){ // lim_x/lim_y são a largura/altura da tela.
        // move corpo: do fim para o início
        for (int i = corpo.size() - 1; i > 0; i--) { // Começa no penúltimo elemento (último é size()-1) e vai até o 1.
            corpo.get(i).x = corpo.get(i - 1).x;     // O segmento 'i' (cauda) recebe a posição X do segmento anterior 'i-1'.
            corpo.get(i).y = corpo.get(i - 1).y;     // O segmento 'i' (cauda) recebe a posição Y do segmento anterior 'i-1'.
        } // Isso arrasta o corpo, deixando a posição da cabeça (índice 0) intacta para ser movida abaixo.

        // atualiza a cabeça a partir da orientação
        Point cabeca = corpo.get(0); // Pega a referência para o primeiro segmento (a cabeça).
        switch (orientacao) {
            case 0 -> cabeca.y -= largura; // 0 (Cima): Diminui a coordenada Y em 'largura' (um passo).
            case 1 -> cabeca.x += largura; // 1 (Direita): Aumenta a coordenada X.
            case 2 -> cabeca.y += largura; // 2 (Baixo): Aumenta a coordenada Y.
            case 3 -> cabeca.x -= largura; // 3 (Esquerda): Diminui a coordenada X.
        }

        // teletransporte (wrap-around)
        if (cabeca.x + largura > lim_x) cabeca.x = 0;               // Se a cabeça sai pela direita, reaparece na esquerda (x=0).
        if (cabeca.y + largura > lim_y) cabeca.y = 0;               // Se a cabeça sai por baixo, reaparece no topo (y=0).
        if (cabeca.x < 0) cabeca.x = lim_x - largura;              // Se a cabeça sai pela esquerda, reaparece na direita (na última coluna).
        if (cabeca.y < 0) cabeca.y = lim_y - largura;              // Se a cabeça sai por cima, reaparece embaixo.

        // atualiza campos pos_x/pos_y (compatibilidade com código que usa eles)
        pos_x = cabeca.x;
        pos_y = cabeca.y;
    }

    // crescer: adiciona um novo segmento (copia o último)
    public void crescer(){
        Point ultimo = corpo.get(corpo.size() - 1); // Pega o último segmento (a cauda).
        corpo.add(new Point(ultimo.x, ultimo.y));   // Cria um novo Point, cópia exata do último, e adiciona no final da lista.
        tamanho = corpo.size(); // Atualiza a variável de tamanho.
    }

    // verifica colisão da cabeça com o próprio corpo (exclui índice 0)
    public boolean bateuNoProprioCorpo(){
        Point cabeca = corpo.get(0); // Pega a referência da cabeça.
        for (int i = 1; i < corpo.size(); i++) { // Percorre do SEGUNDO segmento (índice 1) até o final.
            if (cabeca.equals(corpo.get(i))) return true; // Se a posição da cabeça é igual à posição de qualquer outro segmento.
        }
        return false; // Se o loop terminou e não houve colisão, retorna false.
    }

    // desenhar: pinta cada segmento (cabeça com cor ligeiramente diferente)
    protected void desenhar(Graphics g){
        for (int i = 0; i < corpo.size(); i++) { // Percorre todos os segmentos do corpo.
            Point p = corpo.get(i);
            if (i == 0) g.setColor(Color.BLUE.darker()); // Se for o primeiro segmento (cabeça), usa azul mais escuro.
            else g.setColor(Color.BLUE);               // Para o resto do corpo, usa azul padrão.
            g.fillRect(p.x, p.y, largura, largura);    // Desenha o quadrado preenchido do segmento.
        }
    }
}