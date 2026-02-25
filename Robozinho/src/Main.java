import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro(10, 10);
        Robo robo = tab.getRobo();

        String nomeArquivo = "comandos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine();
            if (linha != null) {
                String[] partes = linha.split(",");
                int[] comandos = new int[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    comandos[i] = Integer.parseInt(partes[i].trim());
                }

                for (int i = 0; i < comandos.length; i++) {
                    int comando = comandos[i];
                    switch (comando) {
                        case 1: robo.ativarCaneta(); break;
                        case 2: robo.desativarCaneta(); break;
                        case 3: robo.virarEsquerda(); break;
                        case 4: robo.virarDireita(); break;
                        case 5:
                            int passos = comandos[++i];
                            robo.andar(passos, tab.getMatriz());
                            break;
                        case 6: tab.mostrarTabuleiro(); break;
                        default: /* ignora */ break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
