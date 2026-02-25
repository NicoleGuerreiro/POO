public class Consumivel extends Item {
    private int doses;

    public Consumivel(String nome, double preco, String tipo, String classePermitida, int doses) {
        super(nome, preco, tipo, classePermitida);
        this.doses = doses;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Doses restantes: " + doses);
    }

    public boolean usar() {
        if (doses > 0) {
            doses--;
            System.out.println("Usou " + nome + "! Doses restantes: " + doses);
            return true;
        } else {
            System.out.println("Nao foi possivel usar " + nome + ". Acabou as doses!");
            return false;
        }
    }

    public int getDoses() { return doses; }
}