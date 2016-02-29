package com.example.snake;

import android.graphics.Canvas;

import java.util.HashMap;

/**
 * Created by jak on 1/11/15.
 */
public class Segment {
    private Vector2old position;
    private Vector2old direction;
    private Vector2old nextDirection;
    private int type;
    public static final int SEGMENT_BODY = 0;
    public static final int SEGMENT_HEAD = 1;
    public static final int SEGMENT_TAIL = 2;
    private static final int SEGMENT_WIDTH = 48;
    private static final int SEGMENT_HEIGHT = 48;
    private static final int SEGMENT_OFFSET_X = 24;
    private static final int SEGMENT_OFFSET_Y = 24;

    public Segment(Vector2old position, Vector2old direction) {
        this.position = position;
        this.direction = direction;
        this.nextDirection = direction;
        type = SEGMENT_HEAD;
    }

    public Vector2old getPosition() {
        return position;
    }

    public void setNextDirection(Vector2old nextDirection) {
        this.nextDirection = nextDirection;
        type = SEGMENT_BODY;
    }

    public void makeTailSegment() {
        direction = nextDirection;
        type = SEGMENT_TAIL;
    }

    private Vector2 scalePosition(Vector2old position) {
        return new Vector2(position.getX() * SEGMENT_WIDTH + SEGMENT_OFFSET_X + 100000,
                           position.getY() * SEGMENT_HEIGHT + SEGMENT_OFFSET_Y + 100000);
    }

    public void draw(Canvas canvas, HashMap images) {

        int hashKey = Snake.generateHashKey(direction.getX(),
                                            direction.getY(),
                                            nextDirection.getX(),
                                            nextDirection.getY(),
                                            type);

        Image image = (Image)images.get(hashKey);
        image.move(scalePosition(position));
        image.draw(canvas);
    }
}
