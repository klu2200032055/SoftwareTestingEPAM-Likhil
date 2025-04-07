package JavaBasic;

import java.util.Scanner;

public class FiguresExtraChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose figure type: triangle, circle, quadrilateral");
        String figureType = scanner.next().toLowerCase();

        Figure figure = null;

        try {
            switch (figureType) {
                case "triangle":
                    System.out.println("Enter 3 points (x y):");
                    Point ta = readPoint(scanner);
                    Point tb = readPoint(scanner);
                    Point tc = readPoint(scanner);
                    figure = new Triangle(ta, tb, tc);
                    break;

                case "circle":
                    System.out.println("Enter center point (x y) and radius:");
                    Point center = readPoint(scanner);
                    double radius = scanner.nextDouble();
                    figure = new Circle(center, radius);
                    break;

                case "quadrilateral":
                    System.out.println("Enter 4 points (x y):");
                    Point qa = readPoint(scanner);
                    Point qb = readPoint(scanner);
                    Point qc = readPoint(scanner);
                    Point qd = readPoint(scanner);
                    figure = new Quadrilateral(qa, qb, qc, qd);
                    break;

                default:
                    System.out.println("Invalid figure type");
                    return;
            }

            System.out.println("Figure centroid: " + figure.centroid());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid figure: " + e.getMessage());
        }
    }

    private static Point readPoint(Scanner scanner) {
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        return new Point(x, y);
    }
}

// ---------- Classes below ----------

abstract class Figure {
    protected static final double EPSILON = 1E-5;

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    protected static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}

class Point {
    public final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point other) {
        return Figure.doubleEquals(this.x, other.x) && Figure.doubleEquals(this.y, other.y);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class Triangle extends Figure {
    private final Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null || area(a, b, c) < EPSILON)
            throw new IllegalArgumentException("Degenerate triangle");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private static double area(Point a, Point b, Point c) {
        return 0.5 * Math.abs(a.x*(b.y - c.y) + b.x*(c.y - a.y) + c.x*(a.y - b.y));
    }

    public Point centroid() {
        return new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
    }

    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Triangle)) return false;
        Triangle other = (Triangle) figure;
        Point[] pts1 = {a, b, c};
        Point[] pts2 = {other.a, other.b, other.c};
        return haveSamePoints(pts1, pts2);
    }

    private boolean haveSamePoints(Point[] p1, Point[] p2) {
        for (Point pt1 : p1) {
            boolean found = false;
            for (Point pt2 : p2) {
                if (pt1.equals(pt2)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}

class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        if (center == null || radius <= EPSILON)
            throw new IllegalArgumentException("Invalid circle");
        this.center = center;
        this.radius = radius;
    }

    public Point centroid() {
        return center;
    }

    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Circle)) return false;
        Circle other = (Circle) figure;
        return center.equals(other.center) && doubleEquals(radius, other.radius);
    }
}

class Quadrilateral extends Figure {
    private final Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null)
            throw new IllegalArgumentException("Points cannot be null");

        if (!isConvex(a, b, c, d) || isDegenerate(a, b, c, d))
            throw new IllegalArgumentException("Degenerate or non-convex quadrilateral");

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private boolean isDegenerate(Point a, Point b, Point c, Point d) {
        try {
            new Triangle(a, b, c);
            new Triangle(b, c, d);
            new Triangle(c, d, a);
            new Triangle(d, a, b);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean isConvex(Point a, Point b, Point c, Point d) {
        return crossProductSign(a, b, c, d);
    }

    private boolean crossProductSign(Point a, Point b, Point c, Point d) {
        return isSameSign(cross(a, b, c), cross(b, c, d)) &&
                isSameSign(cross(b, c, d), cross(c, d, a)) &&
                isSameSign(cross(c, d, a), cross(d, a, b));
    }

    private double cross(Point p1, Point p2, Point p3) {
        double dx1 = p2.x - p1.x;
        double dy1 = p2.y - p1.y;
        double dx2 = p3.x - p2.x;
        double dy2 = p3.y - p2.y;
        return dx1 * dy2 - dx2 * dy1;
    }

    private boolean isSameSign(double a, double b) {
        return a * b >= -EPSILON;
    }

    public Point centroid() {
        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(a, c, d);

        double area1 = area(a, b, c);
        double area2 = area(a, c, d);

        Point g1 = t1.centroid();
        Point g2 = t2.centroid();

        double cx = (g1.x * area1 + g2.x * area2) / (area1 + area2);
        double cy = (g1.y * area1 + g2.y * area2) / (area1 + area2);
        return new Point(cx, cy);
    }

    private static double area(Point a, Point b, Point c) {
        return 0.5 * Math.abs(a.x*(b.y - c.y) + b.x*(c.y - a.y) + c.x*(a.y - b.y));
    }

    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Quadrilateral)) return false;
        Quadrilateral q = (Quadrilateral) figure;
        Point[] p1 = {a, b, c, d};
        Point[] p2 = {q.a, q.b, q.c, q.d};

        for (Point pt1 : p1) {
            boolean found = false;
            for (Point pt2 : p2) {
                if (pt1.equals(pt2)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}

//FiguresExtraChallenge