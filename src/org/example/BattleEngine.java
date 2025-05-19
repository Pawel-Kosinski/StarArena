package org.example;

public class BattleEngine {
    public static void startBattle(Postac p1, Postac p2) {
        System.out.println("=== Rozpoczyna się walka: " + p1.getName() + " vs " + p2.getName() + " ===");
        int turn = 1;
        while (p1.czyZyje() && p2.czyZyje()) {
            System.out.println("--- Tura " + turn + " ---");
            p1.atakuj(p2);
            if (!p2.czyZyje()) {
                System.out.println(p2.getName() + " zginął!");
                break;
            }
            p2.atakuj(p1);
            if (!p1.czyZyje()) {
                System.out.println(p1.getName() + " zginął!");
                break;
            }
            turn++;
        }
        Postac winner = p1.czyZyje() ? p1 : p2;
        System.out.println("=== Zwycięża " + winner.getName() + "! ===");
    }
}