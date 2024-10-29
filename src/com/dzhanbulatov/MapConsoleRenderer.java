package com.dzhanbulatov;

public class MapConsoleRenderer {

    public static void render(Map map) {
        for (int i = Main.Y; i >= 1; i--) {
            for (int j = 0; j < Main.X; j++) {
                System.out.println("X" + j + " " + "Y" + i);
            }
        }

    }

}
