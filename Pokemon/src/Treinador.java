import java.util.ArrayList;
import java.util.Scanner;

public class Treinador {
    String nome;
    int idade;
    String[] medalhas;
    int qtdMedalhas;
    ArrayList<Pokemon> equipe;

    Treinador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.medalhas = new String[8];
        this.qtdMedalhas = 0;
        this.equipe = new ArrayList<Pokemon>();
    }

    void adicionarPokemon(Pokemon p) {
        if (equipe.size() < 6) {
            equipe.add(p);
        } else {
            System.out.println("A equipe já tem 6 Pokémons!");
        }
    }

    void retirarPokemon(Scanner entrada) {
        if (equipe.isEmpty()) {
            System.out.println("Não há pokémons para retirar!");
            return;
        }
        System.out.println("Qual Pokémon deseja retirar?");
        for (int i = 0; i < equipe.size(); i++) {
            System.out.println((i + 1) + " - " + equipe.get(i));
        }
        int escolha = entrada.nextInt();
        entrada.nextLine();
        if (escolha > 0 && escolha <= equipe.size()) {
            equipe.remove(escolha - 1);
            System.out.println("Pokémon removido!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    void adicionarMedalha(String nome) {
        if (qtdMedalhas < medalhas.length) {
            medalhas[qtdMedalhas++] = nome;
        } else {
            System.out.println("Você já tem as 8 medalhas!");
        }
    }

    void exibirFicha() {
        System.out.println("\nTreinador: " + this.nome +
                "\nIdade: " + this.idade);

        System.out.println("\nMedalhas:");
        if (qtdMedalhas == 0) {
            System.out.println("(sem medalhas)");
        } else {
            for (int i = 0; i < qtdMedalhas; i++) {
                System.out.println("- " + medalhas[i]);
            }
        }

        System.out.println("\nPokémons:");
        if (equipe.isEmpty()) {
            System.out.println("(sem pokémons)");
        } else {
            for (int i = 0; i < equipe.size(); i++) {
                System.out.println((i + 1) + ") " + equipe.get(i));
            }
        }

        System.out.println("----------------------");
    }
}
