import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
 */

/**
 * Creating rectangle object.
 * Our Rectangles will all be aligned with the axes.
 */
public class Rectangle {
    // exponent for pow equation
    static final int EXPONENT = 2;

    // Fields
    private Point upperLeft;
    private double width;
    private double height;
    // creating the coordinates of the rectangle vertex
    private double upperY;
    private double lowerY;
    private double leftX;
    private double rightX;

    /**
     * Constructor.
     * Create a new rectangle with location and width/ height.
     *
     * @param upperLeft - the location of the rectangle
     * @param width     - the width of the rectangle, that defines the x axe.
     * @param height    - the height of the rectangle, that defines the y axe.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperY = this.upperLeft.getY();
        this.lowerY = upperY + this.height;
        this.leftX = this.upperLeft.getX();
        this.rightX = leftX + this.width;
    }

    /**
     * Update the left upper point.
     */
    public void updatePoint() {
        this.upperY = this.upperLeft.getY();
        this.lowerY = upperY + this.height;
        this.leftX = this.upperLeft.getX();
        this.rightX = leftX + this.width;
    }

    /**
     * Update the upper point.
     */
    public void updateUpper() {
        this.upperLeft = new Point(this.leftX, this.upperY);
    }

    /**
     * Checking intersection points and save then on array.
     * @param line - line to check the intersection points.
     * @return - return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listOfPoints = new ArrayList<Point>();
        // creating the upper edge of the rectangle
        Line tempLine = new Line(leftX, upperY, rightX, upperY);
        // checking if the lines intersect
        if (line.isIntersecting(tempLine)) {
            // adding the point of intersect
            listOfPoints.add(line.intersectionWith(tempLine));
        }
        // creating the lower edge of the rectangle
        tempLine = new Line(leftX, lowerY, rightX, lowerY);
        if (line.isIntersecting(tempLine)) {
            listOfPoints.add(line.intersectionWith(tempLine));
        }
        // creating the left edge of the rectangle
        tempLine = new Line(leftX, upperY, leftX, lowerY);
        if (line.isIntersecting(tempLine)) {
            listOfPoints.add(line.intersectionWith(tempLine));
        }
        // creating the right edge of the rectangle
        tempLine = new Line(rightX, upperY, rightX, lowerY);
        if (line.isIntersecting(tempLine)) {
            listOfPoints.add(line.intersectionWith(tempLine));
        }
        // checking how many intersection points we have
        if (listOfPoints.size() == 0) {
            return null;
        } else {
            return listOfPoints;
        }
    }

    /**
     * Checking if there is a hit between the ball and the corners of the rectangle, by distance.
     * @param center - the center of the ball.
     * @param size   - the radius of the ball.
     * @return - if there is intersection:
     * returns point which will be the point of intersection with the ball and the corner of the rectangle.
     * else, return null.
     */
    public Point hitPointCorner(Point center, int size) {
        // the maximum distance we want between the points
        double maxDis = Math.pow(size, EXPONENT);
        // the hit point is in the left up corner of the rectangle
        Point hit1 = new Point(this.leftX, this.upperY),
                // the hit point is in the right up corner of the rectangle
                hit2 = new Point(this.rightX, this.upperY),
                // the hit point is in the left low corner of the rectangle
                hit3 = new Point(this.leftX, this.lowerY),
                // the hit point is in the right low corner of the rectangle
                hit4 = new Point(this.rightX, this.lowerY);
        List<Point> pointList = new ArrayList<Point>();
        pointList.addAll(Arrays.asList(hit1, hit2, hit3, hit4));
        for (int i = 0; i < pointList.size(); ++i) {
            if (isClose(pointList.get(i), center, maxDis)) {
                return (pointList.get(i));
            }
        }
        // there is no close point hit
        return null;
    }

    /**
     * Checking if the distance between the points is close or not.
     * @param hit    - the point we suspect that it's the hit.
     * @param center - the point center of the ball.
     * @param maxDis - the max distance we want between the points.
     * @return - true if the distance is close.
     * Other, false.
     */
    public boolean isClose(Point hit, Point center, double maxDis) {
        if (hit.distance(center) <= maxDis) {
            return true;
        }
        return false;
    }

    /**
     * Gets width.
     * @return - return the width of the rectangle.
     */
    public double getWidth() {
        return (this.width);
    }

    /**
     * Gets height.
     * @return - return the height of the rectangle.
     */
    public double getHeight() {
        return (this.height);
    }

    /**
     * Sets the upper point of the rectangle.
     * @param x - the x coordinate of the point.
     * @param y - the y coordinate of the point.
     */
    public void setUpperPoint(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * Gets upper y coordinate.
     * @return - return the y coordinate of the above of the rectangle.
     */
    public double getUpperY() {

        return (this.upperY);
    }

    /**
     * Sets the upper y coordinate.
     * @param newUpperY - the new upper y coordinate.
     */
    public void setUpperY(double newUpperY) {
        this.upperY = newUpperY;
        updateUpper();
    }

    /**
     * Gets lower y coordinate.
     * @return - return the y coordinate of the bottom of the rectangle.
     */
    public double getLowerY() {
        return (this.lowerY);
    }

    /**
     * Sets the lower y coordinate.
     * @param newLowerY - the new lower y coordinate.
     */
    public void setLowerY(double newLowerY) {
        this.lowerY = newLowerY;
        updateUpper();
    }

    /**
     * Gets left x coordinate.
     * @return - return the x coordinate of the left side of the rectangle.
     */
    public double getLeftX() {
        return (upperLeft.getX());
    }

    /**
     * Sets the left x coordinate.
     * @param newLeftX - the new left x coordinate.
     */
    public void setLeftX(double newLeftX) {
        this.leftX = newLeftX;
        updateUpper();
    }

    /**
     * Gets right x coordinate.
     * @return - return the x coordinate of the right side of the rectangle.
     */
    public double getRightX() {
        return (this.rightX);
    }

    /**
     * Sets the right x coordinate.
     * @param newRightX - the new right x coordinate.
     */
    public void setRightX(double newRightX) {
        this.rightX = newRightX;
        updateUpper();
    }

    /**
     * Gets upper left point.
     * @return - returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return (this.upperLeft);
    }

    /**
     * Sets upper left point.
     * @param newUpperLeft - the point of the up left edge of the rectangle.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
        updatePoint();
    }
}


