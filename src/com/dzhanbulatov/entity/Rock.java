package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDFD4";
    }
    // Скала
}
