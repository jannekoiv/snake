package com.example.hello;

import android.graphics.Canvas;

import java.util.HashMap;

/**
 * Created by jak on 1/11/15.
 */
public class Segment
{
    Vector2i position;
    Vector2i direction;
    Vector2i nextDirection;
    boolean isFirstSegment;
    boolean isLastSegment;

    public Segment(Vector2i position, Vector2i direction)
    {
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
        isFirstSegment = false;
        isLastSegment = false;
    }

    public void setNextDirection(Vector2i nextDirection)
    {
        this.nextDirection = nextDirection;
    }

    public void draw(Canvas canvas, HashMap images)
    {
        int t = 0;
        if (isFirstSegment)
        {
            t = 1;
        }
        else if (isLastSegment)
        {
            t = 2;
        }
        int hashKey = Snake.generateHashKey(direction.getX(),
                                            direction.getY(),
                                            nextDirection.getX(),
                                            nextDirection.getY(),
                                            t);
        Image image = (Image)images.get(hashKey);
        image.move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        image.draw(canvas);
    }
}
