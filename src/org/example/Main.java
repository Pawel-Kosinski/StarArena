// src/main/java/org/example/Main.java
package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1) Przygotuj ekwipunek
        Ekwipunek<Przedmiot> ekw = new Ekwipunek<>();
        Bron plasma = new Bron("Plazmowy Karabin", Rarity.EPIC, 10);
        Zbroja armor   = new Zbroja("Pancerz Obcych", Rarity.RARE, 8);
        Mikstura potion= new Mikstura("Mikstura Zdrowia", Rarity.COMMON, 30);

        ekw.dodaj(plasma);
        ekw.dodaj(armor);
        ekw.dodaj(potion);
        ekw.pokazEkwipunek();

        // 2) Stwórz gracza i wroga
        Postac player = new Terranin("Jim Raynor");
        Postac enemy  = new Zerg("Zergling");

        System.out.println("\n=== Statystyki przed ekwipowaniem ===");
        System.out.printf("%s: HP=%d  ATK=%d  DEF=%d%n",
                player.getName(), player.hp, player.attack, player.defense);

        // 3) Ekwipuj broń i zbroję
        System.out.println("\nEkwipowanie broni i zbroi...");
        player.attack  += plasma.getAttackModifier();
        player.defense += armor.getDefenseModifier();

        System.out.println("=== Statystyki po ekwipowaniu ===");
        System.out.printf("%s: HP=%d  ATK=%d  DEF=%d%n",
                player.getName(), player.hp, player.attack, player.defense);

        // 4) Użyj mikstury, jeśli jest w ekwipunku
        System.out.println("\nUżywanie mikstury...");
        List<Przedmiot> mikstury = ekw.filtruj(item -> item instanceof Mikstura);
        if (!mikstury.isEmpty()) {
            Mikstura m = (Mikstura) mikstury.get(0);
            player.hp += m.getHealAmount();
            ekw.usun(m);
            System.out.println("HP po użyciu mikstury: " + player.hp);
        }

        // 5) Pokaż ekwipunek po użyciu mikstury
        ekw.pokazEkwipunek();

        // 6) Start bitwy
        System.out.println("\n=== Rozpoczynamy bitwę! ===");
        BattleEngine.startBattle(player, enemy);
    }
}
