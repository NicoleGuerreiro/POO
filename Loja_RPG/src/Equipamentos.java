/** A classe Equipamento "herda" da classe Item.
 * Isso significa que ela tem tudo o que Item tem (nome, preço, tipo)
 * e pode adicionar novos atributos e comportamentos. */


public class Equipamentos extends Item{
    /** Esses atributos representam como o equipamento
     * afeta o personagem no jogo (valores de combate). */
    private int ataque;
    private int defesa;
    private int magia;
    private int velocidade;

    /** Construtor da classe Equipamento
     * Ele recebe todos os dados de um Item (nome, preço, tipo)
     * mais os atributos específicos do Equipamento. */
    Equipamentos(String nome, float preco, String marca, int ataque, int defesa, int magia, int velocidade){
        /** "super" chama o construtor da classe mãe (Item) */
        super(nome, preco, marca);
        this.ataque = ataque;
        this.defesa = defesa;
        this.magia = magia;
        this.velocidade = velocidade;
    }

    /** Método para exibir todos os dados do equipamento
     * Aqui usamos "super.exibirDados()" pra aproveitar o método da classe mãe */
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Atributos do Equipamento:");
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Magia: " + magia);
        System.out.println("Velocidade: " + velocidade);
    }

    /** Getters (acessam os valores) */
    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getMagia() {
        return magia;
    }

    public int getVelocidade() {
        return velocidade;
    }
}
