package com.dzhanbulatov;

import java.util.Scanner;

public class Action {

    private Map map;

    public Action(Map map) {
        this.map = map;
    }
    Scanner in = new Scanner(System.in);




    public boolean initActions() {
        Simulation simulation = new Simulation(map);
        System.out.println("1 - make one move\n2 - start endless simulation\n3 - exit");
        int a = in.nextInt();
        if (a == 1) {
            simulation.nextTurn();
        } else if (a == 2) {
            simulation.stopSimulation(); // запускается параллельный поток, который будет ждать команду чтобы остановить бесконечную симуляцию
            System.out.println("Sumulation starts in 4 seconds\nIf you want to stop simulation put \"s\" or \'\'");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulation.startSimulation();
        } else {
            return false;
        }
        return true;
    }

    public void question(){};


}
