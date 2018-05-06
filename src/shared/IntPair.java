package shared;

import java.util.Objects;

public class IntPair {
    private int x;
    private int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntPair)) return false;
        IntPair intPair = (IntPair) o;
        return x == intPair.x &&
                y == intPair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
