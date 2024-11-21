package com.dzhanbulatov;

import java.util.Scanner;

public class Action {

    private Map map;

    public Action(Map map) {
        this.map = map;
    }



    Scanner in = new Scanner(System.in);




    public void initActions() {
        Simulation simulation = new Simulation(map);
        System.out.println("Введите 1 чтобы сделать 1 ход\nВведите 2 чтобы запустить бесконечный цикл симуляции");
        int a = in.nextInt();
        if (a == 1) {
            simulation.nextTurn();
        } else if (a == 2) {
            System.out.println("If you want to stop simulation, put \"s\"");
            simulation.startSimulation();
        }
    }

    public void question(){};

    public void addHerbivores() {

    }

    public void addGrass() {

    }
}
