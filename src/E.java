import java.util.*;

/**
 * Created by Jamie on 10/28/2018.
 */
public class E {

    static HashSet<Integer>[] rows;
    static int r;
    static HashSet<Integer> barriers;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        r = scan.nextInt();
        int n = scan.nextInt();
        int s = scan.nextInt();
        int e = scan.nextInt();
        int x = scan.nextInt();
        barriers = new HashSet<>();
        for (int i = 0; i < x; i++) {
            barriers.add(scan.nextInt());
        }

        int cells = r * r * r - (r-1)*(r-1)*(r-1);
        ArrayList[] graph = new ArrayList[cells];
        for (int i = 0; i < cells; i++) {
            graph[i] = new ArrayList<>();
        }

        int nRows = 2*r -1;
        rows = new HashSet[nRows];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new HashSet<>();
        }

        int curr = 1;
        //Top half of hexagon
        for (int i = 0; i < r-1; i++) {
            for (int j = 0; j < r + i; j++) {
                rows[i].add(curr);
                curr++;
            }
        }
        //middle row
        for (int i = 0; i < 2*r-1; i++) {
            rows[r-1].add(curr);
            curr++;
        }
        //bottom half of hexagon
        for (int i = 0; i < r-1; i++) {
            for (int j = 0; j < 2*r-2 - i; j++) {
                rows[r + i].add(curr);
                curr++;
            }
        }

        int d = bfs(s, e, cells);
        if (d <= n) {
            System.out.println(d);
        }
        else {
            System.out.println("No");
        }

    }

    public static List<Integer> getNeigh(int c) {
        List<Integer> neighbors = new ArrayList<>();
        int row = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i].contains(c)) {
                row = i;
                break;
            }
        }

        //Neighbors in above row
        if (row > 0) {
            int potential = c - Math.max(rows[row].size(), rows[row-1].size());
            if (rows[row-1].contains(potential)) {
                neighbors.add(potential);
            }
            if (rows[row-1].contains(potential+1)) {
                neighbors.add(potential+1);
            }
        }
        //Neighbors in same row
        if (rows[row].contains(c +1)) {
            neighbors.add(c+1);
        }
        if (rows[row].contains(c -1)) {
            neighbors.add(c-1);
        }
        //Neighbors in above row
        if (row < rows.length - 1) {
            int potential = c + Math.min(rows[row].size(), rows[row+1].size());
            if (rows[row+1].contains(potential)) {
                neighbors.add(potential);
            }
            if (rows[row+1].contains(potential+1)) {
                neighbors.add(potential+1);
            }
        }

        for (int i = 0; i < neighbors.size(); i++) {
            if (barriers.contains(neighbors.get(i))) {
                neighbors.remove(i);
                i--;
            }
        }

        return neighbors;
    }

    public static int bfs(int s, int e, int cells) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);

        int[] dist = new int[cells+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == e) {
                return dist[curr];
            }

            for (Integer n : getNeigh(curr)) {
                if (dist[n] == Integer.MAX_VALUE) {
                    dist[n] = dist[curr] + 1;
                    queue.offer(n);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

}
