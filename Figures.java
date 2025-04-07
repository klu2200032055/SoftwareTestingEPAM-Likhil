import java.util.Scanner;

abstract class Figure {
    public abstract double area();
    public abstract String pointsToString();
    public abstract Point leftmostPoint();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + pointsToString() + "]";
    }
}

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class Triangle extends Figure {
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        return 0.5 * Math.abs(
                a.x * (b.y - c.y) +
                        b.x * (c.y - a.y) +
                        c.x * (a.y - b.y)
        );
    }

    public String pointsToString() {
        return a + "" + b + "" + c;
    }

    public Point leftmostPoint() {
        Point min = a;
        if (b.x < min.x) min = b;
        if (c.x < min.x) min = c;
        return min;
    }
}

class Quadrilateral extends Figure {
    private Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double area() {
        // Split into two triangles: ABC and ACD
        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(a, c, d);
        return t1.area() + t2.area();
    }

    public String pointsToString() {
        return a + "" + b + "" + c + "" + d;
    }

    public Point leftmostPoint() {
        Point[] pts = {a, b, c, d};
        Point min = pts[0];
        for (Point p : pts) {
            if (p.x < min.x) min = p;
        }
        return min;
    }
}

class Circle extends Figure {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public String pointsToString() {
        return center.toString();
    }

    public Point leftmostPoint() {
        return new Point(center.x - radius, center.y);
    }

    @Override
    public String toString() {
        return "Circle[" + pointsToString() + radius + "]";
    }
}

public class Figures {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose figure type (1 - Triangle, 2 - Quadrilateral, 3 - Circle): ");
        int choice = sc.nextInt();

        Figure figure = null;

        switch (choice) {
            case 1 -> {
                System.out.println("Enter coordinates of 3 points (x y):");
                Point a = new Point(sc.nextDouble(), sc.nextDouble());
                Point b = new Point(sc.nextDouble(), sc.nextDouble());
                Point c = new Point(sc.nextDouble(), sc.nextDouble());
                figure = new Triangle(a, b, c);
            }
            case 2 -> {
                System.out.println("Enter coordinates of 4 points (x y):");
                Point a = new Point(sc.nextDouble(), sc.nextDouble());
                Point b = new Point(sc.nextDouble(), sc.nextDouble());
                Point c = new Point(sc.nextDouble(), sc.nextDouble());
                Point d = new Point(sc.nextDouble(), sc.nextDouble());
                figure = new Quadrilateral(a, b, c, d);
            }
            case 3 -> {
                System.out.println("Enter center point (x y) and radius:");
                Point center = new Point(sc.nextDouble(), sc.nextDouble());
                double radius = sc.nextDouble();
                figure = new Circle(center, radius);
            }
            default -> System.out.println("Invalid choice!");
        }

        if (figure != null) {
            System.out.println("Figure: " + figure);
            System.out.println("Area: " + figure.area());
            System.out.println("Leftmost point: " + figure.leftmostPoint());
        }

        sc.close();
    }
}
