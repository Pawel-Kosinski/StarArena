// src/main/java/org/example/loader/AbilityLoader.java
package org.example.loader;

import org.example.Postac;
import org.example.annotations.SpecjalnaZdolnosc;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AbilityLoader {
    private final Map<String, Method> registry = new HashMap<>();

    public AbilityLoader(Class<?>... classesToScan) {
        for (Class<?> cls : classesToScan) {
            for (Method m : cls.getDeclaredMethods()) {
                if (m.isAnnotationPresent(SpecjalnaZdolnosc.class)) {
                    SpecjalnaZdolnosc ann = m.getAnnotation(SpecjalnaZdolnosc.class);
                    m.setAccessible(true);
                    registry.put(ann.name(), m);
                }
            }
        }
    }

    /** Zwraca wszystkie zarejestrowane nazwy zdolności */
    public Set<String> getAbilityNames() {
        return registry.keySet();
    }

    /** Zwraca listę zdolności dostępnych dla danej instancji (tylko te zadeklarowane w jej klasie) */
    public List<String> getAbilitiesFor(Postac p) {
        return registry.entrySet().stream()
                .filter(e -> e.getValue().getDeclaringClass().equals(p.getClass()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /** Wywołuje zdolność o podanej nazwie na instancji i celu */
    public void invoke(String abilityName, Postac instance, Postac target) {
        Method m = registry.get(abilityName);
        if (m == null) {
            System.out.println("Brak zdolności: " + abilityName);
            return;
        }
        try {
            m.invoke(instance, target);
        } catch (Exception ex) {
            throw new RuntimeException("Błąd wywołania zdolności: " + abilityName, ex);
        }
    }
}
