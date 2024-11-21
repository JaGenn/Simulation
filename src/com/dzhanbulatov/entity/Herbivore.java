package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;
import com.dzhanbulatov.Map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Herbivore extends Creature {


    @Override
    public String getSprite() {
        return "\uD83D\uDC0F";
        // Травоядное
    }



    public Herbivore(Coordinates coordinates, Map map) {
        super(coordinates, map);
        super.speed = 1;
        super.HP = 50;
    }

    @Override
    protected boolean canEat(Coordinates other) {
        return map.getEntity(other) instanceof Grass;
    }
}
