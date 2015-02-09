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
    private Vector2i prevDirection;

    private float t = 0;
    public float speed = 1;
    public int length = 5;
    LinkedList<Segment> segments;
    GameView view;

    public Snake(GameView view)
    {
        this.view = view;
        direction = new Vector2i(0, 0);
        prevDirection = new Vector2i(0, 0);
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2i(5, 5), new Vector2i(0, 0)));
    }

//    public int getOrientation()
//    {
//        if (direction.equals(new Vector2i(0, -1)))
//        {
//            if (prevDirection.equals(new Vector2i(1, 0)))
//            {
//                return Segment.SEGMENT_UP | Segment.SEGMENT_RIGHT;
//            }
//            else if (prevDirection.equals(new Vector2i(-1, 0)))
//            {
//                return Segment.SEGMENT_UP | Segment.SEGMENT_LEFT;
//            }
//            else
//            {
//                return Segment.SEGMENT_UP;
//            }
//        }
//        if (direction.equals(new Vector2i(0, 1)))
//        {
//            if (prevDirection.equals(new Vector2i(1, 0)))
//            {
//                return Segment.SEGMENT_DOWN | Segment.SEGMENT_RIGHT;
//            }
//            else if (prevDirection.equals(new Vector2i(-1, 0)))
//            {
//                return Segment.SEGMENT_DOWN | Segment.SEGMENT_LEFT;
//            }
//            else
//            {
//                return Segment.SEGMENT_DOWN;
//            }
//        }
//        if (direction.equals(new Vector2i(-1, 0)))
//        {
//            if (prevDirection.equals(new Vector2i(0, -1)))
//            {
//                return Segment.SEGMENT_RIGHT | Segment.SEGMENT_DOWN;
//            }
//            else if (prevDirection.equals(new Vector2i(0, 1)))
//            {
//                return Segment.SEGMENT_RIGHT | Segment.SEGMENT_UP;
//            }
//            else
//            {
//                return Segment.SEGMENT_LEFT;
//            }
//        }
//        if (direction.equals(new Vector2i(1, 0)))
//        {
//            if (prevDirection.equals(new Vector2i(0, -1)))
//            {
//                return Segment.SEGMENT_LEFT | Segment.SEGMENT_DOWN;
//            }
//            else if (prevDirection.equals(new Vector2i(0, 1)))
//            {
//                return Segment.SEGMENT_LEFT | Segment.SEGMENT_UP;
//            }
//            else
//            {
//                return Segment.SEGMENT_RIGHT;
//            }
//        }
//        return Segment.SEGMENT_STOP;
//    }

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

    public void draw(Canvas canvas, Image[] images)
    {
        if (isSnakeMoving())
        {
            t += speed;
            if (t >= 10)
            {
                t = 0;
                Segment segment = segments.getFirst();
                Vector2i newPosition = new Vector2i(Vector2i.add(segment.position, direction));
//                segment.type = Segment.SEGMENT_HEAD;
                prevDirection = direction;
                Segment newSegment = new Segment(newPosition, new Vector2i(0, 0));
//                newSegment.type |= Segment.SEGMENT_HEAD;
                segments.addFirst(newSegment);
                if (segments.size() > length)
                {
                    segments.removeLast();
                }
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
