package org.example.items;

public class Zbroja extends Przedmiot {
    private int defenseModifier;

    public Zbroja(String name, Rarity rarity, int defenseModifier) {
        super(name, rarity);
        this.defenseModifier = defenseModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    @Override
    public String toString() {
        return String.format("Zbroja: %s, +%d Obrony", super.toString(), defenseModifier);
    }
}