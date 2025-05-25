package org.example;

import org.example.items.Bron;
import org.example.items.Mikstura;
import org.example.items.Zbroja;
import org.example.items.loader.ItemLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemLoaderTest {

    private ItemLoader itemLoader;

    @BeforeEach
    void setUp() {
        itemLoader = new ItemLoader();
    }

    @Test
    void testLoadWeapons() {
        List<Bron> weapons = itemLoader.loadWeapons();
        assertNotNull(weapons, "Lista broni nie może być null");
        assertFalse(weapons.isEmpty(), "Lista broni nie może być pusta");
        // Możesz dodać więcej asercji, np. sprawdzić konkretne pola pierwszego elementu
        Bron firstWeapon = weapons.get(0);
        assertNotNull(firstWeapon.getName(), "Nazwa broni nie może być null");
        assertTrue(firstWeapon.getAttackModifier() >= 0, "Atak broni musi być >= 0");
    }

    @Test
    void testLoadArmors() {
        List<Zbroja> armors = itemLoader.loadArmors();
        assertNotNull(armors, "Lista zbroi nie może być null");
        assertFalse(armors.isEmpty(), "Lista zbroi nie może być pusta");
        Zbroja firstArmor = armors.get(0);
        assertNotNull(firstArmor.getName(), "Nazwa zbroi nie może być null");
        assertTrue(firstArmor.getDefenseModifier() >= 0, "Obrona zbroi musi być >= 0");
    }

    @Test
    void testLoadPotions() {
        List<Mikstura> potions = itemLoader.loadPotions();
        assertNotNull(potions, "Lista mikstur nie może być null");
        assertFalse(potions.isEmpty(), "Lista mikstur nie może być pusta");
        Mikstura firstPotion = potions.get(0);
        assertNotNull(firstPotion.getName(), "Nazwa mikstury nie może być null");
        assertTrue(firstPotion.getAmount() > 0, "Ilość mikstury musi być większa niż 0");
    }
}
