package org.example;

import org.example.annotations.SpecjalnaZdolnosc;

public class Protos extends Postac implements Zdolnosc {
    private int mana;
    private int minAbilityDamage;
    private int maxAbilityDamage;

    public Protos(String name) {
        super(name, 90, 18, 28, 8); // HP=90, atak 18-28, obrona 8
        this.mana = 50;
        this.minAbilityDamage = 35;
        this.maxAbilityDamage = 50;
    }

    @Override
    public void atakuj(Postac target) {
        int dmg = generateAttackDamage();
        System.out.println(name + " atakuje plazmową bronią za " + dmg + " pkt. obrażeń!");
        target.obron(dmg);
    }

    @Override
    @SpecjalnaZdolnosc(name = "Psychiczny Cios", cost = 20)
    public void uzyj(Postac target) {
        if (mana >= 20) {
            int dmg = minAbilityDamage + random.nextInt(maxAbilityDamage - minAbilityDamage + 1);
            System.out.println(name + " używa Psychicznego Ciosu za " + dmg + " pkt. obrażeń!");
            target.obron(dmg);
            mana -= 20;
        } else {
            System.out.println(name + " nie ma wystarczająco many!");
        }
    }

    public void regenerujMana(int amount) {
        mana += amount;
        System.out.println(name + " odzyskuje " + amount + " many. (" + mana + "/50)");
    }
}