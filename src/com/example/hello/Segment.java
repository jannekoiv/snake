package com.example.hello;

import android.graphics.Canvas;

/**
 * Created by jak on 1/11/15.
 */
public class Segment
{
    Vector2i position;
    Vector2i direction;

    public Segment(Vector2i position, Vector2i direction)
    {
        this.position = position;
        this.direction = direction;
    }

    public void draw(Canvas canvas, Image[] images)
    {
        images[0].move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        images[0].draw(canvas);
    }
}
