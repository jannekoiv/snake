package com.example.snake;

import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import com.example.snake.R;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Game
{
    private Image background;
    private Image[] buttons;
    private Snake snake;
    private LinkedList<Bottle> bottles;
    private SoundPool sounds;
    private int slurp;
    private int perkule;

    public Game()
    {
    }

    public void init(GameView view)
    {
        initBackground(view);
        initButtons(view);
        initSnake(view);
        initBottles(view);
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        slurp = sounds.load(view.getContext(), R.raw.kalja, 1);
        perkule = sounds.load(view.getContext(), R.raw.siikala, 1);
    }

    private void initBottles(GameView view)
    {
        bottles = new LinkedList<Bottle>();
        Bottle bottle = new Bottle(view);
        bottles.addFirst(bottle);
    }

    private void initSnake(GameView view)
    {
        snake = new Snake(view);
    }

    private void initBackground(GameView view)
    {
        background = new Image(view, R.drawable.background);
    }

    private void initButtons(GameView view)
    {
        buttons = new Image[4];
        buttons[0] = new Image(view, R.drawable.arrowleft);
        buttons[0].move(new Vector2(50, 930));
        buttons[1] = new Image(view, R.drawable.arrowright);
        buttons[1].move(new Vector2(450, 930));
        buttons[2] = new Image(view, R.drawable.arrowup);
        buttons[2].move(new Vector2(250, 720));
        buttons[3] = new Image(view, R.drawable.arrowdown);
        buttons[3].move(new Vector2(250, 930));
    }

    public void draw(Canvas canvas)
    {
        background.draw(canvas);

        for (int i = 0; i < 4; i++)
        {
            buttons[i].draw(canvas);
        }

        ListIterator<Bottle> iterator = bottles.listIterator();
        while (iterator.hasNext())
        {
            if (iterator.next().draw(canvas, snake.segments) == true)
            {
                snake.grow();
                sounds.play(slurp, 1.0f, 1.0f, 0, 0, 1.0f);
            }
        }

        snake.update();

        if (snake.testCollision() == true)
        {
            sounds.play(perkule, 1.0f, 1.0f, 0, 0, 1.0f);
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {

            }
            snake.initSegments();
            snake.setDirection(new Vector2old(0, 0));
            snake.reset();
        }
        snake.draw(canvas);
    }

    public void onTouchEventDown(Vector2 point)
    {
        if (buttons[0].containsPoint(point))
        {
            snake.setDirection(new Vector2old(-1, 0));
        }
        else if (buttons[1].containsPoint(point))
        {
            snake.setDirection(new Vector2old(1, 0));
        }
        else if (buttons[2].containsPoint(point))
        {
            snake.setDirection(new Vector2old(0, -1));
        }
        else if (buttons[3].containsPoint(point))
        {
            snake.setDirection(new Vector2old(0, 1));
        }
    }
}
