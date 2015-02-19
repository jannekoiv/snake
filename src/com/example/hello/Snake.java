package com.example.hello;

import android.graphics.Canvas;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by jak on 1/11/15.
 */
public class Snake {
    GameView view;
    private Vector2i direction;
    private float t = 0;
    public float speed = 1;
    public int length = 5;
    LinkedList<Segment> segments;
    private HashMap images;
    private static final float TIME_LIMIT = 10;

    public Snake(GameView view) {
        this.view = view;
        direction = new Vector2i(0, 0);
        initImages();
        initSegments();
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
        segments.addFirst(new Segment(new Vector2i(5, 5), new Vector2i(0, 0)));
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
        segments.addFirst(new Segment(Vector2i.add(segment.getPosition(), direction), direction));
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
        Vector2i headPosition = segments.getFirst().getPosition();
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
        Vector2i headPosition = segments.getFirst().getPosition();
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

    public void setDirection(Vector2i direction) {
        this.direction = direction;
    }
}
