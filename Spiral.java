package BasicJava;

import java.util.Scanner;

public class Spiral {

    public static int[][] spiral(int rows, int columns) {
        int[][] result = new int[rows][columns];
        int top = 0, bottom = rows - 1;
        int left = 0, right = columns - 1;
        int num = 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[bottom][i] = num++;
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = num++;
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.printf("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] spiralMatrix = spiral(rows, cols);

        System.out.println("\nSpiral Matrix:");
        for (int i = 0; i < spiralMatrix.length; i++) {
            for (int j = 0; j < spiralMatrix[i].length; j++) {
                System.out.printf("%4d", spiralMatrix[i][j]);
            }
            System.out.println();
        }

        scanner.close();
    }
}
