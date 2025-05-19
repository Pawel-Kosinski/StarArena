package org.example;

public class Protos extends Postac implements Zdolnosc {
    private int mana;

    public Protos(String name) {
        super(name, 90, 18, 8);
        this.mana = 50;
    }

    @Override
    public void atakuj(Postac target) {
        System.out.println(name + " atakuje plazmową bronią!");
        target.obron(attack);
    }

    @Override
    public void uzyj(Postac target) {
        if (mana >= 20) {
            System.out.println(name + " używa psionic lash!");
            target.obron(attack + 20);
            mana -= 20;
        } else {
            System.out.println(name + " nie ma wystarczająco many!");
        }
    }

    public void regenerujMana(int amount) {
        mana += amount;
    }
}