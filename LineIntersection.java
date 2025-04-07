package JavaBasic;

import java.util.Scanner;

public class LineIntersection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter k and b for Line 1:");
        int k1 = scanner.nextInt();
        int b1 = scanner.nextInt();

        System.out.println("Enter k and b for Line 2:");
        int k2 = scanner.nextInt();
        int b2 = scanner.nextInt();

        Line line1 = new Line(k1, b1);
        Line line2 = new Line(k2, b2);

        Point intersection = line1.intersection(line2);

        if (intersection == null) {
            System.out.println("No intersection (lines are parallel or coinciding).");
        } else {
            System.out.println("Intersection point: " + intersection);
        }

        scanner.close();
    }

    // Nested Point class
    static class Point {
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ";" + y + ")";
        }
    }

    // Nested Line class
    static class Line {
        private final int k;
        private final int b;

        public Line(int k, int b) {
            this.k = k;
            this.b = b;
        }

        public Point intersection(Line other) {
            if (this.k == other.k) {
                return null; // parallel or same
            }

            int x = (other.b - this.b) / (this.k - other.k);
            int y = this.k * x + this.b;

            return new Point(x, y);
        }
    }
}
