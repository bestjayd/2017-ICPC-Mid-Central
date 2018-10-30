import java.util.*;

/**
 * Created by Jamie on 10/28/2018.
 */
public class I {

    static HashMap<Integer, Double> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        Interval[] sortedIntervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            int s = scan.nextInt();
            int e1 = scan.nextInt();
            int e2 = scan.nextInt();
            sortedIntervals[i] = new Interval(s, e1, e2);
        }
        Arrays.sort(sortedIntervals);
        System.out.println(opt(0, sortedIntervals));
    }

    public static double opt(int curr, Interval[] intervals) {
        if (cache.containsKey(curr)) {
            return cache.get(curr);
        }
        if (curr >= intervals.length) {
            return 0.0;
        }
        double expectation = 0;
        //Go here and end short
        //Find next interval after ending short
        int diff = intervals[curr].e2 - intervals[curr].e1 + 1;

        if (diff > 0) {
            double prob = (1/(double) diff);
            int next = curr;
            int accountedFor = 0;
            int prev = intervals[curr].e1;
            while (next < intervals.length && intervals[next].s < intervals[curr].e2) {
                if (intervals[next].s >= intervals[curr].e1) {
                    accountedFor += (intervals[next].s - prev + 1);
                    double thisProb = prob * (intervals[next].s - prev + 1);
                    double temp = 1 + opt(next, intervals);
                    expectation += thisProb * temp;
                    prev = intervals[next].s + 1;
                }
                next++;
            }
            expectation += prob * (diff - accountedFor) * (1 + opt(next, intervals));
        }

//        double expectation2 = 0;
//        if (diff > 0) {
//            double prob = (1/(double) diff);
//            for (int i = 0; i < diff; i++) {
//                int next = curr;
//                int possibleEnd = (intervals[curr].e1 + i);
//                while (next < intervals.length && intervals[next].s < possibleEnd) {
//                    next++;
//                }
//                expectation2 += prob * (1 + opt(next, intervals));
//            }
//        }
        double ans = Math.max(opt(curr+1, intervals), expectation);
        cache.put(curr, ans);
        return ans;
    }
    static class Interval implements Comparable<Interval>{
        int s;
        int e1;
        int e2;

        Interval(int s, int e1, int e2) {
            this.s = s;
            this.e1 = s + e1;
            this.e2 = s + e2;
        }

        @Override
        public int compareTo(Interval o) {
            if (this.s == o.s) {
                if (this.e1 == o.e1) {
                    return Integer.compare(this.e2, o.e2);
                }
                else {
                    return Integer.compare(this.e1, o.e1);
                }
            }
            else {
                return Integer.compare(this.s, o.s);
            }
        }
    }
}
