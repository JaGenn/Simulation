package com.dzhanbulatov;

import com.dzhanbulatov.entity.Creature;
import com.dzhanbulatov.entity.Entity;
import com.dzhanbulatov.entity.Grass;
import com.dzhanbulatov.entity.Herbivore;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class Simulation {
    private Map map;
    private MapConsoleRenderer renderer = new MapConsoleRenderer();
    private boolean isOver = false;


    public Simulation(Map map) {
        this.map = map;
    }

    public void nextTurn() {
        renderer.render(map);
        for (Entity entity : getOnlyCreatures()) {
            Creature creature = (Creature) entity;
            Deque<Coordinates> pathToFood = creature.findWayToFood(map);  // Ищем путь для каждого существа


            if (!pathToFood.isEmpty()) {
                creature.makeMove(pathToFood, map); // если путь найден, двигаем каждое существо к еде
            }
        }
        System.out.println(); // для пустой строки
        renderer.render(map);
    }


    public void startSimulation() {
        boolean herbSpawned = false;
        boolean areDead = false;
        while (!isOver) {
            System.out.println();

            Set<Entity> grass = map.getMap().values().stream()
                    .filter(gr -> gr instanceof Grass)
                    .collect(Collectors.toSet());


            //for (int i = 0; i <= 1 && !isOver; i++) {

                Set<Entity> herb = getOnlyCreatures().stream()
                        .filter(hr -> hr instanceof Herbivore)
                        .collect(Collectors.toSet());


                for (Entity entity : getOnlyCreatures()) {
                    Creature creature = (Creature) entity;
                    Deque<Coordinates> pathToFood = creature.findWayToFood(map);  // Ищем путь для каждого существа
                    if (!pathToFood.isEmpty()) {
                        creature.makeMove(pathToFood, map); // каждое существо выполняет движение к пище
                        break;
                    }
                    else {
                        creature.movesCount++;
                    }
                    if (creature.movesCount > 4) { // Если больше 4 итераций существо не двигается, то оно меняет местоположение
                        creature.changeCoordinates();
                        creature.movesCount = 0;
                    }
                }


                if (herb.isEmpty()) {
                    System.out.println("All herbivores are eaten, simulation is over\n");
                    areDead = true;
                    break;
                }

                if (grass.size() < 5) {
                    for (int g = 0; g < 5; g++) {
                        Coordinates randCoord = map.getRandomCoordinatesOnMap();
                        map.setEntity(randCoord, new Grass(randCoord));
                    }
                    System.out.println("Added 5 grass cells\n");
                }

                if (herb.size() < map.getMap().size() * 10 / 100) {
//                    if (!herbSpawned) {
                        for (int g = 0; g < 3; g++) {
                            Coordinates randCoord = map.getRandomCoordinatesOnMap();
                            map.setEntity(randCoord, new Herbivore(randCoord, map));
                        }
                        System.out.println("Added 3 herbivores\n");
                        herbSpawned = true;
//                    }
                }
            //}
            if (areDead) {
                renderer.render(map);
                break;
            }
            renderer.render(map);

            try {
                Thread.sleep(500);  // Пауза 500 мс между итерациями
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

    }

    public void stopSimulation() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Scanner in = new Scanner(System.in);
                while(true) {
                    String s = in.nextLine();
                    if (s.equalsIgnoreCase("s") || Integer.parseInt(s) == 3) {
                        isOver = true;
                        break;
                    }
                }
                //in.close();
            }
        });
    }


    private Set<Entity> getOnlyCreatures() {
        Set<Entity> creatures = new HashSet<>();
        return creatures = map.getMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .collect(Collectors.toSet());
    }
}
