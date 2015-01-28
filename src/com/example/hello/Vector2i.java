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
    public Vector2i Add(Vector2i p)
    {
        this.x += p.getX();
        this.y += p.getY();
        return this;
    }
}
