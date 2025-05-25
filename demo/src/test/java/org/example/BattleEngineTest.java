package org.example;

import org.example.abilities.AbilityLoader;
import org.example.classes.Postac;
import org.example.classes.Terranin;
import org.example.classes.Zerg;
import org.example.events.BattleEngine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleEngineTest {

    @Test
    public void testBattleAlwaysNormalAttack() {
        // Ładujemy pustą listę zdolności, aby AI i gracz mieli tylko normalny atak
        AbilityLoader dummyLoader = new AbilityLoader() {
            @Override
            public java.util.List<String> getAbilitiesFor(Postac postac) {
                return Collections.emptyList();
            }

            @Override
            public void invoke(String abilityName, Postac user, Postac target) {
                // Nic nie robimy w testach
            }
        };

        // Symulujemy wejście "0" w nieskończoność (tu na tyle, żeby starczyło na turę)
        String simulatedInput = "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);

        BattleEngine engine = new BattleEngine(dummyLoader, scanner);

        Postac player = new Terranin("Player");
        Postac enemy = new Zerg("Enemy");

        engine.startBattle(player, enemy);

        // Po walce dokładnie jedna postać powinna żyć
        boolean playerAlive = player.czyZyje();
        boolean enemyAlive = enemy.czyZyje();
        assertTrue(playerAlive ^ enemyAlive, "Tylko jedna postać powinna żyć po walce");
    }
}
