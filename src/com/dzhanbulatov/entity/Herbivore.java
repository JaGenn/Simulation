package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Herbivore extends Creature {

    public Herbivore(int speed, int HP, Coordinates coordinates) {
        super(speed, HP, coordinates);
    }

    // Травоядное

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }

    @Override
    public void setHP(int HP) {
        super.setHP(HP);
    }

    @Override
    public void makeMove() {

    }

}
