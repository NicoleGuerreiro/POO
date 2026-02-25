import java.util.ArrayList;

public class Sistema {
    private String nome;
    private ArrayList<Usuario> usuarios;

    Sistema(String nome){
        this.nome = nome;
        this.usuarios = new ArrayList<>();
    }

    void cadastrar_usuario(Usuario x){
        this.usuarios.add(x);
    }


    void exibir_sistema(){
        System.out.println("Sistema "+nome);
        System.out.println("Lista de usuários:\n\n");
        for (Usuario u : usuarios){
            u.mostrar_usuario();
        }
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario(int index) {
        return this.usuarios.get(index);
    }
}
