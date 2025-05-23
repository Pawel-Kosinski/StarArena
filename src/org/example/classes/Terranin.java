package org.example.classes;

import org.example.abilities.Zdolnosc;
import org.example.annotations.SpecjalnaZdolnosc;

public class Terranin extends Postac implements Zdolnosc {
    private int minAbilityDamage;
    private int maxAbilityDamage;

    public Terranin(String name) {
        super(name, 100, 15, 25, 10); // HP=100, atak 15-25, obrona 10
        this.minAbilityDamage = 25;
        this.maxAbilityDamage = 35;
    }

    @Override
    public void atakuj(Postac target) {
        int dmg = generateAttackDamage();
        System.out.println(name + " atakuje z broni palnej za " + dmg + " pkt. obrażeń!");
        target.obron(dmg);
    }

    @Override
    @SpecjalnaZdolnosc(name = "Granat Fragmentacyjny", cost = 0)
    public void uzyj(Postac target) {
        int dmg = minAbilityDamage + random.nextInt(maxAbilityDamage - minAbilityDamage + 1);
        System.out.println(name + " używa granatu fragmentacyjnego za " + dmg + " pkt. obrażeń!");
        target.obron(dmg);
    }
}