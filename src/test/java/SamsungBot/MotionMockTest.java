package SamsungBot;

import org.junit.Test;

import static org.junit.Assert.*;

public class MotionMockTest {

    @Test
    public void move() {
        int[][] grid = {
                {-1,-1,-1,-1},
                {-1,2,0,-1},
                {-1,-1,-1,-1}
        };
        MotionMock motionMock = new MotionMock(grid);
        assertTrue(motionMock.move(1));
    }

    @Test
    public void rotate() {
        int[][] grid = {
                {-1,-1,-1,-1},
                {-1,0,0,-1},
                {-1,-1,-1,-1}
        };
        MotionMock motionMock = new MotionMock(grid);
        assertTrue(motionMock.move(1));
        motionMock.rotate(90);
        assertFalse(motionMock.move(1));
        motionMock.rotate(90);
        assertTrue(motionMock.move(1));
    }
}