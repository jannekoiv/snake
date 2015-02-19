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
    Vector2i position;

    public Bottle()
    {
        Random random = new Random();
        position = new Vector2i(random.nextInt(14), random.nextInt(14));
    }

    private void init()
    {
        Random random = new Random();
        position = new Vector2i(random.nextInt(14), random.nextInt(14));
    }

    public boolean draw(Canvas canvas, Image image, LinkedList<Segment> segments)
    {
        boolean collected = false;
        if (segments.getFirst().getPosition().getX() == position.getX() &&
            segments.getFirst().getPosition().getY() == position.getY())
        {
            do
            {
                init();
            } while (isPositionOccupied(segments));
            collected = true;
        }
        image.move(new Vector2(position.getX() * 48 + 24, position.getY() * 48 + 24));
        image.draw(canvas);
        return collected;
    }

    public boolean isPositionOccupied(LinkedList<Segment> segments)
    {

        ListIterator<Segment> iterator = segments.listIterator();
        while (iterator.hasNext())
        {
            Segment segment = iterator.next();
            if (segment.getPosition().getX() == position.getX() &&
                segment.getPosition().getY() == position.getY())
            {
                return true;
            }
        }
        return false;
    }
}



