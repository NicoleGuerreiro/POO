// imports básicos
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;

/*
  JogoHanoi.java
  - Mantém o estado do jogo: 3 torres e discos
  - Controla seleção/movimentação no modo jogador
  - Gera e executa movimentos no modo automático (lista simples)
*/
public class JogoHanoi {
    // as três torres do jogo
    private final Torre[] torres;
    // disco atualmente selecionado pelo jogador (null se nenhum)
    private Disco discoSelecionado;
    // índice da torre de onde o disco selecionado veio (para devolver se necessário)
    private int torreOrigem = -1;
    // número total de discos
    private final int numDiscos;
    // indica se é modo automático
    private final boolean modoAutomatico;
    // lista simples de movimentos para o modo automático (cada movimento é um par [origem,destino])
    private final List<int[]> movimentos;

    // construtor
    public JogoHanoi(int numDiscos, boolean modoAutomatico) {
        this.numDiscos = numDiscos;
        this.modoAutomatico = modoAutomatico;
        this.torres = new Torre[3];
        this.movimentos = new ArrayList<>();
        // inicializa as torres e empilha todos os discos na torre 0
        inicializar();
    }

    // inicializa as três torres e empilha discos na torre 0 (maior embaixo)
    private void inicializar() {
        for (int i = 0; i < 3; i++) {
            torres[i] = new Torre();
        }
        // cria discos do maior para o menor e empilha na torre 0
        for (int i = numDiscos; i >= 1; i--) {
            Disco d = new Disco(i, numDiscos);
            torres[0].push(d);
        }
    }

    // getters usados pelo PainelHanoi para desenhar
    public Torre[] getTorres() {
        return torres;
    }

    public boolean isModoAutomatico() {
        return modoAutomatico;
    }

    public Disco getDiscoSelecionado() {
        return discoSelecionado;
    }

    // lógica chamada quando jogador clica em uma torre no modo jogador
    public void selecionarOuMover(int torreClicada) {
        // se não há disco selecionado, tenta pegar o topo da torre clicada
        if (discoSelecionado == null) {
            if (!torres[torreClicada].isEmpty()) {
                // guarda de qual torre veio o disco (útil para devolver se movimento inválido)
                torreOrigem = torreClicada;
                // remove o topo da torre e marca como selecionado
                discoSelecionado = torres[torreClicada].pop();
            } else {
                // nada para pegar, dá um som de aviso
                Toolkit.getDefaultToolkit().beep();
            }
        } else {
            // já existe um disco selecionado: tenta colocá-lo na torre clicada
            if (torres[torreClicada].podeReceber(discoSelecionado)) {
                // se pode receber, empilha e limpa seleção
                torres[torreClicada].push(discoSelecionado);
                discoSelecionado = null;
                torreOrigem = -1;
            } else {
                // movimento inválido: devolve para torre de origem
                if (torreOrigem >= 0) {
                    torres[torreOrigem].push(discoSelecionado);
                } else {
                    // fallback: tenta devolver para qualquer torre que aceite
                    boolean devolveu = false;
                    for (int i = 0; i < 3; i++) {
                        if (torres[i].podeReceber(discoSelecionado)) {
                            torres[i].push(discoSelecionado);
                            devolveu = true;
                            break;
                        }
                    }
                    if (!devolveu) {
                        // último recurso: empilha na torre clicada (não ideal, mas evita perda)
                        torres[torreClicada].push(discoSelecionado);
                    }
                }
                // limpa seleção e dá som de aviso
                discoSelecionado = null;
                torreOrigem = -1;
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    // verifica se todos os discos estão empilhados na torre do meio (índice 1)
    public boolean jogoConcluido() {
        return torres[1].size() == numDiscos;
    }

    // ---------------- MODO AUTOMÁTICO ----------------
    // Gera a lista de movimentos com a recursão clássica e executa-os com Timer
    public void iniciarResolucaoAutomatica(PainelHanoi painel) {
        // limpa movimentos antigos
        movimentos.clear();
        // gera movimentos para mover de 0 (esquerda) para 1 (meio), usando 2 (direita) como auxiliar
        gerarMovimentosRec(numDiscos, 0, 2, 1);

        // cria um Timer que executa um movimento a cada 100 ms
        Timer timer = new Timer(500, e -> {
            if (movimentos.isEmpty()) {
                // se terminou, para o timer e mostra mensagem de conclusão
                ((Timer) e.getSource()).stop();
                if (jogoConcluido()) {
                    JOptionPane.showMessageDialog(painel, "Solução automática concluída!");
                }
                return;
            }
            // pega o primeiro movimento da lista
            int[] m = movimentos.remove(0);
            int origem = m[0];
            int destino = m[1];
            // realiza movimento: pop da origem e push no destino (se válido)
            if (!torres[origem].isEmpty()) {
                Disco d = torres[origem].pop();
                if (torres[destino].podeReceber(d)) {
                    torres[destino].push(d);
                } else {
                    // devolve se algo estranho acontecer (proteção)
                    torres[origem].push(d);
                }
            }
            // pede ao painel redesenhar para mostrar o movimento
            painel.repaint();
        });
        // inicia o timer
        timer.start();
    }

    // gera recursivamente os movimentos e os adiciona à lista 'movimentos'
    private void gerarMovimentosRec(int n, int origem, int auxiliar, int destino) {
        if (n <= 0) return; // nada a fazer
        // mover n-1 de origem para auxiliar
        gerarMovimentosRec(n - 1, origem, destino, auxiliar);
        // mover 1 disco de origem para destino (adiciona à lista)
        movimentos.add(new int[]{origem, destino});
        // mover n-1 de auxiliar para destino
        gerarMovimentosRec(n - 1, auxiliar, origem, destino);
    }
}
