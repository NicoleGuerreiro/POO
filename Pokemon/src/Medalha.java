import java.util.ArrayList;
public class Medalha {
    String nome;

    String[] QUADRO_DE_MEDALHAS = {
            "Pedra", "Cascata", "Trovão", "Arco-Íris", "Pântano",
            "Alma", "Vulcão", "Terra"
    };

    public Medalha(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
