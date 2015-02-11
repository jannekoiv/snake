package com.example.hello;

import android.graphics.Canvas;

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
    private Image[][][][][] images;

    public Snake(GameView view)
    {
        this.view = view;
        direction = new Vector2i(0, 0);
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2i(5, 5), new Vector2i(0, 0)));
        images = new Image[5][5][5][5][5];
        images[1][1][1][1][0] = new Image(view, R.drawable.snakestop);
        images[1][1][0][1][0] = new Image(view, R.drawable.snakehorizontal);
        images[1][1][2][1][0] = new Image(view, R.drawable.snakehorizontal);
        images[1][1][1][0][0] = new Image(view, R.drawable.snakevertical);
        images[1][1][1][2][0] = new Image(view, R.drawable.snakevertical);
        images[1][1][0][1][1] = new Image(view, R.drawable.snakeheadleft);
        images[1][1][2][1][1] = new Image(view, R.drawable.snakeheadright);
        images[1][1][1][0][1] = new Image(view, R.drawable.snakeheadup);
        images[1][1][1][2][1] = new Image(view, R.drawable.snakeheaddown);
        images[1][1][0][1][2] = new Image(view, R.drawable.snaketailleft);
        images[1][1][2][1][2] = new Image(view, R.drawable.snaketailright);
        images[1][1][1][0][2] = new Image(view, R.drawable.snaketailup);
        images[1][1][1][2][2] = new Image(view, R.drawable.snaketaildown);
        images[1][1][1][1][0] = new Image(view, R.drawable.snakestop);
        images[0][1][0][1][0] = new Image(view, R.drawable.snakehorizontal);
        images[2][1][2][1][0] = new Image(view, R.drawable.snakehorizontal);
        images[1][0][1][0][0] = new Image(view, R.drawable.snakevertical);
        images[1][2][1][2][0] = new Image(view, R.drawable.snakevertical);
        images[0][1][0][1][1] = new Image(view, R.drawable.snakeheadleft);
        images[2][1][2][1][1] = new Image(view, R.drawable.snakeheadright);
        images[1][0][1][0][1] = new Image(view, R.drawable.snakeheadup);
        images[1][2][1][2][1] = new Image(view, R.drawable.snakeheaddown);
        images[0][1][0][1][2] = new Image(view, R.drawable.snaketailleft);
        images[2][1][2][1][2] = new Image(view, R.drawable.snaketailright);
        images[1][0][1][0][2] = new Image(view, R.drawable.snaketailup);
        images[1][2][1][2][2] = new Image(view, R.drawable.snaketaildown);
        images[2][1][1][0][0] = new Image(view, R.drawable.snakerightup);
        images[2][1][1][2][0] = new Image(view, R.drawable.snakerightdown);
        images[0][1][1][0][0] = new Image(view, R.drawable.snakeleftup);
        images[0][1][1][2][0] = new Image(view, R.drawable.snakeleftdown);
        images[1][0][0][1][0] = new Image(view, R.drawable.snakerightdown);
        images[1][0][2][1][0] = new Image(view, R.drawable.snakeleftdown);
        images[1][2][0][1][0] = new Image(view, R.drawable.snakerightup);
        images[1][2][2][1][0] = new Image(view, R.drawable.snakeleftup);
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

    public void draw(Canvas canvas)
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
