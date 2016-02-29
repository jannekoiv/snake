package com.example.snake;

import android.graphics.Canvas;
import com.example.snake.R;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Snake {
    GameView view;
    private Vector2old direction;
    private float t;
    private float speed;
    private int length;
    private HashMap images;
    LinkedList<Segment> segments;
    private static final float TIME_LIMIT = 10;

    public Snake(GameView view) {
        this.view = view;
        initVariables();
        initImages();
        initSegments();
    }

    private void initVariables() {
        direction = new Vector2old(0, 0);
        t = 0;
        speed = 1;
        length = 5;
    }

    public void initImages() {
        images = new HashMap();
        images.put(generateHashKey(0, 0, 0, 0, Segment.SEGMENT_HEAD), newImage(R.drawable.snakestop));
        images.put(generateHashKey(0, 0, -1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, 0, 1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, 0, 0, -1, Segment.SEGMENT_BODY), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 0, 0, 1, Segment.SEGMENT_BODY), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(-1, 0, -1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(1, 0, 1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakehorizontal));
        images.put(generateHashKey(0, -1, 0, -1, Segment.SEGMENT_BODY), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 1, 0, 1, Segment.SEGMENT_BODY), newImage(R.drawable.snakevertical));
        images.put(generateHashKey(0, 0, -1, 0, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadleft));
        images.put(generateHashKey(0, 0, 1, 0, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadright));
        images.put(generateHashKey(0, 0, 0, -1, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadup));
        images.put(generateHashKey(0, 0, 0, 1, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheaddown));
        images.put(generateHashKey(0, 0, -1, 0, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailleft));
        images.put(generateHashKey(0, 0, 1, 0, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailright));
        images.put(generateHashKey(0, 0, 0, -1, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailup));
        images.put(generateHashKey(0, 0, 0, 1, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketaildown));
        images.put(generateHashKey(-1, 0, -1, 0, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadleft));
        images.put(generateHashKey(1, 0, 1, 0, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadright));
        images.put(generateHashKey(0, -1, 0, -1, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheadup));
        images.put(generateHashKey(0, 1, 0, 1, Segment.SEGMENT_HEAD), newImage(R.drawable.snakeheaddown));
        images.put(generateHashKey(-1, 0, -1, 0, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailleft));
        images.put(generateHashKey(1, 0, 1, 0, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailright));
        images.put(generateHashKey(0, -1, 0, -1, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketailup));
        images.put(generateHashKey(0, 1, 0, 1, Segment.SEGMENT_TAIL), newImage(R.drawable.snaketaildown));
        images.put(generateHashKey(1, 0, 0, -1, Segment.SEGMENT_BODY), newImage(R.drawable.snakerightup));
        images.put(generateHashKey(1, 0, 0, 1, Segment.SEGMENT_BODY), newImage(R.drawable.snakerightdown));
        images.put(generateHashKey(-1, 0, 0, -1, Segment.SEGMENT_BODY), newImage(R.drawable.snakeleftup));
        images.put(generateHashKey(-1, 0, 0, 1, Segment.SEGMENT_BODY), newImage(R.drawable.snakeleftdown));
        images.put(generateHashKey(0, -1, -1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakerightdown));
        images.put(generateHashKey(0, -1, 1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakeleftdown));
        images.put(generateHashKey(0, 1, -1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakerightup));
        images.put(generateHashKey(0, 1, 1, 0, Segment.SEGMENT_BODY), newImage(R.drawable.snakeleftup));
    }

    public static int generateHashKey(int i, int j, int k, int l, int m) {
        return i * 10000 +
               j * 1000 +
               k * 100 +
               l * 10 +
               m;
    }

    public Image newImage(int resource) {
        return new Image(view, resource);
    }

    public void initSegments() {
        segments = new LinkedList<Segment>();
        segments.addFirst(new Segment(new Vector2old(5, 5), new Vector2old(0, 0)));
    }

    public void update() {
        if (isSnakeMoving() == true && updateTime() == true) {
            updateSegments();
        }
    }

    private boolean isSnakeMoving() {
        return !direction.isZero();
    }

    private boolean updateTime() {
        t += speed;
        if (t > TIME_LIMIT) {
            t = 0;
            return true;
        }
        else {
            return false;
        }
    }

    private void updateSegments() {
        Segment segment = segments.getFirst();
        segment.setNextDirection(direction);
        segments.addFirst(new Segment(Vector2old.add(segment.getPosition(), direction), direction));
        limitSegmentCount();
        segments.getLast().makeTailSegment();
    }

    private void limitSegmentCount() {
        if (segments.size() > length) {
            segments.removeLast();
        }
    }

    public void draw(Canvas canvas) {
        for (Segment segment : segments) {
            segment.draw(canvas, images);
        }
    }

    public boolean testCollision() {
        return testCollisionWalls() || testCollisionSelf();
    }

    private boolean testCollisionSelf() {
        Vector2old headPosition = segments.getFirst().getPosition();
        ListIterator<Segment> iterator = segments.listIterator(1);
        while (iterator.hasNext()) {
            Segment segment = iterator.next();
            if (headPosition.equals(segment.getPosition())) {
                return true;
            }
        }


        return false;
    }

    private boolean testCollisionWalls() {
        Vector2old headPosition = segments.getFirst().getPosition();
        if (headPosition.getX() < 0 ||
            headPosition.getX() > 13 ||
            headPosition.getY() < 0 ||
            headPosition.getY() > 13) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setDirection(Vector2old direction) {
        this.direction = direction;
    }

    public void reset() {
        initVariables();
        initSegments();
    }

    public void grow() {
        length++;
        speed += 0.1f;
    }


}
