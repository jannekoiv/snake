package com.example.hello;

import android.graphics.Canvas;

/**
 * Created by jak on 1/11/15.
 */
public class Segment
{
    public static final int SEGMENT_STOP = 0;
    public static final int SEGMENT_UP = 1;
    public static final int SEGMENT_DOWN = 2;
    public static final int SEGMENT_LEFT = 4;
    public static final int SEGMENT_RIGHT = 8;
    public static final int SEGMENT_HEAD = 16;
    public static final int SEGMENT_TAIL = 32;

    Vector2i position;
    public int type;

    public Segment(Vector2i position, int type)
    {
        this.position = position;
        this.type = type;
    }

    public void draw(Canvas canvas, Image[] images)
    {
        images[type].move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        images[type].draw(canvas);
    }
}
