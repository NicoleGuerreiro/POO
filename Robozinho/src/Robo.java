public class Robo {
    private int x; // linha
    private int y; // coluna
    private boolean canetaAtiva;
    private String direcao; // "NORTE", "SUL", "LESTE", "OESTE"

    public Robo(int xInicial, int yInicial){
        this.x = xInicial;
        this.y = yInicial;
        this.canetaAtiva = false;
        this.direcao = "NORTE"; // começa para cima
    }

    public void ativarCaneta(){ canetaAtiva = true; }
    public void desativarCaneta(){ canetaAtiva = false; }

    public void virarEsquerda(){
        switch (direcao){
            case "NORTE": direcao = "OESTE"; break;
            case "OESTE": direcao = "SUL"; break;
            case "SUL": direcao = "LESTE"; break;
            case "LESTE": direcao = "NORTE"; break;
        }
    }

    public void virarDireita() {
        switch (direcao) {
            case "NORTE": direcao = "LESTE"; break;
            case "LESTE": direcao = "SUL"; break;
            case "SUL": direcao = "OESTE"; break;
            case "OESTE": direcao = "NORTE"; break;
        }
    }

    public void andar(int passos, int[][] tabuleiro) {
        for (int i = 0; i < passos; i++) {
            if (canetaAtiva) {
                tabuleiro[x][y] = 1;
            }
            switch (direcao) {
                case "NORTE": x--; break;
                case "SUL":   x++; break;
                case "LESTE": y++; break;
                case "OESTE": y--; break;
            }
        }
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
}
