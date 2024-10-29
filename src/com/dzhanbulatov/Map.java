package com.dzhanbulatov;

import com.dzhanbulatov.entity.Creature;
import com.dzhanbulatov.entity.Herbivore;
import com.dzhanbulatov.entity.Predator;

import java.util.HashMap;

public class Map {

    HashMap<Coordinates, Creature> creaturesMap = new HashMap<>();

    public void setCreature(Coordinates coordinates, Creature creature) {
        creature.coordinates = coordinates;
        creaturesMap.put(coordinates, creature);
    }

    public void defaultCreaturesPositions() {
        for (int i = 0; i< Main.X; i++) {
            setCreature(new Coordinates(i, 2), new Herbivore(1, 15, new Coordinates(i, 2)));
            setCreature(new Coordinates(i, 7), new Predator(1, 15, new Coordinates(i, 7)));
        }
    }
}
