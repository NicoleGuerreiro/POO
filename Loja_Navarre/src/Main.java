import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // CRIAR LOJA
        Loja loja = new Loja("Fortaleza Basgiath");

        // Itens para TODAS as classes
        loja.adicionarProduto(new Consumivel("Poção de Cura", 50, "Cura", "Todas", 3));
        loja.adicionarProduto(new Consumivel("Poção de Energia", 70, "Energia", "Todas", 2));

        // Itens APENAS para CAVALEIROS
        loja.adicionarProduto(new Arma("Lâmina de Tairn", 500, "Espada", "Cavaleiro", 25, 8, 5, -3, "Relâmpago"));
        loja.adicionarProduto(new Arma("Adaga de Sgaeyl", 450, "Adaga", "Cavaleiro", 15, 5, 8, 5, "Gelo"));
        loja.adicionarProduto(new Equipamento("Armadura de Escamas Negras", 600, "Armadura", "Cavaleiro", 8, 30, 3, -8));

        // Itens APENAS para ESCRIBAS
        loja.adicionarProduto(new Arma("Cajado do Conhecimento", 200, "Cajado", "Escriba", 5, 0, 15, 0, "Luz"));
        loja.adicionarProduto(new Equipamento("Túnica do Sábio", 250, "Vestes", "Escriba", 0, 8, 12, 3));

        // Itens APENAS para CURANDEIROS
        loja.adicionarProduto(new Arma("Cetro da Cura", 180, "Cetro", "Curandeiro", 3, 2, 10, 2, "Luz"));
        loja.adicionarProduto(new Equipamento("Manto do Curandeiro", 220, "Manto", "Curandeiro", 0, 5, 15, 5));

        // CRIAÇÃO DO PERSONAGEM
        System.out.println("=== CRIAÇÃO DE PERSONAGEM ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        String classe = "";
        while (classe.equals("")) {
            System.out.println("\nEscolha sua classe:");
            System.out.println("1. Cavaleiro de Dragão");
            System.out.println("2. Escriba");
            System.out.println("3. Curandeiro");
            System.out.print("Escolha (1-3): ");
            int escolhaClasse = scanner.nextInt();

            switch (escolhaClasse) {
                case 1 -> classe = "Cavaleiro";
                case 2 -> classe = "Escriba";
                case 3 -> classe = "Curandeiro";
                default -> System.out.println("Opção inválida!");
            }
        }

        // Atributos base diferentes para cada classe
        int ataque = 10, defesa = 10, magia = 10, velocidade = 10;
        if (classe.equals("Cavaleiro")) {
            ataque = 15;
            defesa = 12;
            magia = 15;
            velocidade = 11;
        } else if (classe.equals("Escriba")) {
            ataque = 6;
            defesa = 8;
            magia = 5;
            velocidade = 8;
        } else if (classe.equals("Curandeiro")) {
            ataque = 7;
            defesa = 9;
            magia = 14;
            velocidade = 10;
        }

        Personagem player = new Personagem(nome, classe, 10000, ataque, defesa, magia, velocidade);

        // ESCOLHA DO DRAGÃO PARA CAVALEIROS
        if (classe.equals("Cavaleiro")) {
            System.out.println("\n=== ESCOLHA SEU DRAGÃO ===");
            System.out.println("Dragões disponíveis para vínculo:");

            String especie = "";
            String cor = "";
            String descricao = "";

            while (especie.equals("")) {
                System.out.println("\n1. TAIRN (Negro) - Rabo-de-Chicote - Astuto e poderoso");
                System.out.println("2. SGAEYL (Azul) - Rabo-de-Adaga - Impiedosa e estratégica");
                System.out.println("3. BAIDE (Verde) - Rabo-de-Escorpião - Racional e leal");
                System.out.println("4. ANDARNA (Dourado) - Rabo-de-Pena - Curiosa e rara");
                System.out.println("5. DRAGÃO VERMELHO - Rabo-de-Espada - Temperamental e agressivo");
                System.out.print("\nEscolha seu dragão (1-5): ");
                int escolhaDragao = scanner.nextInt();

                switch (escolhaDragao) {
                    case 1 -> { especie = "Rabo-de-Chicote"; cor = "Negro"; descricao = "Tairn - Poderoso e exigente"; }
                    case 2 -> { especie = "Rabo-de-Adaga"; cor = "Azul"; descricao = "Sgaeyl - Ágil e mortal"; }
                    case 3 -> { especie = "Rabo-de-Escorpião"; cor = "Verde"; descricao = "Baide - Leal e venenoso"; }
                    case 4 -> { especie = "Rabo-de-Pena"; cor = "Dourado"; descricao = "Andarna - Jovem e curiosa"; }
                    case 5 -> { especie = "Rabo-de-Espada"; cor = "Vermelho"; descricao = "Dragão Vermelho - Agressivo"; }
                    default -> System.out.println("Opção inválida!");
                }
            }

            player.setDragao(especie, cor);
            System.out.println("\nVÍNCULO ESTABELECIDO!");
            System.out.println("Dragão: " + descricao);
        }

        // MENU PRINCIPAL
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Comprar na Loja");
            System.out.println("2. Ver Ficha do Personagem");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> loja.comprarItem(player, scanner);
                case 2 -> player.exibirInfo();
                case 3 -> System.out.println("Saindo... Até a próxima, guerreiro!");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 3);

        scanner.close();
    }
}
