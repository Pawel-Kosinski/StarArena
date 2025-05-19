// src/main/java/org/example/BattleEngine.java
package org.example;

import org.example.loader.AbilityLoader;

import java.util.*;

public class BattleEngine {
    private final AbilityLoader loader;
    private static final int COOLDOWN_TURNS = 3;
    private final Scanner scanner = new Scanner(System.in);

    public BattleEngine(AbilityLoader loader) {
        this.loader = loader;
    }

    public void startBattle(Postac player, Postac enemy) {
        // pobieramy tylko te umiejętności, które deklaruje dana klasa
        List<String> playerAbilities = loader.getAbilitiesFor(player);
        List<String> enemyAbilities  = loader.getAbilitiesFor(enemy);

        Map<String, Integer> playerCD = new HashMap<>();
        Map<String, Integer> enemyCD  = new HashMap<>();
        for (String abl : playerAbilities) playerCD.put(abl, 0);
        for (String abl : enemyAbilities)  enemyCD.put(abl, 0);

        System.out.println("=== Rozpoczyna się walka: " +
                player.getName() + " vs " + enemy.getName() + " ===");

        int turn = 1;
        Random rnd = new Random();

        while (player.czyZyje() && enemy.czyZyje()) {
            System.out.println("\n--- Tura " + turn + " ---");
            decrement(playerCD);
            decrement(enemyCD);

            // ——— Tura gracza ———
            System.out.println(player.getName() + ": wybierz akcję:");
            System.out.println("0) Normalny atak");
            for (int i = 0; i < playerAbilities.size(); i++) {
                String abl = playerAbilities.get(i);
                int cd = playerCD.get(abl);
                System.out.printf("%d) %s %s%n",
                        i + 1,
                        abl,
                        cd > 0 ? "(odnowienie: " + cd + " tur)" : "");
            }
            int choice;
            do {
                System.out.print("Twój wybór: ");
                choice = scanner.nextInt();
            } while (choice < 0 || choice > playerAbilities.size());

            if (choice == 0) {
                player.atakuj(enemy);
            } else {
                String abl = playerAbilities.get(choice - 1);
                if (playerCD.get(abl) == 0) {
                    loader.invoke(abl, player, enemy);
                    playerCD.put(abl, COOLDOWN_TURNS);
                } else {
                    System.out.println("Zdolność w odnowieniu, atakujesz normalnie.");
                    player.atakuj(enemy);
                }
            }
            if (!enemy.czyZyje()) break;

            // ——— Tura wroga (AI) ———
            System.out.println("\n" + enemy.getName() + ": tura AI");
            List<String> enemyReady = new ArrayList<>();
            for (String abl : enemyAbilities) {
                if (enemyCD.get(abl) == 0) enemyReady.add(abl);
            }
            if (!enemyReady.isEmpty() && rnd.nextBoolean()) {
                String abl = enemyReady.get(rnd.nextInt(enemyReady.size()));
                loader.invoke(abl, enemy, player);
                enemyCD.put(abl, COOLDOWN_TURNS);
            } else {
                enemy.atakuj(player);
            }
            if (!player.czyZyje()) break;

            turn++;
        }

        Postac winner = player.czyZyje() ? player : enemy;
        System.out.println("\n=== Zwycięża " + winner.getName() + "! ===");
    }

    private void decrement(Map<String, Integer> cd) {
        cd.replaceAll((ability, v) -> v > 0 ? v - 1 : 0);
    }
}
