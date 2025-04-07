package JavaBasic;

import java.util.Scanner;

public class MaxMethod {

    public static int max(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for the number of elements
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        // Read array elements
        int[] vals = new int[n];
        System.out.println("Enter " + n + " integer values:");
        for (int i = 0; i < n; i++) {
            vals[i] = scanner.nextInt();
        }

        // Print maximum value
        int result = max(vals);
        System.out.println("Max value: " + result);

        scanner.close();
    }
}
