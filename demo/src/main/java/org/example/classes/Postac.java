package org.example.classes;
import org.example.items.*;

import java.util.Random;
import java.util.List;

public abstract class Postac
{
    protected String name;
    protected int hp;
    protected int defense;
    protected int minAttack;
    protected int maxAttack;
    protected Ekwipunek<Przedmiot> ekwipunek = new Ekwipunek<>();
    protected Random random = new Random();

    public Postac(String name, int hp, int minAttack, int maxAttack, int defense) {
        this.name = name;
        this.hp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.defense = defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void atakuj(Postac target);

    public int getHp() {
        return hp;
    }

    protected int generateAttackDamage() {
        return minAttack + random.nextInt(maxAttack - minAttack + 1);
    }

    public void obron(int dmg)
    {
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

    public void dodajAtak(int amount) {
        minAttack += amount;
        maxAttack += amount;
        System.out.println(name + " zyskuje +" + amount + " do obrażeń! Nowy zakres ataku: "
                + minAttack + "-" + maxAttack);
    }

    public void equipWeapon(Bron weapon) {
        ekwipunek.dodaj(weapon);
        minAttack += weapon.getAttackModifier();
        maxAttack += weapon.getAttackModifier();
        System.out.println(name + " ekwipuje broń: " + weapon + ". Nowy zakres ataku: " + minAttack + "-" + maxAttack);
    }

    public void equipArmor(Zbroja armor) {
        ekwipunek.dodaj(armor);
        defense += armor.getDefenseModifier();
        System.out.println(name + " ekwipuje zbroję: " + armor + ". Nowa obrona: " + defense);
    }

    public void addPotion(Mikstura potion) {
        ekwipunek.dodaj(potion);
    }

    public void showInventory() {
        ekwipunek.pokazEkwipunek();
    }
    public List<Mikstura> getPotions() {
        return ekwipunek.filtruj(item -> item instanceof Mikstura)
                .stream()
                .map(item -> (Mikstura)item)
                .toList();
    }

    // używa mikstury i od razu ją usuwa z ekwipunku
    public void usePotion(Mikstura pot) {
        if (!ekwipunek.usun(pot))
        {
            System.out.println("Nie masz takiej mikstury!");
            return;
        }

        if (pot.isMana() && this instanceof Protos)
        {
            ((Protos)this).regenerujMana(pot.getAmount());
        }
        else if (!pot.isMana())
        {
            hp += pot.getAmount();
            System.out.println(name + " odzyskał " + pot.getAmount() + " HP. (" + hp + " HP)");
        }
    }
}