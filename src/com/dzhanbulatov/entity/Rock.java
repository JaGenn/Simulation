package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "⛰\uFE0F";
        //return "r";
    }
    // Скала
}
