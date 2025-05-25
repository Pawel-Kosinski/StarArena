package org.example;

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
import org.example.Campaign.Campaign;      // nowy pakiet z domyślną ścieżką
import org.example.InputUtils.InputUtils;      // pakiet z helperami
import org.example.EnemySetup.EnemySetup;      // mój wcześniejszy przykład

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Wstęp fabularny
        System.out.println("W odległym sektorze kosmosu trwa brutalna walka o planetę Xerion — klucz do przyszłości trzech potężnych ras.\n");
        System.out.println("Jako dowódca wybranej frakcji, Twoim zadaniem jest prowadzić elitarne jednostki na arenie walk, by zdobyć przewagę i zapewnić przetrwanie swojego ludu.\n");
        System.out.println("Czy masz odwagę stanąć do walki i przejąć kontrolę nad Xerionem?\n");

        // Wczytujemy dane z JSON
        ItemLoader itemLoader = new ItemLoader();
        List<Bron> weapons     = itemLoader.loadWeapons();
        List<Zbroja> armors    = itemLoader.loadArmors();
        List<Mikstura> allPotions = itemLoader.loadPotions();

        // 1) Wybór bohatera
        Postac player = InputUtils.wybierzPostac(scanner);

        // 2) Ekwipowanie gracza
        Bron chosenWeapon = InputUtils.wybierzBron(scanner, weapons);
        if (chosenWeapon != null) player.equipWeapon(chosenWeapon);

        Zbroja chosenArmor = InputUtils.wybierzZbroje(scanner, armors);
        if (chosenArmor != null) player.equipArmor(chosenArmor);

        List<Mikstura> potions = player instanceof Protos
                ? allPotions
                : allPotions.stream().filter(p -> !p.isMana()).toList();
        Mikstura chosenPotion = InputUtils.wybierzMiksture(scanner, potions);
        if (chosenPotion != null) player.addPotion(chosenPotion);

        player.showInventory();

        // 3) Ścieżka kampanii: trzy starcia
        List<Postac> path = Campaign.defaultPath();
        AbilityLoader loader = new AbilityLoader(
                Terranin.class, Zerg.class, Protos.class
        );

        for (int stage = 0; stage < path.size(); stage++) {
            Postac enemy = path.get(stage);
            // nadajemy wrogowi pasującą nazwę
            enemy = EnemySetup.withName(enemy, "AI-" + enemy.getName());

            System.out.printf("\n=== Starcie %d: %s ===\n", stage + 1, enemy.getName());
            new BattleEngine(loader, scanner).startBattle(player, enemy);

            if (!player.czyZyje()) {
                System.out.println("\nTwoja armia padła. Koniec kampanii.");
                scanner.close();
                return;
            }
            System.out.println("\nZwycięstwo! Przygotuj się na kolejne starcie.");
            // opcjonalnie: heal/reset cooldowns
        }

        System.out.println("\nGratulacje! Ukończyłeś kampanię!");
        scanner.close();
    }
}
