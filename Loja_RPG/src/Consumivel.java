/** Classe consumível herda de Item
 * Serve para itens que podem ser usados várias vezes (ex: poções)
 */

public class Consumivel extends Item {
    private int doses; /** Número de usos restantes */

    /** Construtor */
    public Consumivel(String nome, float preco, String tipo, int doses){
        /** Chama o construtor da classe mãe (Item) */
        super(nome, preco, tipo);
        this.doses = doses;
    }

    /** Método para usar item */
    public void usar(){
        if (doses > 0){
            doses--;
            System.out.println("Você usou " + getNome() + "! Doses restantes: " + doses);
        } else {
            System.out.println("Não foi possível usar: " + getNome() + ": sem doses restantes!");
        }
    }

    /** Mostra as informações completas do item */
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Doses restantes: " + doses);
    }

    /** Getter */
    public int getDoses(){
        return doses;
    }
}
