package com.example.snake;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by jak on 1/11/15.
 */
public class Image
{
    private GameView view;
    public Vector2 position;
    private Vector2 size;
    public Bitmap bitmap;

    public Image(GameView view, int input)
    {
        this.view = view;
        position = new Vector2(0, 0);
        BitmapFactory.Options mNoScale = new BitmapFactory.Options();
        mNoScale.inScaled = false;
        bitmap = BitmapFactory.decodeResource(view.getResources(), input, mNoScale);
        size = new Vector2(bitmap.getWidth(), bitmap.getHeight());
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, position.x, position.y, null);
    }
    public void move(Vector2 position)
    {
        this.position = position;
    }
    public boolean containsPoint(Vector2 point)
    {
        if (point.x > position.x &&
            point.y > position.y &&
            point.x < (position.x + size.x) &&
            point.y < (position.y + size.y))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
