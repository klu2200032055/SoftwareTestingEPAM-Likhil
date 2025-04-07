package JavaBasic;

import java.util.Scanner;

public class DirectionDemo {
    public enum Direction {
        N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

        private final int degrees;

        Direction(int degrees) {
            this.degrees = degrees;
        }

        public int getDegrees() {
            return degrees;
        }

        public static Direction ofDegrees(int degrees) {
            degrees = normalize(degrees);
            for (Direction dir : values()) {
                if (dir.degrees == degrees) return dir;
            }
            return null;
        }

        public static Direction closestToDegrees(int degrees) {
            degrees = normalize(degrees);
            Direction closest = null;
            int minDiff = 360;
            for (Direction dir : values()) {
                int diff = Math.abs(degrees - dir.degrees);
                diff = Math.min(diff, 360 - diff); // circular wrap
                if (diff < minDiff) {
                    minDiff = diff;
                    closest = dir;
                }
            }
            return closest;
        }

        public Direction opposite() {
            return ofDegrees(degrees + 180);
        }

        public int differenceDegreesTo(Direction other) {
            int diff = Math.abs(this.degrees - other.degrees);
            return Math.min(diff, 360 - diff);
        }

        private static int normalize(int degrees) {
            degrees %= 360;
            if (degrees < 0) degrees += 360;
            return degrees;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // First input: for ofDegrees and closestToDegrees
        System.out.print("Enter a degree (int): ");
        int deg = scanner.nextInt();

        Direction exact = Direction.ofDegrees(deg);
        Direction closest = Direction.closestToDegrees(deg);

        System.out.println("Exact direction (if any): " + (exact != null ? exact : "None"));
        System.out.println("Closest direction: " + closest);
        System.out.println("Opposite of closest: " + closest.opposite());

        // Second input: for direction difference
        System.out.print("Enter second degree for difference calculation: ");
        int deg2 = scanner.nextInt();
        Direction d1 = Direction.closestToDegrees(deg);
        Direction d2 = Direction.closestToDegrees(deg2);

        System.out.println("Direction 1: " + d1);
        System.out.println("Direction 2: " + d2);
        System.out.println("Difference in degrees: " + d1.differenceDegreesTo(d2));
    }
}
