// src/main/java/org/example/Main.java
package org.example;

import org.example.classes.Postac;
import org.example.classes.Protos;
import org.example.classes.Terranin;
import org.example.classes.Zerg;
import org.example.events.BattleEngine;
import org.example.items.Bron;
import org.example.items.Mikstura;
import org.example.items.Rarity;
import org.example.items.Zbroja;
import org.example.abilities.AbilityLoader;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("W odległym sektorze kosmosu trwa brutalna walka o planetę Xerion — klucz do przyszłości trzech potężnych ras.\n" +
                "\n" +
                "Jako dowódca wybranej frakcji, Twoim zadaniem jest prowadzić elitarne jednostki na arenie walk, by zdobyć przewagę i zapewnić przetrwanie swojego ludu.\n" +
                "\n" +
                "Czy masz odwagę stanąć do walki i przejąć kontrolę nad Xerionem?\n");

        // 1) Przygotuj bazę dostępnych przedmiotów
        List<Bron> weapons = List.of(
                new Bron("Karabin Plazmowy", Rarity.EPIC, 10),
                new Bron("Pistolet Gaussowski", Rarity.RARE, 7)
        );
        List<Zbroja> armors = List.of(
                new Zbroja("Lekki Pancerz", Rarity.COMMON, 5),
                new Zbroja("Ciężka Zbroja", Rarity.RARE, 10)
        );
        List<Mikstura> allPotions = List.of(
                new Mikstura("Mikstura Lecznicza", Rarity.COMMON, 20, false),
                new Mikstura("Mikstura Many",     Rarity.RARE,   30, true)
        );

        // 2) Wybór bohatera
        System.out.println("Wybierz frakcję:");
        System.out.println("1) Terranin");
        System.out.println("2) Zerg");
        System.out.println("3) Protos");
        System.out.print("Twój wybór: ");
        int choice = scanner.nextInt();
        System.out.print("Podaj imię postaci: ");
        String name = scanner.next();

        Postac player = switch (choice) {
            case 2 -> new Zerg(name);
            case 3 -> new Protos(name);
            default -> new Terranin(name);
        };

        // 3) Wybór broni (0 = bez broni)
        System.out.println("\nWybierz broń (0 = brak):");
        System.out.println("0) Brak broni");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.printf("%d) %s%n", i+1, weapons.get(i));
        }
        System.out.print("Twój wybór: ");
        int wChoice = scanner.nextInt();
        if (wChoice > 0 && wChoice <= weapons.size()) {
            player.equipWeapon(weapons.get(wChoice - 1));
        } else {
            System.out.println("Bez broni.");
        }

        // 4) Wybór zbroi (0 = bez zbroi)
        System.out.println("\nWybierz zbroję (0 = brak):");
        System.out.println("0) Brak zbroi");
        for (int i = 0; i < armors.size(); i++) {
            System.out.printf("%d) %s%n", i+1, armors.get(i));
        }
        System.out.print("Twój wybór: ");
        int aChoice = scanner.nextInt();
        if (aChoice > 0 && aChoice <= armors.size()) {
            player.equipArmor(armors.get(aChoice - 1));
        } else {
            System.out.println("Bez zbroi.");
        }

        List<Mikstura> potions = player instanceof Protos
                ? allPotions
                : allPotions.stream().filter(p -> !p.isMana()).toList();

        // 5) Mikstura (jak poprzednio)
        System.out.println("\nMasz dodatkowo miksturę? (tak/nie)");
        if (scanner.next().equalsIgnoreCase("tak")) {
            System.out.println("Wybierz miksturę:");
            for (int i = 0; i < potions.size(); i++) {
                System.out.printf("%d) %s%n", i+1, potions.get(i));
            }
            System.out.print("Twój wybór: ");
            player.addPotion(potions.get(scanner.nextInt() - 1));
        }

        player.showInventory();

        // 6) Ekwipowanie przeciwnika AI
        Postac enemy = new Zerg("AI-Zerg");
        // broń AI: 50% szans na brak
        if (rand.nextBoolean()) {
            Bron bp = weapons.get(rand.nextInt(weapons.size()));
            enemy.equipWeapon(bp);
        } else {
            System.out.println("AI bez broni.");
        }
        // zbroja AI: 50% szans na brak
        if (rand.nextBoolean()) {
            Zbroja za = armors.get(rand.nextInt(armors.size()));
            enemy.equipArmor(za);
        } else {
            System.out.println("AI bez zbroi.");
        }

        // mikstura AI: 50% szans
        List<Mikstura> potionsAI = enemy instanceof Protos
                ? allPotions
                : allPotions.stream().filter(p -> !p.isMana()).toList();

        if (rand.nextBoolean()) {
            Mikstura pm = potionsAI.get(rand.nextInt(potionsAI.size()));
            enemy.addPotion(pm);
        }
        enemy.showInventory();

        // 7) Rozpocznij bitwę
        AbilityLoader loader = new AbilityLoader(
                Terranin.class, Zerg.class, Protos.class
        );
        new BattleEngine(loader).startBattle(player, enemy);
    }
}
