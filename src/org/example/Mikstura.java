package org.example;

public class Mikstura extends Przedmiot {
    private int healAmount;

    public Mikstura(String name, Rarity rarity, int healAmount) {
        super(name, rarity);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public String toString() {
        return String.format("Mikstura: %s, Leczy %d HP", super.toString(), healAmount);
    }
}