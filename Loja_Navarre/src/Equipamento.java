public class Equipamento extends Item {
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    public Equipamento(String nome, double preco, String tipo, String classePermitida,
                       int ataque, int defesa, int magia, int velocidade) {
        super(nome, preco, tipo, classePermitida);
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Status: Ataque+" + ataque + " | Defesa+" + defesa + " | Magia+" + magia + " | Velocidade+" + velocidade);
    }

    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getMagia() { return magia; }
    public int getVelocidade() { return velocidade; }
}