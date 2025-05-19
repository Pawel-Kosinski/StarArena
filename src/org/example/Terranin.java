package org.example;

public class Terranin extends Postac implements Zdolnosc {
    public Terranin(String name) {
        super(name, 100, 15, 10);
    }

    @Override
    public void atakuj(Postac target) {
        System.out.println(name + " atakuje z broni palnej!");
        target.obron(attack);
    }

    @Override
    public void uzyj(Postac target) {
        System.out.println(name + " używa granatu frag!");
        target.obron(attack + 10);
    }
}