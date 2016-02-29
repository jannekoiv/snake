package com.example.snake;
/**
 * Created by jak on 1/11/15.
 */
public class Vector2
{
    float x;
    float y;

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 v)
    {
        x = v.x;
        y = v.y;
    }
}
