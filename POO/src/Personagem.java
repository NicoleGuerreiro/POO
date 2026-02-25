public class Personagem {
    String nome;
    Job job;
    int HP_max;
    int HP;
    int nivel;
    Skill skill;

    Personagem(String nome, int hpBase, Job job, Skill skill) {
        this.nome = nome;
        this.job = job;
        this.HP_max = hpBase + job.vigor;
        this.HP = this.HP_max;
        this.nivel = 1;
        this.skill = skill;
    }

    void mostrarFicha() {
        System.out.println("Personagem " + nome);
        System.out.println("Classe: " + job.nome + " Nível " + nivel);
        System.out.println("HP: " + HP + "/" + HP_max);
        System.out.println("Ataque: " + job.ataque);
        System.out.println("Defesa: " + job.defesa);
        System.out.println("Vigor: " + job.vigor);
        System.out.println("Fraqueza: " + job.fraqueza);
        System.out.println("Skill: " + skill.nome + " (Base " + skill.danoBase + ", tipo " + skill.tipo +
                ", reduz defesa em " + (int)(skill.efeitoDefesa * 100) + "%)");
        System.out.println("--------------------");
    }

    void atacar(Personagem alvo) {
        int dano = this.skill.calcularDano(this, alvo);
        alvo.receberDano(dano);
        System.out.println(this.nome + " ataca " + alvo.nome + ": dano = " + dano);
    }

    void receberDano(int dano) {
        this.HP-= dano;
        if (this.HP < 0) {
            this.HP = 0;
            System.out.println(this.nome + " morreu.");
        }
    }
}