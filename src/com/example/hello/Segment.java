package com.example.hello;

import android.graphics.Canvas;

import java.util.HashMap;

/**
 * Created by jak on 1/11/15.
 */
public class Segment
{
    private Vector2i position;
    private Vector2i direction;
    private Vector2i nextDirection;
    private boolean isFirstSegment;
    private boolean isLastSegment;

    public Segment(Vector2i position, Vector2i direction)
    {
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
        isFirstSegment = true;
        isLastSegment = false;
    }

    public Vector2i getPosition() {
        return position;
    }

    public void setNextDirection(Vector2i nextDirection) {
        this.nextDirection = nextDirection;
        isFirstSegment = false;
    }

    public void makeLastSegment() {
        direction = nextDirection;
        isLastSegment = true;
    }

    public void draw(Canvas canvas, HashMap images) {
        int type = 0;
        if (isFirstSegment) {
            type = 1;
        }
        else if (isLastSegment) {
            type = 2;
        }
        int hashKey = Snake.generateHashKey(direction.getX(),
                                            direction.getY(),
                                            nextDirection.getX(),
                                            nextDirection.getY(),
                                            type);
        Image image = (Image) images.get(hashKey);
        image.move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        image.draw(canvas);
    }
}
