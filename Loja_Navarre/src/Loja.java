import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    private String nome;
    private ArrayList<Item> estoque;

    public Loja(String nome) {
        this.nome = nome;
        this.estoque = new ArrayList<>();
    }

    public void adicionarProduto(Item item) {
        estoque.add(item);
    }

    public void comprarItem(Personagem player, Scanner scanner) {
        System.out.println("\n=== LOJA " + nome.toUpperCase() + " ===");
        for (int i = 0; i < estoque.size(); i++) {
            System.out.print((i + 1) + ". ");
            estoque.get(i).exibirInfo();
        }

        System.out.print("\nEscolha o número do item (0 para cancelar): ");
        int escolha = scanner.nextInt();
        if (escolha == 0) return;
        if (escolha < 1 || escolha > estoque.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Item item = estoque.get(escolha - 1);

        // Verifica se pode comprar
        if (!item.getClassePermitida().equals("Todas") &&
                !item.getClassePermitida().equals(player.getClasse())) {
            System.out.println("Este item não é compatível com sua classe!");
            return;
        }

        if (player.pagar(item.getPreco())) {
            estoque.remove(item);
            player.adicionarItem(item);

            // Equipar automaticamente se for arma ou equipamento
            if (item instanceof Arma || item instanceof Equipamento) {
                player.equiparAutomatico(item);
            }

            System.out.println("Compra realizada com sucesso!");
        }
    }
}

