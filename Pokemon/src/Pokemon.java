public class Pokemon {
    String especie;
    String tipo;
    int nivel;

    Pokemon(String especie, String tipo, int nivel) {
        this.especie = especie;
        this.tipo = tipo;
        if (nivel < 1) nivel = 1;
        if (nivel > 100) nivel = 100;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return especie + " | Tipo: " + tipo + " | Nível: " + nivel;
    }
}
