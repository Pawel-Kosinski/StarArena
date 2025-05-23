package org.example.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Ekwipunek<T extends Przedmiot> {
    private final List<T> items = new ArrayList<>();

    public void dodaj(T item) {
        items.add(item);
        System.out.println("Dodano do ekwipunku: " + item);
    }

    public boolean usun(T item) {
        boolean removed = items.remove(item);
        if (removed) {
            System.out.println("Usunięto z ekwipunku: " + item);
        }
        return removed;
    }

    public List<T> filtruj(Predicate<T> warunek) {
        return items.stream().filter(warunek).toList();
    }

    public List<T> wszystkie() {
        return Collections.unmodifiableList(items);
    }

    public void pokazEkwipunek() {
        System.out.println("--- Ekwipunek: ---");
        items.forEach(i -> System.out.println(" * " + i));
    }
}