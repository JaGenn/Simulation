package com.dzhanbulatov.entity;

import com.dzhanbulatov.Coordinates;
import com.dzhanbulatov.Map;

import java.util.*;
import java.util.stream.Collectors;


public abstract class Creature extends Entity {
    protected Map map;
    public Coordinates coordinates;
    protected int speed;
    public int movesCount;


    public Creature(Coordinates coordinates, Map map) {
        super(coordinates);
        this.coordinates = coordinates;
        this.map = map;
        movesCount = 0;
    }


    protected abstract boolean canEat(Coordinates other);

    public void changeCoordinates() {
        Coordinates newCoordForCreature = map.getRandomCoordinatesOnMap();
        map.removeEntity(this.coordinates);
        this.coordinates = newCoordForCreature;
        map.setEntity(this.coordinates, this);
    }

    public void makeMove(Deque<Coordinates> path, Map map) {
        for (int step = 0; step <= this.speed && !path.isEmpty(); step++) {
            Coordinates nextStep = path.pollFirst(); // Берем следующий шаг из пути
            map.removeEntity(this.coordinates);
            this.coordinates = nextStep;
            map.setEntity(nextStep, this);
        }
    }


    public Deque<Coordinates> findWayToFood(Map map) {
        Queue<Deque<Coordinates>> toVisit = new ArrayDeque<>();    // Очередь путей для BFS
        Set<Coordinates> visited = new HashSet<>();         // Чтобы отслеживать посещённые клетки

        Deque<Coordinates> startPath = new ArrayDeque<>();
        startPath.add(this.coordinates);                      // Начинаем с текущих координат
        toVisit.add(startPath);
        visited.add(this.coordinates);

        while (!toVisit.isEmpty()) {
            Deque<Coordinates> currentPath = toVisit.poll(); // Текущий путь из очереди
            Coordinates currentCoord = currentPath.peekLast(); // Конец пути


            // Проверяем, есть ли съедобная сущность на текущих координатах
            Entity entityAtVisiting = map.getEntity(currentCoord);
            if (entityAtVisiting != null && canEat(entityAtVisiting.coordinates)) {
                return currentPath;             // Возвращаем пройденный путь
            }

            // Добавляем все пустые соседние клетки для продолжения поиска
            for (Coordinates neighbor : emptyCellsNear(currentCoord)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    // Создаём новый путь на основе текущего и добавляем соседа
                    Deque<Coordinates> newPath = new ArrayDeque<>(currentPath);
                    newPath.add(neighbor);
                    toVisit.add(newPath);
                }
            }
        }

        // Если еда не найдена, возвращаем пустой путь
        return new ArrayDeque<>();
    }


    private boolean isCellAvailableToMove(Coordinates newCoordinates, Map map) {
        Entity itself = map.getEntity(newCoordinates);
        if (itself == null) {
            return true;
        }
        if (map.getEntity(newCoordinates) instanceof Rock ||
                map.getEntity(newCoordinates) instanceof Tree ||
                map.getEntity(newCoordinates) instanceof Predator ||
                itself.getClass() == this.getClass()) {
            return false;
        } else if (this instanceof Predator && map.getEntity(newCoordinates) instanceof Grass) {
            return false;
        }  else {
            return true;
        }

    }


    private Set<Coordinates> getCreatureMoves() {
        return new HashSet<>(Arrays.asList(
                new Coordinates(0, 1),
                new Coordinates(1, 0),
                new Coordinates(1, 1),
                new Coordinates(0, -1),
                new Coordinates(-1, 0),
                new Coordinates(-1, -1),
                new Coordinates(1, -1),
                new Coordinates(-1, 1)
        ));
    }

    private List<Coordinates> emptyCellsNear(Coordinates c2) {
        return getCreatureMoves().stream()
                .map(c2::shift) // Применяем смещения к переданным координатам
                .filter(el -> map.getMap().containsKey(el) && isCellAvailableToMove(el, map) || map.getEntity(c2) == null) // Проверяем, что клетка доступна для движения
                .collect(Collectors.toList());
    }


}

