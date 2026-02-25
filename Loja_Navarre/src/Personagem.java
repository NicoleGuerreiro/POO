import java.util.ArrayList;

public class Personagem {
    private String nome, classe, especieDragao, corDragao;
    private double dinheiro;
    private int ataque, defesa, magia, velocidade;
    private Arma arma;
    private Equipamento equipamento;
    private ArrayList<Item> mochila;

    public Personagem(String nome, String classe, double dinheiro,
                      int ataque, int defesa, int magia, int velocidade) {
        this.nome = nome;
        this.classe = classe;
        this.dinheiro = dinheiro;
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
        this.mochila = new ArrayList<>();
    }

    public void setDragao(String especie, String cor) {
        this.especieDragao = especie;
        this.corDragao = cor;
    }

    public void exibirInfo() {
        System.out.println("\n=== FICHA DO PERSONAGEM ===");
        System.out.println("Nome: " + nome + " | Classe: " + classe);
        if (especieDragao != null)
            System.out.println("Dragão: " + especieDragao + " (" + corDragao + ")");
        System.out.println("Dinheiro: " + dinheiro);
        System.out.println("Status: Ataque=" + ataque + " | Defesa=" + defesa + " | Magia=" + magia + " | Velocidade=" + velocidade);
        System.out.println("Arma equipada: " + (arma != null ? arma.getNome() : "Nenhuma"));
        System.out.println("Equipamento: " + (equipamento != null ? equipamento.getNome() : "Nenhum"));
        if (mochila.isEmpty()) System.out.println("Mochila vazia.");
        else {
            System.out.println("\nItens na mochila:");
            for (Item i : mochila) i.exibirInfo();
        }
    }

    public void adicionarItem(Item item) {
        mochila.add(item);
    }

    public boolean pagar(double valor) {
        if (dinheiro >= valor) {
            dinheiro -= valor;
            return true;
        }
        System.out.println("Dinheiro insuficiente!");
        return false;
    }

    public void equiparAutomatico(Item item) {
        if (item instanceof Arma a) {
            arma = a;
            System.out.println("Você equipou a arma: " + a.getNome());
        } else if (item instanceof Equipamento e) {
            equipamento = e;
            System.out.println("Você equipou o equipamento: " + e.getNome());
        }
    }

    public String getClasse() { return classe; }
    public double getDinheiro() { return dinheiro; }
}
