package com.example.snake;

import org.junit.Before;
import org.junit.Test;

public class Vector2iTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAdd() {
        Vector2i result = Vector2i.add(new Vector2i(1, 2), new Vector2i(3, 4));
        assert (result.getX() == 4 && result.getY() == 6);
    }
}