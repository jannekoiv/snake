package com.example.snake;

/**
 * Created by jak on 2/27/15.
 */
public class Vector2i {
    private int y;
    private int x;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public static Vector2i add(Vector2i vectorA, Vector2i vectorB) {
        return new Vector2i(vectorA.x + vectorB.x, vectorA.y + vectorB.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
