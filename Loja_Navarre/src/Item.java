public class Item {
    protected String nome;
    protected double preco;
    protected String tipo;
    protected String classePermitida;

    public Item(String nome, double preco, String tipo, String classePermitida) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.classePermitida = classePermitida;
    }

    public void exibirInfo() {
        System.out.println("Item: " + nome + " | Preco: " + preco + " | Tipo: " + tipo);
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getTipo() { return tipo; }
    public String getClassePermitida() { return classePermitida; }
}