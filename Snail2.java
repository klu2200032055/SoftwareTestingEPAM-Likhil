package BasicJava;

import java.util.Scanner;

public class Snail2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter feet the snail climbs during the day (a): ");
        int a = scanner.nextInt();

        System.out.printf("Enter feet the snail slides down during the night (b): ");
        int b = scanner.nextInt();

        System.out.printf("Enter height of the tree (h): ");
        int h = scanner.nextInt();

        if (a >= h) {
            System.out.println(1);
        } else if (a <= b) {
            System.out.println("Impossible");
        } else {
            int effectiveDailyClimb = a - b;
            int remainingHeight = h - a;
            int days = (int) Math.ceil((double) remainingHeight / effectiveDailyClimb) + 1;
            System.out.println(days);
        }

        scanner.close();
    }
}
