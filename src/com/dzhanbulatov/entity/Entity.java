package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Entity {

    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getSprite() {
        return "  ";
    }


}
