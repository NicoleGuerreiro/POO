public class Item {
    private String nome;
    private float preco;
    private String tipo;
    private int qtd;

    public Item(String nome, float preco, String tipo){
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.qtd = 1;
    }

    public void exibirDados(){
        System.out.println("Produto: "+nome+
                "\nPreço: "+preco+
                "\nMarca: "+tipo);
    }

    public String getNome(){
        return nome;
    }

    public float getPreco(){
        return preco;
    }

    public String getTipo(){
        return tipo;
    }

    public void setPreco(float Preco){
        this.preco = preco;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
