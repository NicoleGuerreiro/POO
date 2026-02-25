public class Skill {
    String nome;
    int danoBase;
    String tipo;
    double efeitoDefesa;
    int efeitoCura;
    int efeitoFuria;

    Skill(String nome, int danoBase, String tipo, double efeitoDefesa, int efeitoCura, int efeitoFuria) {
        this.nome = nome;
        this.danoBase = danoBase;
        this.tipo = tipo;
        this.efeitoDefesa = efeitoDefesa;
        this.efeitoCura = efeitoCura;
        this.efeitoFuria = efeitoFuria;
    }

    public int calcularDano(Personagem atacante, Personagem alvo) {
        double dano = danoBase + (1.2 * atacante.job.ataque) - alvo.job.defesa;

        if (atacante.HP <= efeitoFuria && efeitoFuria > 0) {
            dano *= 2;
        }

        if (this.tipo.equals(alvo.job.fraqueza)) {
            dano *= 1.5;
        }

        if (dano < 0) {
            dano = 0;
        }

        if (efeitoDefesa > 0) {
            int reducaoDefesa = (int) (alvo.job.defesa * this.efeitoDefesa);
            alvo.job.defesa -= reducaoDefesa;
            System.out.println("Defesa de " + alvo.nome + " cai para " + alvo.job.defesa + ".");
        }

        if (efeitoCura > 0) {
            atacante.HP += this.efeitoCura;
            if (atacante.HP > atacante.HP_max) {
                atacante.HP = atacante.HP_max;
            }
            System.out.println(atacante.nome + " cura " + this.efeitoCura + " HP.");
        }
        return (int) dano;
    }
}