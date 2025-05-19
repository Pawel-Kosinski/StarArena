package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz frakcję:");
        System.out.println("1) Terranin");
        System.out.println("2) Zerg");
        System.out.println("3) Protos");
        System.out.print("Twój wybór: ");
        int choice = scanner.nextInt();
        System.out.print("Podaj imię postaci: ");
        String name = scanner.next();

        Postac player = switch (choice) {
            case 1 -> new Terranin(name);
            case 2 -> new Zerg(name);
            case 3 -> new Protos(name);
            default -> {
                System.out.println("Niepoprawny wybór, domyślnie Terranin");
                yield new Terranin(name);
            }
        };

        // Generujemy przeciwnika losowo
        Random rand = new Random();
        int oppChoice = rand.nextInt(3) + 1;
        Postac enemy = (oppChoice == 1) ? new Terranin("AI-Terran") : (oppChoice == 2) ? new Zerg("AI-Zerg") : new Protos("AI-Protos");
        System.out.println("Twój przeciwnik to: " + enemy.getName() + " (" + enemy.getClass().getSimpleName() + ")");

        // Start bitwy
        BattleEngine.startBattle(player, enemy);
        scanner.close();
    }
}