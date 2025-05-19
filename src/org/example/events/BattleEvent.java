// src/main/java/org/example/events/BattleEvent.java
package org.example.events;

import org.example.Postac;

@FunctionalInterface
public interface BattleEvent {
    /**
     * Apply the event effect to the target character.
     * @param target the character affected by the event
     */
    void apply(Postac target);
}