package org.example.items;

import java.util.Objects;

public abstract class Przedmiot {
    protected String name;
    protected Rarity rarity;

    public Przedmiot(String name, Rarity rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", name, rarity);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Przedmiot that = (Przedmiot) o;
        return name.equals(that.name) && rarity == that.rarity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rarity);
    }
}