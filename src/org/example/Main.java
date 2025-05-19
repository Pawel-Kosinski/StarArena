// src/main/java/org/example/Main.java
package org.example;

import org.example.loader.AbilityLoader;

public class Main {
    public static void main(String[] args) {
        // Inicjalizacja loadera zdolności
        AbilityLoader loader = new AbilityLoader(
                Terranin.class,
                Zerg.class,
                Protos.class
        );

        // Tworzymy gracza i AI
        Postac player = new Terranin("Jim Raynor");
        Postac enemy  = new Zerg("Zergling");

        // Start gry
        BattleEngine engine = new BattleEngine(loader);
        engine.startBattle(player, enemy);
    }
}
