package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Predator extends Creature {

    public String getSprite() {
        return "\uD83E\uDD81";
    }

    public Predator(int speed, int HP, Coordinates coordinates) {
        super(speed, HP, coordinates);
    }


    @Override
    public void makeMove() {

    }
}
