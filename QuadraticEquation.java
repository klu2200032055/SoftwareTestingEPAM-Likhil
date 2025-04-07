package BasicJava;

import java.util.Scanner;

public class QuadraticEquation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println(root1 + " " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println(root);
        } else {
            System.out.println("no roots");
        }

        scanner.close();
    }
}

