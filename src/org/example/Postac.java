package org.example;

public abstract class Postac {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;

    public Postac(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public abstract void atakuj(Postac target);

    public void obron(int dmg) {
        int damageTaken = Math.max(0, dmg - defense);
        hp -= damageTaken;
        System.out.println(name + " otrzymał " + damageTaken + " obrażeń.");
    }

    public boolean czyZyje() {
        return hp > 0;
    }

    public String getName () {
        return this.name;
    }
}