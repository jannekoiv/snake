package com.example.hello;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Snake
{

    private int direction;
    private int prevDirection;

    private float t = 0;
    public float speed = 1;
    public int length = 5;
    LinkedList<Segment> segments;
    GameView view;

    public Snake(GameView view)
    {
        this.view = view;
        direction = Segment.SEGMENT_STOP;
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2i(5, 5), Segment.SEGMENT_STOP));
    }

    public int getOrientation()
    {
        if (direction == Segment.SEGMENT_UP)
        {
            if (prevDirection == Segment.SEGMENT_RIGHT)
            {
                return Segment.SEGMENT_UP | Segment.SEGMENT_RIGHT;
            }
            else if (prevDirection == Segment.SEGMENT_LEFT)
            {
                return Segment.SEGMENT_UP | Segment.SEGMENT_LEFT;
            }
            else
            {
                return Segment.SEGMENT_UP;
            }
        }
        if (direction == Segment.SEGMENT_DOWN)
        {
            if (prevDirection == Segment.SEGMENT_RIGHT)
            {
                return Segment.SEGMENT_DOWN | Segment.SEGMENT_RIGHT;
            }
            else if (prevDirection == Segment.SEGMENT_LEFT)
            {
                return Segment.SEGMENT_DOWN | Segment.SEGMENT_LEFT;
            }
            else
            {
                return Segment.SEGMENT_DOWN;
            }
        }
        if (direction == Segment.SEGMENT_LEFT)
        {
            if (prevDirection == Segment.SEGMENT_UP)
            {
                return Segment.SEGMENT_RIGHT | Segment.SEGMENT_DOWN;
            }
            else if (prevDirection == Segment.SEGMENT_DOWN)
            {
                return Segment.SEGMENT_RIGHT | Segment.SEGMENT_UP;
            }
            else
            {
                return Segment.SEGMENT_LEFT;
            }
        }
        if (direction == Segment.SEGMENT_RIGHT)
        {
            if (prevDirection == Segment.SEGMENT_UP)
            {
                return Segment.SEGMENT_LEFT | Segment.SEGMENT_DOWN;
            }
            else if (prevDirection == Segment.SEGMENT_DOWN)
            {
                return Segment.SEGMENT_LEFT | Segment.SEGMENT_UP;
            }
            else
            {
                return Segment.SEGMENT_RIGHT;
            }
        }
        return Segment.SEGMENT_STOP;
    }

    public boolean isSnakeMoving()
    {
        if (direction != Segment.SEGMENT_STOP)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void draw(Canvas canvas, Image[] images)
    {
        t += speed;
        if (t >= 10)
        {
            t = 0;

            Segment segment = segments.getFirst();
            Vector2i newPosition = new Vector2i(Vector2i.add(segment.position, getDirection(direction)));
            segment.type = getOrientation();
            prevDirection = direction;
            Segment newSegment = new Segment(newPosition, getOrientation());
            if (isSnakeMoving() == true)
            {
                newSegment.type |= Segment.SEGMENT_HEAD;
            }
            segments.addFirst(newSegment);
            if (segments.size() > length)
            {
                segments.removeLast();
            }
        }

        ListIterator<Segment> iterator = segments.listIterator();
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            segment.draw(canvas, images);
        }
    }

    private Vector2i getDirection(int direction)
    {
        return new Vector2i(getDirectionX(direction), getDirectionY(direction));
    }

    private int getDirectionX(int direction)
    {
        if (direction == Segment.SEGMENT_LEFT)
        {
            return -1;
        }
        else if (direction == Segment.SEGMENT_RIGHT)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private int getDirectionY(int direction)
    {
        if (direction == Segment.SEGMENT_UP)
        {
            return -1;
        }
        else if (direction == Segment.SEGMENT_DOWN)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }
}
