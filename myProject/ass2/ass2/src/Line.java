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

    /**
     * Constructor.
     * @param start - built of x and y
     * @param end   - built of x and y
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     * @param x1 - the x coordinate of the start point.
     * @param y1 - the y coordinate of the start point.
     * @param x2 - the x coordinate of the end point.
     * @param y2 - the y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
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
        return (newStart.distance(newEnd));
    }

    /**
     * Calculate the middle of the line.
     * @return a point which is the middle of the line,
     * it has values like x ad y.
     */
    public Point middle() {
        double midX = (((this.start.getX()) + (this.end.getX())) / 2);
        double midY = (((this.start.getY()) + (this.end.getY())) / 2);
        Point mid = new Point(midX, midY);
        return (mid);
    }

    /**
     * Say who is the point that starts the line.
     * @return the values of the point start.
     */
    public Point start() {
        return (this.start);
    }

    /**
     * Say who is the point that ends the line.
     * @return the values of the point end.
     */
    public Point end() {
        return (this.end);
    }

    /**
     * Checking if there is another line.
     * @param other - the other line to calculate with.
     * @return - true if there is another line, other return false.
     */
    public boolean isAnotherLine(Line other) {
        if (other == null) {
            return false;
        }
        return true;
    }

    /**
     * Answer who is the point of the intersection.
     * @param other - the line to calculate with.
     * @return null if there is no other line.
     * other, checks if there is intersection
     * and returns the point of the intersect.
     */
    public Point intersectionWith(Line other) {
        // checking if there is another line to intersect with
        if (!isAnotherLine(other)) {
            return null;
        }
        // if it's the same line there is no intersection
        if (equals(other)) {
            return null;
        }
        // using the points coordinates to build lines equations in form y = a * x + b
        // line equation of the other line
        double y1 = other.start.getY();
        double y2 = other.end.getY();
        double x1 = other.start.getX();
        double x2 = other.end.getX();
        double a1 = ((y2 - y1) / (x2 - x1));
        double b1 = (y1 - a1 * x1);
        // line equation of our line
        double y3 = this.start.getY();
        double y4 = this.end.getY();
        double x3 = this.start.getX();
        double x4 = this.end.getX();
        double a2 = ((y4 - y3) / (x4 - x3));
        double b2 = (y3 - a2 * x3);
        double x = ((b2 - b1) / (a1 - a2));
        // positioning the variable x in one of the lines equations, to get the y
        double y = (a1 * x + b1);
        Point intersection = new Point(x, y);
        // checking if the point is in the range of the lines, because they are'nt endless
        if (length(this.start(), intersection) + length(this.end(), intersection) == length(this.start(), this.end())
                && length(other.start(), intersection) + length(other.end(), intersection)
                == length(other.start(), other.end())) {
                /*
                comparison between the two lines equations, so we can get out the variable x,
                so he is the point of the intersection. a1 * x + b1 = a2 * x + b2
                */
            // if the slope of the two lines are the same, automatically they are'nt intersecting, but parallel
            if (a1 == a2) {
                    /*
                    it can be that there are two lines with the same slope
                    and they have exactly one intersection point
                    that is the one start point and the other is the end point
                    */
                if (other.start() == this.end()
                        || other.end() == this.start()
                        || other.start() == this.start()
                        || other.end() == this.end()) {
                    return (intersection);
                }
                // they are parallel, or exactly the same line
                return null;
            }
            // they are'nt have the same slope and the point is in the range of the lines
            return (intersection);
        }
        // the point is'nt in the range of the lines
        return null;
    }

    /**
     * Checking if the lines are intersecting.
     * @param other - the line to calculate with.
     * @return true if the lines has a point of intersect.
     * other, return false.
     */
    public boolean isIntersecting(Line other) {
        // checking if there is an intersection point
        if (intersectionWith(other) == null) {
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
    public boolean equals(Line other) {
        // checking if there is another line to intersect with
        if (!isAnotherLine(other)) {
            return false;
        }
        if ((this.start == other.start) && (this.end == other.end)) {
            return true;
        }
        return false;
    }
}
