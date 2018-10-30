import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Pack200;

/**
 * Created by Jamie on 10/28/2018.
 */
public class A {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean[][] grid = new boolean[5][5];

        int count = 0;
        for (int i = 0; i < 5; i++) {
            String row = scan.nextLine();
            for (int j = 0; j < 5; j++) {
                grid[i][j] = 'k' == row.charAt(j);
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        if (count != 9) {
            System.out.println("invalid");
            System.exit(0);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] && conflict(grid, i, j)) {
                    System.out.println("invalid");
                    System.exit(0);
                }
            }
        }
        System.out.println("valid");

    }

    public static boolean conflict(boolean[][] grid, int i, int j) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(i + 1, j + 2));
        points.add(new Point(i - 1, j + 2));
        points.add(new Point(i + 1, j - 2));
        points.add(new Point(i - 1, j + 2));


        points.add(new Point(i + 2, j + 1));
        points.add(new Point(i - 2, j + 1));
        points.add(new Point(i + 2, j - 1));
        points.add(new Point(i - 2, j + 1));

        for (Point p : points) {
            if (inGrid(p) && grid[p.x][p.y]) {
                //System.err.println("Conflict at " + p.x + " " + p.y);
                return true;
            }
        }
        return false;
    }

    public static boolean inGrid(Point p) {
        return p.x < 5 && p.x >= 0 && p.y < 5 && p.y >=0;
    }
}
