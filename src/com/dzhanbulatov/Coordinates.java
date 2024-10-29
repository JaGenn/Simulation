package com.dzhanbulatov;

public class Coordinates {
    public final Integer X;
    public final Integer Y;

    public Coordinates(int x, int y) {
        X = x;
        Y = y;
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
