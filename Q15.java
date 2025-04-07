package BasicJava;

public class Q15 {
    public static void main(String[] args) {
        Segment segment = new Segment(new Point(2, 0), new Point(0, 2));
        System.out.println("Length: " + segment.length());

        Point mid = segment.middle();
        System.out.println("Middle: (" + mid.getX() + ", " + mid.getY() + ")");

        Segment s1 = new Segment(new Point(0, 0), new Point(4, 4));
        Segment s2 = new Segment(new Point(2, 0), new Point(0, 2));
        Point inter = s1.intersection(s2);
        if (inter != null) {
            System.out.println("Intersection: (" + inter.getX() + ", " + inter.getY() + ")");
        } else {
            System.out.println("No intersection");
        }
    }
}

