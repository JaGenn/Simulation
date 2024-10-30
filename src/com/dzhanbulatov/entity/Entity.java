package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public abstract class Entity {

    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract String getSprite();
}
