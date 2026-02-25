public class Main {
    public static void main(String[] args) {
        Skill bolaDeFogo = new Skill("Bola de Fogo", 15, "Fogo", 0.20, 0, 0);
        Skill espadaSagrada = new Skill("Espada Sagrada", 25, "Luz", 0, 15, 40);

        Job mago = new Job("Mago", 30, 20, 10, "Gelo");
        Job paladino = new Job("Paladino", 25, 30, 40, "Fogo");

        Personagem juremildo = new Personagem("Juremildo", 90, mago, bolaDeFogo);
        Personagem aderbal = new Personagem("Aderbal", 110, paladino, espadaSagrada);

        System.out.println("--- STATUS ANTES DO ATAQUE ---");
        juremildo.mostrarFicha();
        aderbal.mostrarFicha();

        System.out.println("Juremildo ataca Aderbal: dano = (15 + 1.2 * 30 - 30) * 1.5 = 31.");
        juremildo.atacar(aderbal);

        System.out.println("\n--- STATUS APÓS O PRIMEIRO ATAQUE ---");
        juremildo.mostrarFicha();
        aderbal.mostrarFicha();

        System.out.println("\n--- SIMULANDO REVANCHE---");
        System.out.println("Aderbal ataca Juremildo: dano = 25 + 1.2 * 25 - 20 = 35.");
        aderbal.atacar(juremildo);

        System.out.println("\n--- STATUS FINAL ---");
        juremildo.mostrarFicha();
        aderbal.mostrarFicha();
    }
}