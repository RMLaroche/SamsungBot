package SamsungBot;

public class Utils {
    private Utils(){}

    public static void printMap(int width, int height, int[][] map){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j]<0) {
                    System.out.print(map[i][j]);
                }else{
                    System.out.print(" " + map[i][j]);
                }
            }
            System.out.println();
        }
    }
}
