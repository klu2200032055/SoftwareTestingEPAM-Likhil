package BasicJava;

import java.util.Scanner;
import java.util.Arrays;

public class SumOfPrevious {

    public static boolean[] getSumCheckArray(int[] array) {
        boolean[] result = new boolean[array.length];
        result[0] = false;
        result[1] = false;

        for (int i = 2; i < array.length; i++) {
            result[i] = array[i] == array[i - 1] + array[i - 2];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the number of elements (at least 2): ");
        int size = scanner.nextInt();
        while (size < 2) {
            System.out.printf("Array must have at least 2 elements. Enter again: ");
            size = scanner.nextInt();
        }

        int[] array = new int[size];

        System.out.printf("Enter %d integer values:\n", size);
        for (int i = 0; i < size; i++) {
            System.out.printf("Element %d: ", i);
            array[i] = scanner.nextInt();
        }

        boolean[] result = getSumCheckArray(array);

        System.out.println("Input array: " + Arrays.toString(array));
        System.out.println("Output array: " + Arrays.toString(result));

        scanner.close();
    }
}

