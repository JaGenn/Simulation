package com.dzhanbulatov;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Map map = new Map(10, 10);
        Action action = new Action(map);
        Simulation simulation = new Simulation(map);
        map.defaultEntitiesPositions();
        simulation.startSimulation();

    }
}