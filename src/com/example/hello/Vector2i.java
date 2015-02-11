package com.example.hello;

/**
 * Created by jak on 1/13/15.
 */
public class Vector2i
{
    private int x;
    private int y;
    public Vector2i()
    {
    }
    public Vector2i(Vector2i vector)
    {
        this.x = vector.x;
        this.y = vector.y;
    }
    public Vector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public Vector2i add(Vector2i vector)
    {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }
    public boolean equals(Vector2i vector)
    {
        if (x == vector.x && y == vector.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isZero()
    {
        if (x == 0 && y == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static Vector2i add(Vector2i vectorA, Vector2i vectorB)
    {
        return new Vector2i(vectorA.x + vectorB.x, vectorA.y + vectorB.y);
    }
}
