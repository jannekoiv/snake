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
    private int type;
    public static final int SEGMENT_BODY = 0;
    public static final int SEGMENT_HEAD = 1;
    public static final int SEGMENT_TAIL = 2;

    public Segment(Vector2i position, Vector2i direction)
    {
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
        type = SEGMENT_HEAD;
    }

    public Vector2i getPosition() {
        return position;
    }

    public void setNextDirection(Vector2i nextDirection) {
        this.nextDirection = nextDirection;
        type = SEGMENT_BODY;
    }

    public void makeTailSegment() {
        direction = nextDirection;
        type = SEGMENT_TAIL;
    }

    public void draw(Canvas canvas, HashMap images) {
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
