package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Grass extends Entity {

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF40";
    }
    // Трава
}
