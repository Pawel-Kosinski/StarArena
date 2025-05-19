package org.example.events;

import org.example.Postac;
import org.example.Protos;
import org.example.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EventEngine {
    private final List<Event> events = new ArrayList<>();
    private final Random random = new Random();

    public EventEngine() {
        events.add(new Event(
                "Pułapka Kolcowa",
                p -> p.czyZyje() && getHp(p) > 20,
                p -> {
                    System.out.println(p.getName() + " trafił na pułapkę kolcową!");
                    p.obron(5);
                }
        ));
        events.add(new Event(
                "Regeneracja Many",
                p -> p instanceof Protos,
                p -> {
                    ((Protos) p).regenerujMana(10);
                    System.out.println(p.getName() + " odzyskał 10 many.");
                }
        ));
        events.add(new Event(
                "Adrenalina",
                p -> getHp(p) < getMaxHp(p) / 2,
                p -> {
                    adjustAttack(p, 5);
                    System.out.println(p.getName() + " odczuwa napływ adrenaliny (+5 ataku)!");
                }
        ));
        events.add(new Event(
                "Regeneracja",
                p -> p.czyZyje() && getHp(p) < getMaxHp(p),
                p -> {
                    heal(p, 3);
                    System.out.println(p.getName() + " regeneruje się (+3 HP)");
                }
        ));

    }


    public void applyRandomEvent(Postac p) {
        List<Event> applicable = events.stream()
                .filter(e -> e.isApplicable(p))
                .collect(Collectors.toList());
        if (applicable.isEmpty()) return;
        Event chosen = applicable.get(random.nextInt(applicable.size()));
        chosen.execute(p);
    }

    // Reflection might be used to fetch/get private fields; here we assume getters exist or fields are protected
    private int getHp(Postac p) {
        try {
            var field = Postac.class.getDeclaredField("hp");
            field.setAccessible(true);
            return field.getInt(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getMaxHp(Postac p) {
        return switch (p.getClass().getSimpleName()) {
            case "Terranin" -> 100;
            case "Zerg" -> 80;
            case "Protos" -> 90;
            default -> getHp(p);
        };
    }

    private void adjustAttack(Postac p, int amount) {
        try {
            var field = Postac.class.getDeclaredField("attack");
            field.setAccessible(true);
            field.setInt(p, field.getInt(p) + amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void heal(Postac p, int amount) {
        try {
            var field = Postac.class.getDeclaredField("hp");
            field.setAccessible(true);
            field.setInt(p, field.getInt(p) + amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}