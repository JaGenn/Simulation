package com.dzhanbulatov;

import com.dzhanbulatov.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Map {
    private static int X;
    private static int Y;

    public Map(int x, int y) {
        X = x;
        Y = y;
    }

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public HashMap<Coordinates, Entity> getMap() {
        return entitiesMap;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        if (entity != null) {
            entity.coordinates = coordinates;
            entitiesMap.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entitiesMap.remove(coordinates);
    }

    private Entity setRandomEntity(Coordinates coordinates) {
        List<Entity> entities = new ArrayList<>();
        Entity Grass = null,
                Rock = null,
                Tree = null,
                Predator = null,
                Herbivore = null;
        entities.add(Grass);
        entities.add(Rock);
        entities.add(Tree);
        entities.add(Predator);
        entities.add(Herbivore);
        int random = (int) (Math.random() * (entities.size() + 3));
        switch (random) {
            case 0:
                return new Rock(coordinates);
            case 1:
                return new Grass(coordinates);
            case 2:
                return new Tree(coordinates);
            case 3:
                return new Herbivore(coordinates, this);
            case 4:
                return new Predator(coordinates, this);
            default:
                return new Entity(coordinates);
        }

    }

    public Coordinates getRandomCoordinatesOnMap() {
        Coordinates randomCoord;
        while (true) {
            int x = (int) (Math.random() * getX());
            int y = (int) (Math.random() * getY());
            if (this.getEntity(new Coordinates(x, y)) == null || this.getEntity(new Coordinates(x, y)).getClass() == Entity.class) {
                randomCoord = new Coordinates(x, y);
                return randomCoord;
            }
        }
    }



    public void defaultEntitiesPositions() {
        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {
                setEntity(new Coordinates(i, j), setRandomEntity(new Coordinates(i, j)));
            }
        }
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entitiesMap.containsKey(coordinates);
    }

    public String getSpriteByCoordinates(Coordinates coordinates) {
        if (!isCellEmpty(coordinates)) {
            return entitiesMap.get(coordinates).getSprite();
        } else {
            return "  ";
        }
        //return entitiesMap.get(coordinates).getSprite();
    }
}
