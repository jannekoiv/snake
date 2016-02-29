package com.example.snake;

import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by jak on 1/9/15.
 */
public class GameView extends SurfaceView {
    private Image[] images;
    private GameLoopThread gameLoopThread;
    private SurfaceHolder surfaceHolder;
    float t;
    boolean touching;

    int frameNumber = 0;

    private Image[] buttons;
    private Image background;

    private Game game;

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        surfaceHolder = getHolder();

        surfaceHolder.addCallback (new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder)
            {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while(retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    }
                    catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder)
            {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height)
            {
            }
        });
        game = new Game();
        game.init(this);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        game.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            game.onTouchEventDown(new Vector2(event.getX(), event.getY()));
            return true;
        }
        return true;
    }
}




