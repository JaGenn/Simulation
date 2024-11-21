package com.dzhanbulatov;

public class Coordinates {
    private final Integer X;
    private final Integer Y;


    public Coordinates(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public Coordinates shift(Coordinates shift) {
        return new Coordinates(this.X + shift.X, this.Y + shift.Y);
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }


    public boolean canShift(Coordinates shift) {
        int x = X + shift.X;
        int y = Y + shift.Y;

        if (x < 1 || x > Map.getX()) return false;
        if (y < 1 || y > Map.getY()) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return X.equals(that.X) && Y.equals(that.Y);
    }

    @Override
    public int hashCode() {
        int result = X.hashCode();
        result = 31 * result + Y.hashCode();
        return result;
    }
}
