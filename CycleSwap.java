package JavaBasic;

import java.util.Arrays;
import java.util.Scanner;

public class CycleSwap {

    public static void cycleSwap(int[] array) {
        if (array == null || array.length == 0) return;

        int last = array[array.length - 1];
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = last;
    }

    public static void cycleSwap(int[] array, int shift) {
        if (array == null || array.length == 0 || shift == 0) return;

        int len = array.length;
        shift %= len;

        if (shift == 0) return;

        int[] copy = Arrays.copyOf(array, len);

        for (int i = 0; i < len; i++) {
            array[(i + shift) % len] = copy[i];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read array size
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Empty array.");
            return;
        }

        // Read array elements
        int[] array = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Ask for shift value
        System.out.print("Enter shift (0 for default shift by 1): ");
        int shift = scanner.nextInt();

        if (shift == 0) {
            cycleSwap(array);
        } else {
            cycleSwap(array, shift);
        }

        // Print result
        System.out.println("Shifted array: " + Arrays.toString(array));
    }
}
