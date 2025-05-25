package org.example.EnemySetup;

import org.example.classes.Postac;
import org.example.classes.Protos;
import org.example.items.Bron;
import org.example.items.Mikstura;
import org.example.items.Zbroja;

import java.util.List;
import java.util.Random;

public class EnemySetup {
    private static final Random rand = new Random();

    /** Ustawia ekwipunek AI (broń, zbroja, mikstura) losowo. */
    public static void initializeEnemy(Postac enemy,
                                       List<Bron> weapons,
                                       List<Zbroja> armors,
                                       List<Mikstura> allPotions) {
        if (rand.nextBoolean() && !weapons.isEmpty()) {
            Bron bp = weapons.get(rand.nextInt(weapons.size()));
            enemy.equipWeapon(bp);
        } else {
            System.out.println(enemy.getName() + " bez broni.");
        }

        if (rand.nextBoolean() && !armors.isEmpty()) {
            Zbroja za = armors.get(rand.nextInt(armors.size()));
            enemy.equipArmor(za);
        } else {
            System.out.println(enemy.getName() + " bez zbroi.");
        }

        List<Mikstura> potionsAI = enemy instanceof Protos
                ? allPotions
                : allPotions.stream().filter(p -> !p.isMana()).toList();
        if (!potionsAI.isEmpty() && rand.nextBoolean()) {
            Mikstura pm = potionsAI.get(rand.nextInt(potionsAI.size()));
            enemy.addPotion(pm);
        }

        enemy.showInventory();
    }

    /**
     * Zmienia imię istniejącej postaci i zwraca ją z powrotem.
     */
    public static Postac withName(Postac enemy, String newName) {
        enemy.setName(newName);
        return enemy;
    }
}
