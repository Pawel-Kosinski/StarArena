// src/main/java/org/example/Mikstura.java
package org.example;

import java.util.Objects;

public class Mikstura extends Przedmiot {
    private final int amount;
    private final boolean isMana;

    public Mikstura(String name, Rarity rarity, int amount, boolean isMana) {
        super(name, rarity);
        this.amount = amount;
        this.isMana = isMana;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isMana() {
        return isMana;
    }

    @Override
    public String toString() {
        if (isMana) {
            return String.format("Mikstura Many: %s, +%d MANA", name, amount);
        } else {
            return String.format("Mikstura Lecznicza: %s, +%d HP", name, amount);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mikstura m)) return false;
        if (!super.equals(o)) return false;
        return amount == m.amount && isMana == m.isMana;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, isMana);
    }
}
