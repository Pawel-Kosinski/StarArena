// src/main/java/org/example/Mikstura.java
package org.example.items;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mikstura extends Przedmiot {
    private final int amount;
    private final boolean isMana;



    @JsonCreator
    public Mikstura(@JsonProperty("name") String name,
                    @JsonProperty("rarity") Rarity rarity,
                    @JsonProperty("amount") int amount,
                    @JsonProperty("isMana") boolean isMana) {
        super(name, rarity);
        this.amount = amount;
        this.isMana = isMana;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isMana() {
        return isMana;
    }

    @Override
    public String toString() {
        if (isMana) {
            return String.format("Mikstura Many: %s, +%d MANA", name, amount);
        } else {
            return String.format("Mikstura Lecznicza: %s, +%d HP", name, amount);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mikstura m)) return false;
        if (!super.equals(o)) return false;
        return amount == m.amount && isMana == m.isMana;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, isMana);
    }
}
