package org.example;

public class Bron extends Przedmiot {
    private int attackModifier;

    public Bron(String name, Rarity rarity, int attackModifier) {
        super(name, rarity);
        this.attackModifier = attackModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    @Override
    public String toString() {
        return String.format("Bron: %s, +%d Ataku", super.toString(), attackModifier);
    }
}