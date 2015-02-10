package com.example.hello;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Snake
{

    private Vector2i direction;

    private float t = 0;
    public float speed = 1;
    public int length = 5;
    LinkedList<Segment> segments;
    GameView view;

    public Snake(GameView view)
    {
        this.view = view;
        direction = new Vector2i(0, 0);
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2i(5, 5), new Vector2i(0, 0)));
    }

    public boolean isSnakeMoving()
    {
        if (!direction.equals(new Vector2i(0, 0)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void draw(Canvas canvas, Image[][][][][] images)
    {
        if (isSnakeMoving())
        {
            t += speed;
            if (t >= 10)
            {
                t = 0;
                Segment segment = segments.getFirst();
                segment.isFirstSegment = false;
                segment.setNextDirection(direction);
                Vector2i newPosition = new Vector2i(Vector2i.add(segment.position, direction));
                Segment newSegment = new Segment(newPosition, direction);
                newSegment.isFirstSegment = true;
                segments.addFirst(newSegment);
                if (segments.size() > length)
                {
                    segments.removeLast();
                }
                segments.getLast().direction = segments.getLast().nextDirection;
                segments.getLast().isLastSegment = true;
            }
        }
        ListIterator<Segment> iterator = segments.listIterator();
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            segment.draw(canvas, images);
        }
    }

    public void setDirection(Vector2i direction)
    {
        this.direction = direction;
    }
}
