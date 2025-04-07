package JavaBasic;

import java.util.Scanner;

public class FindMaxInSeq {

    public static int max() {
        Scanner scanner = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        int number;

        while (true) {
            number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            if (number > max) {
                max = number;
            }
        }

        scanner.close();
        return max;
    }

    public static void main(String[] args) {
        // Run and print the result of max
        System.out.println(max());
    }
}
