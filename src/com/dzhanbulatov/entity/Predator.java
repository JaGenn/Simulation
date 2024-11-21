package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;
import com.dzhanbulatov.Map;

import java.util.Set;

public class Predator extends Creature {


    public String getSprite() {
        return "\uD83E\uDD81";
    }

    public Predator(Coordinates coordinates, Map map) {
        super(coordinates, map);
        super.speed = 1;
        super.HP = 20;
    }



    @Override
    public boolean canEat(Coordinates other) {
        return map.getEntity(other) instanceof Herbivore;
    }

}
