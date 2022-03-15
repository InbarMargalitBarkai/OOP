import java.util.ArrayList;
import java.util.List;

/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * A Line details.
 * A line is a straight object.
 */
public class Line {
    // Fields
    private Point start;
    private Point end;
    private boolean parallelToY;
    private double[] lineEquation = new double[2]; // 0: incline 1: y

    /**
     * Constructor.
     * Set the line equation array: in first place sets the incline,
     * in the second, set the intersection point with the y axis
     * if the line parallel the y axis, set the array to null
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() == end.getX()) {
            this.lineEquation = null;
        } else {
            this.lineEquation[0] = incline();
            this.lineEquation[1] = intersectionWithY(lineEquation[0]);
        }
        this.parallelToY = (start.getX() == end.getX());
    }

    /**
     * Constructor.
     * Set the line equation array: in first place sets the incline,
     * in the second, set the intersection point with the y axis
     * if the line parallel the y axis, set the array to null
     *
     * @param x1 the x of the start point of the line
     * @param y1 the y of the start point of the line
     * @param x2 the x of the end point of the line
     * @param y2 the y of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (start.getX() == end.getX()) {
            this.lineEquation = null;
        } else {
            this.lineEquation[0] = incline();
            this.lineEquation[1] = intersectionWithY(lineEquation[0]);
        }
        this.parallelToY = (start.getX() == end.getX());
    }

    /**
     * Calculate the length of a line by using the method that calculate the distance between two points.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculate the length of the line.
     * @param newStart - the point that starts the line.
     * @param newEnd   - the point that ends the line.
     * @return a real number.
     * zero if they are the same point
     * and some positive real number
     * (because length cant be a negative number)
     * that say the length of the line.
     */
    public double length(Point newStart, Point newEnd) {
        return newStart.distance(newEnd);
    }

    /**
     * Calculate the middle of the line.
     * @return a point which is the middle of the line,
     * it has values like x ad y.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        Point mid = new Point(midX, midY);
        return mid;
    }

    /**
     * Say who is the point that starts the line.
     * @return the values of the point start.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Say who is the point that ends the line.
     * @return the values of the point end.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Gets incline.
     * @return the incline of the line
     */
    double getM() {
        return this.lineEquation[0];
    }

    /**
     * Gets x intersection.
     * @return the x of the intersection point of the line with axis y
     */
    double getB() {
        return this.lineEquation[1];
    }

    /**
     * Checks if two lines intersect with each other.
     * if the max point of one of thr lines is equal to the min point of the other (the start point and and point)
     * or vice versa, the lines intersects.
     * if both line's equation are null, the lines parallel, so not intersect
     * OR if the intersection point of the lines equation is not on both of lines,
     * there is not intersection between the lines
     *
     * @param other the second line we want to check if is intersecting
     * @return true if the lines are intersecting, false if they are not
     */
    public boolean isIntersecting(Line other) {
        if ((this.parallelToY && other.parallelToY
                //if the lines parallel to y axis checks the max/min according to y and not x
                && (minYPoint(this.start(), this.end()).equals(maxYPoint(other.start(), other.end())))
                || maxYPoint(this.start(), this.end()).equals(minYPoint(other.start(), other.end())))
                || (minXPoint(this.start(), this.end()).equals(maxXPoint(other.start(), other.end())))
                || maxXPoint(this.start(), this.end()).equals(minXPoint(other.start(), other.end()))) {
            return true;
        }
        if (this.lineEquation == other.lineEquation
                || !pointInLine(this.possibleIntersectionPoint(other))
                || !other.pointInLine(other.possibleIntersectionPoint(this))
        ) {
            return false;
        }
        //the point is on both lines
        return true;
    }

    /**
     * Checks which point has the bigger x value.
     * @param first  the start point of the line
     * @param second the end point of the line
     * @return bigger point
     */
    private Point maxXPoint(Point first, Point second) {
        if (first.getX() < second.getX()) {
            return second;
        }
        return first;
    }

