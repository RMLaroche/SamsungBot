package SamsungBot;

public class Processing {
    private int width;
    private int height;
    private IMotion motion;

    private int x, y;
    private int direction;
    private static int[]
            dx = { 1, 0, -1,  0 },
            dy = { 0, 1,  0, -1 };

    public Processing(int width, int height, IMotion motion) {
        this.width = width;
        this.height = height;
        this.motion = motion;

        this.x = 1;
        this.y = 1;
        this.direction=1;
    }

    public int[][] scan(){

        //Initialisation de la grille
        int[][] outputGrid = new int[this.height][this.width];
        outputGrid[1][1] = 1;
        for (int i = 0; i < (this.height); i++) {
            outputGrid[i][0] = -1;
            outputGrid[i][(this.width-1)] = -1;
        }
        for (int j = 0; j < (this.width); j++) {
            outputGrid[0][j] = -1;
            outputGrid[(this.height-1)][j] = -1;
        }
        ////////////////////////////////
        boolean recoverMode = false;
        int maxMoves=0;
        while(maxMoves < height*width) {
            motion.rotate(-90);
            direction=direction==3?0:direction+1;
            if(recoverMode){
                if (outputGrid[x + dx[direction]][y + dy[direction]] != 1 && motion.move(1)) {
                    x += dx[direction];
                    y += dy[direction];
                    //System.out.println(y);
                    recoverMode = outputGrid[x][y] != 0;
                    outputGrid[x][y] = 1;
                } else {
                    motion.rotate(90);
                    direction = direction == 0 ? 3 : direction - 1;
                    if (motion.move(1)) {
                        x += dx[direction];
                        y += dy[direction];
                        recoverMode = outputGrid[x][y] != 0;
                        outputGrid[x][y] = 1;
                    }else {
                        motion.rotate(90);
                        direction = direction == 0 ? 3 : direction - 1;
                        if (motion.move(1)) {
                            x += dx[direction];
                            y += dy[direction];
                            recoverMode = outputGrid[x][y] != 0;
                            outputGrid[x][y] = 1;
                        }
                    }
                }
            }else {
                if (outputGrid[x + dx[direction]][y + dy[direction]] != 1 && motion.move(1)) {
                    x += dx[direction];
                    y += dy[direction];
                    //System.out.println(y);
                    outputGrid[x][y] = 1;
                } else {
                    motion.rotate(90);
                    direction = direction == 0 ? 3 : direction - 1;
                    if (outputGrid[x + dx[direction]][y + dy[direction]] != 1 && motion.move(1)) {
                        x += dx[direction];
                        y += dy[direction];
                        //System.out.println(y);
                        outputGrid[x][y] = 1;
                    } else {
                        motion.rotate(90);
                        direction = direction == 0 ? 3 : direction - 1;
                        if (outputGrid[x + dx[direction]][y + dy[direction]] != 1 && motion.move(1)) {
                            x += dx[direction];
                            y += dy[direction];
                            //System.out.println(y);
                            outputGrid[x][y] = 1;
                        } else {
                            motion.rotate(90);
                            direction = direction == 0 ? 3 : direction - 1;
                                recoverMode= true;
                        }
                    }
                }
            }
            //System.out.println("x = " + x + "    | y = " + y );
            //System.out.println("newx = " + dx[direction] + " | newy = " + dy[direction] );
            //try {
            //    Thread.sleep(1);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            //Utils.printMap(width,height, outputGrid);
            maxMoves++;
        }


        for (int i = 0; i < (this.height); i++) {
            for (int j = 0; j < (this.width); j++) {
                if (outputGrid[i][j] == 0) {
                    outputGrid[i][j] = -1;
                }
            }
        }
        Utils.printMap(this.width,this.height, outputGrid);
        return outputGrid;
    }

}
