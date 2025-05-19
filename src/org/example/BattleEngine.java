package org.example;

import org.example.events.EventEngine;

public class BattleEngine {
    public static void startBattle(Postac p1, Postac p2) {
        System.out.println("=== Rozpoczyna się walka: " + p1.getName() + " vs " + p2.getName() + " ===");
        EventEngine engine1 = new EventEngine();
        EventEngine engine2 = new EventEngine();
        int turn = 1;
        while (p1.czyZyje() && p2.czyZyje()) {
            System.out.println("\n--- Tura " + turn + " ---");
            // Gracz
            System.out.println(p1.getName() + ": Faza zdarzeń");
            engine1.applyRandomEvent(p1);
            System.out.println(p1.getName() + ": Faza ataku");
            p1.atakuj(p2);
            if (!p2.czyZyje()) break;
            // Przeciwnik
            System.out.println(p2.getName() + ": Faza zdarzeń");
            engine2.applyRandomEvent(p2);
            System.out.println(p2.getName() + ": Faza ataku");
            p2.atakuj(p1);
            turn++;
        }
        Postac winner = p1.czyZyje() ? p1 : p2;
        System.out.println("\n=== Zwycięża " + winner.getName() + "! ===");
    }
}