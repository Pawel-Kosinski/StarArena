package org.example;

import org.example.events.EventEngine;
import org.example.loader.AbilityLoader;

import java.util.*;

public class BattleEngine {
    private final AbilityLoader loader;
    private final EventEngine eventEngine;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private static final int COOLDOWN_TURNS = 3;
    private static final double EVENT_PROBABILITY = 0.5; // 30% szans na zdarzenie


    public BattleEngine(AbilityLoader loader) {
        this.loader = loader;
        this.eventEngine = new EventEngine();
    }

    public void startBattle(Postac player, Postac enemy) {
        List<String> playerAbilities = loader.getAbilitiesFor(player);
        List<String> enemyAbilities  = loader.getAbilitiesFor(enemy);

        Map<String, Integer> playerCD = new HashMap<>();
        Map<String, Integer> enemyCD  = new HashMap<>();
        playerAbilities.forEach(abl -> playerCD.put(abl, 0));
        enemyAbilities.forEach(abl -> enemyCD.put(abl, 0));

        System.out.println("=== Rozpoczyna się walka: "
                + player.getName() + " vs " + enemy.getName() + " ===");

        int turn = 1;
        while (player.czyZyje() && enemy.czyZyje()) {
            List<Mikstura> potions = player.getPotions();
            System.out.println("\n--- Tura " + turn + " ---");
            decrement(playerCD);
            decrement(enemyCD);

            // Faza zdarzeń gracza
            if (random.nextDouble() < EVENT_PROBABILITY) {
                eventEngine.applyRandomEvent(player);
            }


            // Faza akcji gracza
            System.out.println(player.getName() + ": Faza akcji");
            System.out.println("0) Normalny atak");
            for (int i = 0; i < playerAbilities.size(); i++) {
                String abl = playerAbilities.get(i);
                int cd = playerCD.get(abl);
                System.out.printf("%d) %s %s\n",
                        i+1,
                        abl,
                        cd>0 ? "(odnowienie: " + cd + ")" : "");
            }
            int choice;
            int base = playerAbilities.size() + 1;
            for (int i = 0; i < potions.size(); i++) {
                Mikstura pot = potions.get(i);
                System.out.printf("%d) Użyj mikstury: %s%n", base + i, pot);
            }

            do {
                System.out.print("Twój wybór: ");
                choice = scanner.nextInt();
            } while (choice < 0 || choice > playerAbilities.size() + potions.size());

            // obsługa wyboru
            if (choice == 0) {
                player.atakuj(enemy);

            } else if (choice <= playerAbilities.size()) {
                String abl = playerAbilities.get(choice - 1);
                if (playerCD.get(abl) == 0) {
                    loader.invoke(abl, player, enemy);
                    playerCD.put(abl, COOLDOWN_TURNS);
                } else {
                    System.out.println("Zdolność w odnowieniu – normalny atak.");
                    player.atakuj(enemy);
                }

            } else {
                // użycie mikstury
                Mikstura pot = potions.get(choice - base);
                player.usePotion(pot);
            }
            if (!enemy.czyZyje()) break;

            // Faza zdarzeń wroga
            if (random.nextDouble() < EVENT_PROBABILITY) {
                eventEngine.applyRandomEvent(enemy);
            }

            // Faza akcji wroga (AI)
            System.out.println(enemy.getName() + ": Faza AI");
            List<String> ready = new ArrayList<>();
            for (String abl : enemyAbilities) {
                if (enemyCD.get(abl) == 0) ready.add(abl);
            }
            List<Mikstura> aiPots = enemy.getPotions();
            if (!aiPots.isEmpty() && enemy.hp < 40 && random.nextBoolean()) {
                enemy.usePotion(aiPots.get(0));
            } else {
                boolean useAbility = !ready.isEmpty() && random.nextBoolean();
                if (useAbility) {
                    String abl = ready.get(random.nextInt(ready.size()));
                    loader.invoke(abl, enemy, player);
                    enemyCD.put(abl, COOLDOWN_TURNS);
                } else {
                    enemy.atakuj(player);
                }
                if (!player.czyZyje()) break;
            }

            turn++;
        }
        Postac winner = player.czyZyje() ? player : enemy;
        System.out.println("\n=== Zwycięża " + winner.getName() + "! ===");
    }

    private void decrement(Map<String, Integer> cds) {
        cds.replaceAll((k, v) -> v>0 ? v-1 : 0);
    }
}