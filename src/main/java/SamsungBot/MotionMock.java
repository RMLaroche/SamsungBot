package SamsungBot;


public class MotionMock implements IMotion{
    public int x, y;
    private int[][] grid;
    private int direction;
    private static int[]
            dx = { 1, 0, -1,  0 },
            dy = { 0, 1,  0, -1 };


    public MotionMock(int[][] grid) {
        this.grid = grid;
        x=1;
        y=1;
        direction=1;
    }

    public boolean move(int d) {
        if(d<=0)
        {
            return false;
        }
        int newx = x;
        int newy = y;

        for (int i = 0; i < d; i++) {
            newx += dx[direction];
            newy += dy[direction];


            if (grid[newx][newy] == -1) {
                return false;

            }
        }
        x = newx;
        y = newy;
        return true;
    }

    public void rotate(int angle) {
        switch (angle){
            case -90:
                this.direction=direction==3?0:direction+1;
                break;
            case 180:
                this.direction=direction<=2?direction+2:direction-2;
                break;
            case 90:
                this.direction=direction==0?3:direction-1;
                break;
            default:
        }
    }
}
