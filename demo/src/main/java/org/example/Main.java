package org.example;

import org.example.InputUtils.InputUtils;
import org.example.classes.Postac;
import org.example.classes.Protos;
import org.example.classes.Terranin;
import org.example.classes.Zerg;
import org.example.events.BattleEngine;
import org.example.items.Bron;
import org.example.items.Mikstura;
import org.example.items.Zbroja;
import org.example.abilities.AbilityLoader;
import org.example.items.loader.ItemLoader;
import org.example.EnemySetup.EnemySetup;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("W odległym sektorze kosmosu trwa brutalna walka o planetę Xerion — klucz do przyszłości trzech potężnych ras.\n" +
                "\n" +
                "Jako dowódca wybranej frakcji, Twoim zadaniem jest prowadzić elitarne jednostki na arenie walk, by zdobyć przewagę i zapewnić przetrwanie swojego ludu.\n" +
                "\n" +
                "Czy masz odwagę stanąć do walki i przejąć kontrolę nad Xerionem?\n");

        ItemLoader itemLoader = new ItemLoader();

        List<Bron> weapons = itemLoader.loadWeapons();
        List<Zbroja> armors = itemLoader.loadArmors();
        List<Mikstura> allPotions = itemLoader.loadPotions();


        // 2) Wybór bohatera
        Postac player = InputUtils.wybierzPostac(scanner);

        // 3) Wybór broni (0 = bez broni)
        Bron chosenWeapon = InputUtils.wybierzBron(scanner, weapons);
        if (chosenWeapon != null) {
            player.equipWeapon(chosenWeapon);
        }

        // 4) Wybór zbroi (0 = bez zbroi)
        Zbroja chosenArmor = InputUtils.wybierzZbroje(scanner, armors);
        if (chosenArmor != null) {
            player.equipArmor(chosenArmor);
        }

        List<Mikstura> potions = player instanceof Protos
                ? allPotions
                : allPotions.stream().filter(p -> !p.isMana()).toList();

        // 5) Mikstura (jak poprzednio)
        Mikstura chosenPotion = InputUtils.wybierzMiksture(scanner, potions);
        if (chosenPotion != null) {
            player.addPotion(chosenPotion);
        }

        player.showInventory();

        // 6) Ekwipowanie przeciwnika AI
        Postac enemy = new Zerg("AI-Zerg");
        EnemySetup.initializeEnemy(enemy, weapons, armors, allPotions);

        // 7) Rozpocznij bitwę
        AbilityLoader loader = new AbilityLoader(
                Terranin.class, Zerg.class, Protos.class
        );
        new BattleEngine(loader).startBattle(player, enemy);
    }
}
