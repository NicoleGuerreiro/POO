import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Treinador T = new Treinador("Ash Ketchum", 15);
        T.adicionarPokemon(new Pokemon("Pikachu", "Elétrico", 50));
        T.adicionarPokemon(new Pokemon("Charmander", "Fogo", 60));
        T.adicionarPokemon(new Pokemon("Squirtle", "Água", 40));
        T.adicionarPokemon(new Pokemon("Bulbasaur", "Planta", 42));

        int op = 0;
        while (op != 5) {
            System.out.println("\nEscolha uma das opções abaixo: ");
            System.out.println("1 - Mostrar dados");
            System.out.println("2 - Adicionar Pokémon");
            System.out.println("3 - Retirar Pokémon");
            System.out.println("4 - Ganhar medalha");
            System.out.println("5 - Sair");
            op = entrada.nextInt();
            entrada.nextLine(); //

            switch (op) {
                case 1:
                    T.exibirFicha();
                    break;
                case 2:
                    System.out.print("Espécie: ");
                    String especie = entrada.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = entrada.nextLine();
                    System.out.print("Nível: ");
                    int nivel = entrada.nextInt();
                    entrada.nextLine();
                    T.adicionarPokemon(new Pokemon(especie, tipo, nivel));
                    break;
                case 3:
                    T.retirarPokemon(entrada);
                    break;
                case 4:
                    System.out.print("Nome da medalha: ");
                    String nomeMedalha = entrada.nextLine();
                    T.adicionarMedalha(nomeMedalha);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
