import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /** Cria a loja e adiciona alguns itens */
        Loja L = new Loja("Venda do seu Zé");
        L.adicionarProduto(new Consumivel("Poção Cura", 35f, "Mágico", 10));
        L.adicionarProduto(new Arma("Espada de Fogo", 300f, "Aço", 400, 200, 400, 200, 400));
        L.adicionarProduto(new Item("Mochila do Aventureiro", 120f, "Zaratrusta"));
        L.adicionarProduto(new Item("Tocha para Masmorra", 5f, "Fogareu"));
        L.adicionarProduto(new Item("Óculos da Verdade", 200f, "Bruxarias"));
        L.adicionarProduto(new Equipamentos("Armadura de Ouro", 230f, "Dona Clotilde", 0, 50, 20, -5));
        L.adicionarProduto(new Equipamentos("Helmo Chifrudão", 70f, "Zaratrusta", 2, 5, 0, 5));

        Player P = new Player("Lancelot", 10000, 500, 500, 300, 400);

        /** Scanner para ler o que o usuário digita */
        Scanner sc = new Scanner(System.in);

        int opcao = 0; /** Variável que guarda a escolha do usuário */

        /** Loop principal do menu — repete até o jogador escolher "Sair" */
        while (opcao != 5) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Listar Itens da Loja");
            System.out.println("2 - Comprar Item");
            System.out.println("3 - Ver Ficha do Personagem");
            System.out.println("4 - Usar Consumível");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    L.listarProdutos();
                    break;

                case 2:
                    L.listarProdutos();
                    System.out.print("Digite o nome do item que deseja comprar: ");
                    String nomeItem = sc.nextLine();
                    L.comprar(P, nomeItem);
                    break;

                case 3:
                    P.exibirFicha();
                    break;

                case 4:
                    System.out.print("Digite o nome do consumível que deseja usar: ");
                    String nomeConsumivel = sc.nextLine();
                    P.usarConsumivel(nomeConsumivel);
                    break;

                case 5:
                    System.out.println("Encerrando o programa... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        sc.close(); /** Fecha o Scanner */
    }
}