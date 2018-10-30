import java.util.Scanner;

/**
 * Created by Jamie on 10/28/2018.
 */
public class B {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int total = 0;
        int bats = 0;
        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            if (a != -1) {
                bats++;
                total += a;
            }
        }
        System.out.println(total/(double) bats);
    }
}
