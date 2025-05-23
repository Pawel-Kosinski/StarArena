package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark special ability methods in character org.example.classes.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecjalnaZdolnosc {
    /**
     * Unikalna nazwa zdolności
     */
    String name();
    /**
     * Koszt many lub inny koszt użycia zdolności
     */
    int cost() default 0;
}