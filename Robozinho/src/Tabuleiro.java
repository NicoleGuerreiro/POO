public class Tabuleiro {
    private int[][] matriz; // 20x20
    private Robo robo;

    public Tabuleiro(int xInicial, int yInicial) {
        matriz = new int[20][20];
        robo = new Robo(xInicial, yInicial);
    }

    public Robo getRobo() {
        return robo;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == robo.getX() && j == robo.getY()) {
                    System.out.print("\uD83D\uDC07"); // posição do robô
                } else if (matriz[i][j] == 1) {
                    System.out.print("\uD83E\uDD55"); // marcado pela caneta
                } else {
                    System.out.print("\uD83C\uDF33"); // vazio
                }
            }
            System.out.println();
        }
    }
}
