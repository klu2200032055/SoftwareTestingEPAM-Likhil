package JavaBasic;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter integers (end with 0):");

        int sum = 0;
        int count = 0;

        while (true) {
            int number = scanner.nextInt();
            if (number == 0) break;
            sum += number;
            count++;
        }

        if (count > 0) {
            int average = sum / count; // Integer division
            System.out.println("Average: " + average);
        } else {
            System.out.println("No values entered.");
        }

        scanner.close();
    }
}
