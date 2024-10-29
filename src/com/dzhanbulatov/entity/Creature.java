package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public abstract class Creature extends Entity {
    // Существо


    public Coordinates coordinates;
    private int speed;
    private int HP;
    private String name;


    public Creature(int speed, int HP, Coordinates coordinates) {
        this.speed = speed;
        this.HP = HP;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public abstract void makeMove();
}

