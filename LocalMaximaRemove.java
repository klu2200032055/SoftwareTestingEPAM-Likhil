package JavaBasic;

import java.util.*;

public class LocalMaximaRemove {

    public static int[] removeLocalMaxima(int[] array) {
        List<Integer> result = new ArrayList<>();

        int n = array.length;

        for (int i = 0; i < n; i++) {
            boolean isLocalMax = false;

            if (i == 0) {
                if (array[i] > array[i + 1]) {
                    isLocalMax = true;
                }
            } else if (i == n - 1) {
                if (array[i] > array[i - 1]) {
                    isLocalMax = true;
                }
            } else {
                if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
                    isLocalMax = true;
                }
            }

            if (!isLocalMax) {
                result.add(array[i]);
            }
        }

        // Convert List<Integer> to int[]
        int[] output = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }

        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] input = new int[n];
        System.out.println("Enter " + n + " integer values:");
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        int[] result = removeLocalMaxima(input);

        System.out.println("Array after removing local maxima:");
        System.out.println(Arrays.toString(result));

        scanner.close();
    }
}
