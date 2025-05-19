package org.example;

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
    public void uzyj(Postac target) {
        System.out.println(name + " używa kwasowego kłucia!");
        target.obron(attack + 15);
    }
}