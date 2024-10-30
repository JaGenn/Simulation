package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF31";
    }


    // Дерево
}
