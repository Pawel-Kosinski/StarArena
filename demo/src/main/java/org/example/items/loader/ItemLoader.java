package org.example.items.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.items.Bron;
import org.example.items.Zbroja;
import org.example.items.Mikstura;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ItemLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Bron> loadWeapons() {
        return loadListFromResource("items/weapons.json", new TypeReference<List<Bron>>() {});
    }

    public List<Zbroja> loadArmors() {
        return loadListFromResource("items/armors.json", new TypeReference<List<Zbroja>>() {});
    }

    public List<Mikstura> loadPotions() {
        return loadListFromResource("items/potions.json", new TypeReference<List<Mikstura>>() {});
    }


    private <T> List<T> loadListFromResource(String resourcePath, TypeReference<List<T>> type) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Nie znaleziono pliku zasobów: " + resourcePath);
            }
            return mapper.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas wczytywania pliku JSON: " + resourcePath, e);
        }
    }
}
