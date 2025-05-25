package org.example.Campaign;

import org.example.classes.Postac;
import org.example.classes.Protos;
import org.example.classes.Zerg;

import java.util.List;

public class Campaign
{
    public static List<Postac> defaultPath()
    {
        return List.of(
                new Zerg("Zergling"),
                new Zerg("Hydralisk"),
                new Protos("Sarah Kerrigan"));
    }
}
