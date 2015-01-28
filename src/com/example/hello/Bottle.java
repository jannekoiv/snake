package com.example.hello;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by jak on 1/11/15.
 */
public class Bottle
{
    private int positionX;
    private int positionY;
    private int type;

    public Bottle()
    {
    }

    public void init()
    {
        Random random = new Random();
        positionX = random.nextInt(14);
        positionY = random.nextInt(14);
        type = 0;
    }

    public boolean draw(Canvas canvas, Image image, LinkedList<Segment> segments)
    {
        boolean collected = false;
        if (segments.getFirst().position.getX() == positionX &&
            segments.getFirst().position.getY() == positionY)
        {
            do
            {
                init();
            } while (isPositionOccupied(segments));
            collected = true;
        }
        image.move(new Vector2(positionX * 48 + 24, positionY * 48 + 24));
        image.draw(canvas);
        return collected;
    }

    public boolean isPositionOccupied(LinkedList<Segment> segments)
    {

        ListIterator<Segment> iterator = segments.listIterator();
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            if (segment.position.getX() == positionX &&
                segment.position.getY() == positionY)
            {
                return true;
            }
        }
        return false;
    }
}



