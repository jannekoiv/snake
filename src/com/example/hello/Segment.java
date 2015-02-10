package com.example.hello;

import android.graphics.Canvas;

/**
 * Created by jak on 1/11/15.
 */
public class Segment
{
    Vector2i position;
    Vector2i direction;
    Vector2i nextDirection;

    public Segment(Vector2i position, Vector2i direction)
    {
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
    }

    public void setNextDirection(Vector2i nextDirection)
    {
        this.nextDirection = nextDirection;
    }

    public void draw(Canvas canvas, Image[][][][] images)
    {
        int i = direction.getX() + 1;
        int j = direction.getY() + 1;
        int k = nextDirection.getX() + 1;
        int l = nextDirection.getY() + 1;
        images[i][j][k][l].move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        images[i][j][k][l].draw(canvas);
    }
}
