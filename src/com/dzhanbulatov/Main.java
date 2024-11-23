package com.dzhanbulatov;

public class Main {


    public static void main(String[] args) {
        Map map = new Map(10, 10);
        Action action = new Action(map);
        map.setRandomEntitiesPosition();
        //action.initActions();
        while (action.initActions()) {
            if (!action.initActions()) {
                break;
            }
            action.initActions();
        }

    }
}