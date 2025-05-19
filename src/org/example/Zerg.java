package org.example;

import org.example.annotations.SpecjalnaZdolnosc;

public class Zerg extends Postac implements Zdolnosc {
    private int minAbilityDamage;
    private int maxAbilityDamage;

    public Zerg(String name) {
        super(name, 80, 20, 30, 5); // HP=80, atak 20-30, obrona 5
        this.minAbilityDamage = 30;
        this.maxAbilityDamage = 45;
    }

    @Override
    public void atakuj(Postac target) {
        int dmg = generateAttackDamage();
        System.out.println(name + " atakuje paszczy za " + dmg + " pkt. obrażeń!");
        target.obron(dmg);
    }

    @Override
    @SpecjalnaZdolnosc(name = "Kwasowe Kłucie", cost = 0)
    public void uzyj(Postac target) {
        int dmg = minAbilityDamage + random.nextInt(maxAbilityDamage - minAbilityDamage + 1);
        System.out.println(name + " używa kwasowego kłucia za " + dmg + " pkt. obrażeń!");
        target.obron(dmg);
    }
}