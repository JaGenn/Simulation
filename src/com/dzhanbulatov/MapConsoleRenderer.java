package com.dzhanbulatov;

public class MapConsoleRenderer {

    public static void render(Map map) {
        for (int i = map.getY(); i >= 1; i--) {
            StringBuilder result = new StringBuilder();
            for (int j = 1; j <= map.getX(); j++) {
                result.append("| " + map.getSpriteByCoordinates(new Coordinates(j, i)) + " ");
            }
            System.out.println(result + "|");
        }
    }

}
