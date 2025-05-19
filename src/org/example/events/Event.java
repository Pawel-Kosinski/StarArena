package org.example.events;

import org.example.Postac;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Event {
    private final String name;
    private final Predicate<Postac> condition;
    private final Consumer<Postac> action;

    public Event(String name, Predicate<Postac> condition, Consumer<Postac> action) {
        this.name = name;
        this.condition = condition;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public boolean isApplicable(Postac p) {
        return condition.test(p);
    }

    public void execute(Postac p) {
        action.accept(p);
        System.out.println("[Event: " + name + "] applied to " + p.getName());
    }
}
