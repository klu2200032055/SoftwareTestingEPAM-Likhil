package BasicJava;

import java.util.Scanner;

public class Snail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the distance snail climbs during the day (a): ");
        int a = scanner.nextInt();

        System.out.printf("Enter the distance snail slides down during the night (b): ");
        int b = scanner.nextInt();

        System.out.printf("Enter the height of the tree (h): ");
        int h = scanner.nextInt();

        if (a >= h) {
            System.out.println(1);
        } else if (a <= b) {
            System.out.println("Impossible");
        } else {
            int effectiveGain = a - b;
            int remainingHeight = h - a;
            int days = (int)Math.ceil((double)remainingHeight / effectiveGain) + 1;
            System.out.println(days);
        }
    }
}

