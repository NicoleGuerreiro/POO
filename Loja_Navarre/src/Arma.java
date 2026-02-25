public class Arma extends Equipamento {
    private String elemento;

    public Arma(String nome, double preco, String tipo, String classePermitida,
                int ataque, int defesa, int magia, int velocidade, String elemento) {
        super(nome, preco, tipo, classePermitida, ataque, defesa, magia, velocidade);
        this.elemento = elemento;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Elemento: " + elemento);
    }
}