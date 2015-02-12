package com.example.hello;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Snake
{
    GameView view;
    private Vector2i direction;
    private float t = 0;
    public float speed = 1;
    public int length = 5;
    LinkedList<Segment> segments;
    private HashMap images;


    public static int generateHashKey(int i, int j, int k, int l, int m)
    {
        return i * 10000 +
               j * 1000 +
               k * 100 +
               l * 10 +
               m;
    }

    public Image newImage(int resource)
    {
        return new Image(view, resource);
    }
    public void initImages()
    {
        images = new HashMap();
        images.put(generateHashKey(0, 0, 0, 0, 0), newImage(R.drawable.snakestop));
        images.put(generateHashKey(0, 0, -1, 0, 0), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, 0, 1, 0, 0), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, 0, 0, -1, 0), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 0, 0, 1, 0), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(-1, 0, -1, 0, 0), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(1, 0, 1, 0, 0), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, -1, 0, -1, 0), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 1, 0, 1, 0), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 0, -1, 0, 1), newImage(R.drawable.snakeheadleft));
        images.put(generateHashKey(0, 0, 1, 0, 1), newImage(R.drawable.snakeheadright));
        images.put(generateHashKey(0, 0, 0, -1, 1), newImage(R.drawable.snakeheadup));
        images.put(generateHashKey(0, 0, 0, 1, 1), newImage(R.drawable.snakeheaddown));
        images.put(generateHashKey(0, 0, -1, 0, 2), newImage(R.drawable.snaketailleft));
        images.put(generateHashKey(0, 0, 1, 0, 2), newImage(R.drawable.snaketailright));
        images.put(generateHashKey(0, 0, 0, -1, 2), newImage(R.drawable.snaketailup));
        images.put(generateHashKey(0, 0, 0, 1, 2), newImage(R.drawable.snaketaildown));
        images.put(generateHashKey(-1, 0, -1, 0, 1), newImage(R.drawable.snakeheadleft));
        images.put(generateHashKey(1, 0, 1, 0, 1), newImage(R.drawable.snakeheadright));
        images.put(generateHashKey(0, -1, 0, -1, 1), newImage(R.drawable.snakeheadup));
        images.put(generateHashKey(0, 1, 0, 1, 1), newImage(R.drawable.snakeheaddown));
        images.put(generateHashKey(-1, 0, -1, 0, 2), newImage(R.drawable.snaketailleft));
        images.put(generateHashKey(1, 0, 1, 0, 2), newImage(R.drawable.snaketailright));
        images.put(generateHashKey(0, -1, 0, -1, 2), newImage(R.drawable.snaketailup));
        images.put(generateHashKey(0, 1, 0, 1, 2), newImage(R.drawable.snaketaildown));
        images.put(generateHashKey(1, 0, 0, -1, 0), newImage(R.drawable.snakerightup));
        images.put(generateHashKey(1, 0, 0, 1, 0), newImage(R.drawable.snakerightdown));
        images.put(generateHashKey(-1, 0, 0, -1, 0), newImage(R.drawable.snakeleftup));
        images.put(generateHashKey(-1, 0, 0, 1, 0), newImage(R.drawable.snakeleftdown));
        images.put(generateHashKey(0, -1, -1, 0, 0), newImage(R.drawable.snakerightdown));
        images.put(generateHashKey(0, -1, 1, 0, 0), newImage(R.drawable.snakeleftdown));
        images.put(generateHashKey(0, 1, -1, 0, 0), newImage(R.drawable.snakerightup));
        images.put(generateHashKey(0, 1, 1, 0, 0), newImage(R.drawable.snakeleftup));
    }

    public void initSegments()
    {
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2i(5, 5), new Vector2i(0, 0)));
    }

    public Snake(GameView view)
    {
        this.view = view;
        direction = new Vector2i(0, 0);
        initImages();
        initSegments();
    }

    public boolean isSnakeMoving()
    {
        return !direction.isZero();
    }

    public boolean updateTime()
    {
        t += speed;
        if (t >= 10)
        {
            t = 0;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void updateSegments()
    {
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

    public void drawSegments(Canvas canvas)
    {
        ListIterator<Segment> iterator = segments.listIterator();
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            segment.draw(canvas, images);
        }
    }

    public boolean testCollisionSelf()
    {
        Vector2i headPosition = segments.getFirst().position;
        ListIterator<Segment> iterator = segments.listIterator(1);
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            if (headPosition.equals(segment.position))
            {
                return true;
            }
        }
        return false;
    }

    public boolean testCollisionWalls()
    {
        Vector2i headPosition = segments.getFirst().position;
        if (headPosition.getX() < 0 ||
            headPosition.getX() > 13 ||
            headPosition.getY() < 0 ||
            headPosition.getY() > 13)
        {
            return true;
        }
        return false;
    }

    public boolean testCollision()
    {
        return testCollisionWalls() || testCollisionSelf();
    }

    public void draw(Canvas canvas)
    {
        drawSegments(canvas);
    }

    public void setDirection(Vector2i direction)
    {
        this.direction = direction;
    }
}
