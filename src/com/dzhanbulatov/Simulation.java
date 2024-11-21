package com.dzhanbulatov;

import com.dzhanbulatov.entity.Creature;
import com.dzhanbulatov.entity.Entity;
import com.dzhanbulatov.entity.Grass;
import com.dzhanbulatov.entity.Herbivore;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        boolean grassSpawned = false;
        boolean areDead = false;
        while (!isOver) {
            System.out.println();

            Set<Entity> grass = map.getMap().values().stream()
                    .filter(gr -> gr instanceof Grass)
                    .collect(Collectors.toSet());


            for (int i = -1; i <= 1; i++) {

                Set<Entity> herb = getOnlyCreatures().stream()
                        .filter(hr -> hr instanceof Herbivore)
                        .collect(Collectors.toSet());


                for (Entity entity : getOnlyCreatures()) {
                    Creature creature = (Creature) entity;
                    Deque<Coordinates> pathToFood = creature.findWayToFood(map);  // Ищем путь для каждого существа

                    if (!pathToFood.isEmpty()) {
                        creature.makeMove(pathToFood, map); // каждое существо выполняет движение к пище
                        break;
                    } else {
                        creature.changeCoordinates(); // если путь для существа не найден, то оно меняет свое местоположение
                    }
                }

                if (herb.isEmpty()) {
                    System.out.println("All herbivores are eaten, simulation is over\n");
                    areDead = true;
                    break;
                }

                if (grass.size() < 5) {
                    if (!grassSpawned) {
                        for (int g = 0; g < 5; g++) {
                            Coordinates randCoord = map.getRandomCoordinatesOnMap();
                            map.setEntity(randCoord, new Grass(randCoord));
                        }
                        System.out.println("Added 5 grass cells\n");
                        grassSpawned = true;
                    }
                }

                if (herb.size() < map.getMap().size() * 10 / 100) {
                    if (!herbSpawned) {
                        for (int g = 0; g < 3; g++) {
                            Coordinates randCoord = map.getRandomCoordinatesOnMap();
                            map.setEntity(randCoord, new Herbivore(randCoord, map));
                        }
                        System.out.println("Added 3 herbivores\n");
                        herbSpawned = true;
                    }
                }
            }
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


    private Set<Entity> getOnlyCreatures() {
        Set<Entity> creatures = new HashSet<>();
        return creatures = map.getMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .collect(Collectors.toSet());
    }
}
