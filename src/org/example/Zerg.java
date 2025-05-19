package org.example;

import org.example.annotations.SpecjalnaZdolnosc;

public class Zerg extends Postac implements Zdolnosc {
    public Zerg(String name) {
        super(name, 80, 20, 5);
    }

    @Override
    public void atakuj(Postac target) {
        System.out.println(name + " atakuje paszczy!");
        target.obron(attack);
    }

    @Override
    @SpecjalnaZdolnosc(name = "Kwasowe Kłucie", cost = 0)
    public void uzyj(Postac target) {
        System.out.println(name + " używa kwasowego kłucia!");
        target.obron(attack + 15);
    }
}