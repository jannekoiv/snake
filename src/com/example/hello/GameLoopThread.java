package com.example.hello;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by jak on 1/9/15.
 */
public class GameLoopThread extends Thread {
    private GameView view;
    private boolean running = false;
    static final long FPS = 30;
    public GameLoopThread(GameView view) {
        this.view = view;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime = 0;
        long sleepTime = 0;
        while(running) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            try
            {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(canvas);
                }
            }
            finally
            {
                if(canvas != null)
                {
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try
            {
                if (sleepTime > 0)
                {
                    sleep(sleepTime);
                }
                else
                {
                    sleep(10);
                }
            }
            catch(Exception e)
            {
            }
        }
    }
}
