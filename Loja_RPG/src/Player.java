import java.util.ArrayList;

public class Player {
    private String nome;
    float dinheiro;
    private ArrayList<Item> armamentos;

    /**
     * Atributos de combate
     */
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    /**
     * Equipamentos atuais
     */
    private Arma armaEquipada;
    private Equipamentos equipamentoEquipada;

    /**
     * Mochila (inventário)
     */
    private ArrayList<Item> mochila;

    /**
     * Construtor
     */
    public Player(String nome, float dinheiro, int ataque, int defesa, int magia, int velocidade) {
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
        this.mochila = new ArrayList<>();
    }

    /**
     * Exibie ficha do personagem
     */
    public void exibirFicha() {
        System.out.println("\n===== FICHA DO PERSONAGEM =====");
        System.out.println("Nome: " + nome);
        System.out.println("Dinheiro: " + dinheiro);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Magia: " + magia);
        System.out.println("Velocidade: " + velocidade);
        System.out.println("Arma equipada: " + (armaEquipada != null ? armaEquipada.getNome() : "Nenhuma"));
        System.out.println("Equipamento: " + (equipamentoEquipada != null ? equipamentoEquipada.getNome() : "Nenhum"));
        System.out.println("Itens na mochila:");
        for (Item item : mochila) {
            System.out.println("- " + item.getNome());
        }
        System.out.println("================================\n");
    }

    /**
     * Adicionar item à mochila
     */
    public void adicionarNaMochila(Item item) {
        mochila.add(item);
    }

    /**
     * Equipar um item (arma ou equipamento)
     */
    public void equiparItem(Item item) {

        /** Se for uma arma */
        if (item instanceof Arma) {
            Arma nova = (Arma) item;

            /** Se já havia uma arma equipada, remover o bônus antigo */
            if (armaEquipada != null) {
                ataque -= armaEquipada.getAtaque();
                defesa -= armaEquipada.getDefesa();
                magia -= armaEquipada.getMagia();
                velocidade -= armaEquipada.getVelocidade();
            }

            /** Aplicar os bônus da nova arma */
            armaEquipada = nova;
            ataque += nova.getAtaque();
            defesa += nova.getDefesa();
            magia += nova.getMagia();
            velocidade += nova.getVelocidade();

            System.out.println("Você equipou a arma: " + nova.getNome());
            System.out.println("Seus atributos foram atualizados!");

            /** Se for um equipamento (como armadura, túnica, etc.) */
        } else if (item instanceof Equipamentos) {
            Equipamentos novo = (Equipamentos) item;

            /** Se já havia um equipamento, remover bônus antigo */
            if (equipamentoEquipada != null) {
                ataque -= equipamentoEquipada.getAtaque();
                defesa -= equipamentoEquipada.getDefesa();
                magia -= equipamentoEquipada.getMagia();
                velocidade -= equipamentoEquipada.getVelocidade();
            }

            /** Aplicar bônus do novo equipamento */
            equipamentoEquipada = novo;
            ataque += novo.getAtaque();
            defesa += novo.getDefesa();
            magia += novo.getMagia();
            velocidade += novo.getVelocidade();

            System.out.println("Você equipou o equipamento: " + novo.getNome());
            System.out.println("Seus atributos foram atualizados!");
        }
        else {
            System.out.println("Esse item não pode ser equipado!");
        }
    }

    /**
     * Pagar (retirar dinheiro se possível)
     */
    public boolean pagar(float valor) {
        if (dinheiro >= valor) {
            dinheiro -= valor;
            return true;
        } else {
            System.out.println("Dinheiro insuficiente!");
            return false;
        }
    }

    /**
     * Usar um consumível da mochila
     */
    public void usarConsumivel(String nomeItem) {
        for (Item item : mochila) {
            if (item instanceof Consumivel && item.getNome().equalsIgnoreCase(nomeItem)) {
                ((Consumivel) item).usar();
                return;
            }
        }
        System.out.println("Item não encontrado ou não é um consumível.");
    }
}




