package org.example.InputUtils;

import org.example.classes.Postac;
import org.example.classes.Protos;
import org.example.classes.Terranin;
import org.example.classes.Zerg;
import org.example.items.Bron;
import org.example.items.Mikstura;
import org.example.items.Zbroja;

import java.util.List;
import java.util.Scanner;

public class InputUtils
{
    public static Postac wybierzPostac(Scanner scanner) {
        int choice;
        String name;

        while (true) {
            System.out.println("Wybierz frakcję:");
            System.out.println("1) Terranin");
            System.out.println("2) Zerg");
            System.out.println("3) Protos");
            System.out.print("Twój wybór: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    scanner.nextLine(); // konsumujemy znak nowej linii po nextInt()
                    System.out.print("Podaj imię postaci: ");
                    name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        name = "Bezimienny";
                    }
                    break;
                }
            } else {
                scanner.next(); // odrzucamy niepoprawny input
            }
            System.out.println("Niepoprawna opcja, spróbuj jeszcze raz.\n");
        }

        return switch (choice) {
            case 2 -> new Zerg(name);
            case 3 -> new Protos(name);
            default -> new Terranin(name);
        };
    }



    public static Bron wybierzBron(Scanner scanner, List<Bron> weapons) {
        int choice;
        while (true) {
            System.out.println("\nWybierz broń (0 = brak):");
            System.out.println("0) Brak broni");
            for (int i = 0; i < weapons.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, weapons.get(i));
            }
            System.out.print("Twój wybór: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= weapons.size()) {
                    break;
                }
            } else {
                scanner.next(); // odrzucamy niepoprawny token
            }
            System.out.println("Niepoprawna opcja, spróbuj jeszcze raz.");
        }

        if (choice == 0) {
            System.out.println("Bez broni.");
            return null;
        } else {
            return weapons.get(choice - 1);
        }
    }


    public static Zbroja wybierzZbroje(Scanner scanner, List<Zbroja> armors) {
        int choice;
        while (true) {
            System.out.println("\nWybierz zbroję (0 = brak):");
            System.out.println("0) Brak zbroi");
            for (int i = 0; i < armors.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, armors.get(i));
            }
            System.out.print("Twój wybór: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= armors.size()) {
                    break;
                }
            } else {
                scanner.next(); // odrzucamy niepoprawny token
            }
            System.out.println("Niepoprawna opcja, spróbuj jeszcze raz.");
        }

        if (choice == 0) {
            System.out.println("Bez zbroi.");
            return null;
        } else {
            return armors.get(choice - 1);
        }
    }


    public static Mikstura wybierzMiksture(Scanner scanner, List<Mikstura> potions) {
        String answer;
        while (true) {
            System.out.println("\nMasz dodatkowo miksturę? (tak/nie)");
            answer = scanner.next().trim();
            if (answer.equalsIgnoreCase("tak") || answer.equalsIgnoreCase("nie")) {
                break;
            } else {
                System.out.println("Proszę odpowiedzieć 'tak' lub 'nie'.");
            }
        }

        if (!answer.equalsIgnoreCase("tak")) {
            System.out.println("Bez mikstury.");
            return null;
        }

        int choice;
        while (true) {
            System.out.println("Wybierz miksturę:");
            for (int i = 0; i < potions.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, potions.get(i));
            }
            System.out.print("Twój wybór: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= potions.size()) {
                    break;
                }
            } else {
                scanner.next(); // odrzucamy niepoprawny token
            }
            System.out.println("Niepoprawna opcja, spróbuj jeszcze raz.");
        }

        return potions.get(choice - 1);
    }

}
