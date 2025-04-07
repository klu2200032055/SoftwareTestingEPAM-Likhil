package BasicJava;


public class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Segment cannot be a point.");
        }
        this.start = start;
        this.end = end;
    }

    public double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point middle() {
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0;
        return new Point(mx, my);
    }

    public Point intersection(Segment another) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();

        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

        if (denom == 0) {
            return null; // Lines are parallel or collinear
        }

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;

        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            double ix = x1 + ua * (x2 - x1);
            double iy = y1 + ua * (y2 - y1);
            return new Point(ix, iy);
        }

        return null; // Intersection not within segment bounds
    }
}
