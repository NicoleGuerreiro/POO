/** A classe Arma herda de Equipamento.
 * Assim, ela tem todos os atributos de Item (nome, preço, tipo)
 * e também os atributos de Equipamento (ataque, defesa, magia, velocidade). */

public class Arma extends Equipamentos{
    /** Novo atributo específico de Arma */
    int dano;

    /** Construtor da classe Arma.
     * Ele precisa receber todos os parâmetros necessários
     * para construir um Equipamento (nome, preço, tipo, ataque, defesa, magia, velocidade)
     * e o novo atributo dano.
     */
    Arma(String nome, float preco, String marca, int ataque, int defesa, int magia, int velocidade, int dano){

        /** Chamamos o construtor da classe mãe (Equipamento)
         * para inicializar tudo que é herdado. */
        super(nome,preco,marca,ataque,defesa,magia,velocidade);
        this.dano = dano;
    }

    /** Método de */
    public int calcularDano() {
        return dano;
    }

    /** Método para exibir as informações completas da arma.
       Reaproveita o método da superclasse e só adiciona a parte nova.*/
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Dano: " + dano);
    }

    /** Getter do atributo dano. */
    public int getDano(){
        return dano;
    }

    /** Setter do atributo dano. */
    public void setDanoBase(int dano){
        this.dano = dano;
    }

}
