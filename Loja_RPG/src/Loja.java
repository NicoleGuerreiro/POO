import java.util.ArrayList;

public class Loja {
    private String nome;
    private ArrayList<Item> estoque;

    public Loja(String nome) {
        this.nome = nome;
        this.estoque = new ArrayList<>();
    }

    /** Adiciona produto ao estoque */
    public void adicionarProduto(Item novo) {
        estoque.add(novo);
    }

    /** Exibe todos os produtos disponíveis */
    public void listarProdutos() {
        System.out.println("\n========== " + nome + " ==========");
        for (Item I : estoque) {
            I.exibirDados();
            System.out.println("-------------------------------\n");
        }
    }

    public Item comprar(Player P, String nome) {
        for (int i = 0; i < estoque.size(); i++) {
            Item I = estoque.get(i);

            if (I.getNome().equalsIgnoreCase(nome)) {

                /** Verifica se o jogador tem dinheiro */
                if (P.pagar(I.getPreco())) {

                    /** Adiciona na mochila */
                    P.adicionarNaMochila(I);

                    /** Equipar automaticamente se for Arma ou Equipamento */
                    if (I instanceof Arma || I instanceof Equipamentos) {
                        P.equiparItem(I);
                    }

                    /** Remove da loja só depois de equipar/adicionar */
                    estoque.remove(i);

                    System.out.println("Compra realizada com sucesso!");
                }

                /** Retorna o item comprado (ou nulo se não tinha dinheiro) */
                return I;
            }
        }

        System.out.println("Item não encontrado!");
        return null;
    }

}
