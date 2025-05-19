package org.example;

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
}