    /**
     * Checks which point has the smaller x value.
     * @param first  the start point of the line
     * @param second the end point of the line
     * @return smaller point
     */
    private Point minXPoint(Point first, Point second) {
        if (first.getX() < second.getX()) {
            return first;
        }
        return second;
    }

    /**
     * Checks which point has the bigger y value.
     * @param first  the start point of the line
     * @param second the end point of the line
     * @return bigger point
     */
    private Point maxYPoint(Point first, Point second) {
        if (first.getY() < second.getY()) {
            return second;
        }
        return first;
    }

    /**
     * Checks which point has the smaller y value.
     * @param first  the start point of the line
     * @param second the end point of the line
     * @return smaller point
     */
    private Point minYPoint(Point first, Point second) {
        if (first.getY() < second.getY()) {
            return first;
        }
        return second;
    }

    /**
     * Checks if there is an intersection point between two line, and if there is returns it.
     * @param other the second line we want to check the intersection point with
     * @return the intersection point if there is,null if there isn't
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return this.possibleIntersectionPoint(other);
        }
        return null;
    }

    /**
     * Checking if the is another line.
     * @param other - the other line to calculate with.
     * @return - true if there is another line,
     * other return false.
     */
    public boolean isAnotherLine(Line other) {
        if (other == null) {
            return false;
        }
        return true;
    }

    /**
     * Checking if the two lines are equal.
     * @param other - the line to calculate with.
     * @return return true if the lines are equal,
     * false otherwise.
     */
    public boolean eqquals(Line other) {
        // checking if there is another line to intersect with
        if (!isAnotherLine(other)) {
            return false;
        }
        if ((this.start == other.start) && (this.end == other.end)) {
            return true;
        }
        return false;
    }

    /**
     * Checks where the line intersect with y axis, by the formula: y-mx=b.
     * @param m the incline of the line
     * @return the x of the point of the intersection
     */
    double intersectionWithY(double m) {
        double y = this.start.getY();
        double x = this.start.getX();
        return (y - m * x);
    }

    /**
     * Gives possible intersection point of two lines, according to the equation y=mx+b.
     * place the parameters in the equation, and find the x and y of the intersection point.
     * @param other the second line to check intersection with
     * @return the intersection point (according to the equations of the lines, not the lines)
     */
    Point possibleIntersectionPoint(Line other) {
        double m1;
        double y;
        double x;
        if (this.lineEquation == null) {
            m1 = other.getM();
            x = this.start.getX();
            y = m1 * x + other.getB();
            Point inPoint = new Point(x, y);
            return inPoint;
        } else if (other.lineEquation == null) {
            m1 = this.getM();
            x = other.start.getX();
            y = m1 * x + this.getB();
            Point inPoint = new Point(x, y);
            return inPoint;
        } else { //if non is null
            m1 = this.getM();
            double m2 = other.getM();
            double b1 = this.getB();
            double b2 = other.getB();
            x = (b2 - b1) / (m1 - m2);
            y = (m1 * x + this.getB());
            Point inPoint = new Point(x, y);
            return inPoint;
        }
    }

    /**
     * Find all the intersections points between a rectangle and a line, returns the closest one.
     * @param rect the rectangle  to find the intersections points with
     * @return the closet intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = new ArrayList<Point>();
        list = rect.intersectionPoints(this);
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else { //there are 2 points
            if (this.start.distance(list.get(0)) < this.start.distance(list.get(1))) {
                return list.get(0);
            } else {
                return list.get(1);
            }
        }
    }

    /**
     * Calculate the incline of a line by two points on the line.
     * @return the incline of the line
     */
    public double incline() {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double m = (y1 - y2) / (x1 - x2);
        return m;
    }


    /**
     * Checks if a point is on a line.
     * If the distance between the start point and the given point
     * + the distance end the start point and the given point
     * = thd distance between the start point and the end point.
     * @param p the point to check if it is on the line
     * @return true if the point is on the line, false if it isn't
     */
    public boolean pointInLine(Point p) {
        double epsilon = Math.pow(1, -14);
        if (Math.abs(this.start.distance(p) + this.end.distance(p) - this.start.distance(this.end)) < epsilon) {
            return true;
        }
        return false;
    }
}
