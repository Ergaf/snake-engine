package com.codenjoy.dojo.snake.client.handler;

import java.util.List;
import java.util.Objects;

public class Point {
    final int x;
    final int y;
    char value;
    int search;

    public Point(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", search=" + search +
                '}';
    }
}
