public class Usuario {
    private String nome;
    private int idade;

    Usuario(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    void mostrar_usuario(){
        System.out.println("Nome: "+nome+
                "\nIdade: "+idade+
                "\n\n");
    }

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade){
        if(idade <0){
            System.err.println("Falha, não existe idade negativa!!!!");
            return;
        }
        if(idade > 200){
            System.err.println("Ninguém vive tanto! Deixe de mentir!!!");
            return;
        }
        this.idade = idade;
    }
}
