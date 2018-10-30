import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Jamie on 10/28/2018.
 */
public class G {

    static ArrayList<Integer>[] forcedGraph;
    static ArrayList<Integer>[] buggyGraph;
    static HashSet<State> visited = new HashSet<>();
    static HashSet<Integer> ends = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        forcedGraph = new ArrayList[n];
        buggyGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            forcedGraph[i] = new ArrayList<>();
            buggyGraph[i] = new ArrayList<>();
        }

        for (int i =0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            if (a < 0) {
                forcedGraph[Math.abs(a) - 1].add(b-1);
            }
            else {
                buggyGraph[a-1].add(b-1);
            }
        }

        dfs(0, false);
        System.out.println(ends.size());
    }

    static void dfs(int curr, boolean hasBugged) {
        visited.add(new State(curr, hasBugged));

        if (forcedGraph[curr].isEmpty()) {
            ends.add(curr);
        }

        for (Integer n : forcedGraph[curr]) {
            if (!visited.contains(new State(n, hasBugged))) {
                dfs(n, hasBugged);
            }
        }
        if (!hasBugged) {
            for (Integer n : buggyGraph[curr]) {
                if (!visited.contains(new State(n, true))){
                    dfs(n, true);
                }
            }
        }
    }

    static class State {
        int a;
        boolean hasBugged;

        State(int a, boolean hasBugged) {
            this.a = a;
            this.hasBugged = hasBugged;
        }

        @Override
        public boolean equals(Object o) {
            State other = (State) o;
            return this.a == other.a && this.hasBugged == other.hasBugged;
        }

        @Override
        public int hashCode() {
            int total = a;
            if (hasBugged) {
                total += 1000000;
            }
            return total;
        }
    }
}
