import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.w3c.dom.ls.LSOutput;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        CSVReader cr = new CSVReader(new FileReader("saida.csv"));
        List<String[]> lista = cr.readAll();
        for(String[] linha: lista){
            System.out.println(Arrays.toString(linha));
        }
//        CSWriter cw = new CSWriter(new FileWriter("saida.csv"));
//        String linha[] = {"2", "5", "8", "3", "5", "8", "3"};
//        for (int i=0; i<10; i++)
//            cw.writeNext(linha);
//        cw.writeNext();
//

    }
}
//        Gson leitor = new Gson();
//        Reader reader = Files.newBufferedReader(Path.get("lista de usuários.json"));
//        Sistema sucesso = leitor.fromJson(reader, Sistema.class);
//
//        sucesso.exibir_sistema();
//
//        sucesso.cadastrar_usuario(new Usuario("Otávio",19));
//        sucesso.cadastrar_usuario(new Usuario("Ilbis",17));
//        sucesso.cadastrar_usuario(new Usuario("Davison",21));
//
//    }
//}
//        File A = new File("Usuario.txt");
//        Scanner leitor = new Scanner(A);
//
//        Sistema S = new Sistema("IFCE");
//        //S.cadastrar_usuario(new Usuario("Yasmin", 17));
//        while (leitor.hasNextLine()) {
//            String texto = leitor.nextLine();
//            String str[] = texto.split(", ");
//            int x = Integer.parseInt(str[1]);
//            S.cadastrar_usuario(new Usuario(str[0], x));
//        }
//
//
//        S.exibir_sistema();
//    }
//}
//
//
//        FileWriter fw = new FileWriter("lista de usuários.json");
//        Gson teste = new Gson();
//        teste.toJson(S,fw);
//        String str = teste.toJson(S);
//        sout("Resultado:\n"+str);
//        fw.close();
//        }
//    }
