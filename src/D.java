import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Jamie on 10/28/2018.
 */
public class D {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HashSet<String> found = new HashSet<>();
        while (scan.hasNext()) {
            String s = scan.next();
            if (found.contains(s)) {
                System.out.println("no");
                System.exit(0);
            }
            found.add(s);
        }
        System.out.println("yes");
    }
}
