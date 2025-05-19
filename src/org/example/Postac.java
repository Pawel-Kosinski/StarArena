// src/main/java/org/example/Postac.java
package org.example;
import java.util.Random;
public abstract class Postac {
    protected String name;
    protected int hp;
    protected int defense;
    protected int minAttack;
    protected int maxAttack;
    protected Random random = new Random();

    public Postac(String name, int hp, int minAttack, int maxAttack, int defense) {
        this.name = name;
        this.hp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.defense = defense;
    }
    public abstract void atakuj(Postac target);

    protected int generateAttackDamage() {
        return minAttack + random.nextInt(maxAttack - minAttack + 1);
    }

    public void obron(int dmg) {
        int damageTaken = Math.max(0, dmg - defense);
        hp -= damageTaken;
        if (damageTaken > 0) {
            System.out.println(name + " otrzymał " + damageTaken + " obrażeń. (" + hp + " HP pozostało)");
        } else {
            System.out.println(name + " uniknął obrażeń!");
        }
    }

    public boolean czyZyje() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }
}