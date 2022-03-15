import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
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
    // Fields
    private Point upperLeft;
    private Point bottomLeft;
    private Point upperRight;
    private Point bottomRight;
    private double width;
    private double height;

    /**
     * Constructor.
     * @param upperLeft - the location of the rectangle
     * @param width     - the width of the rectangle, that defines the x axe.
     * @param height    - the height of the rectangle, that defines the y axe.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double upperY = this.upperLeft.getY();
        double lowerY = upperY + this.height;
        double leftX = this.upperLeft.getX();
        double rightX = leftX + this.width;
        this.bottomLeft = new Point(leftX, lowerY);
        this.upperRight = new Point(rightX, upperY);
        this.bottomRight = new Point(rightX, lowerY);
    }

    /**
     * Find all the intersection points between a line and the rectangle,
     * put them in a list and returns is.
     * @param line the line to check the intersection with
     * @return a list of all the intersections points
     */
    public List<Point> intersectionPoints(Line line) {
        List list = new ArrayList<Point>();
        Line top = new Line(this.getUpperRight(), this.getUpperLeft());
        Line bottom = new Line(this.getBottomRight(), this.getBottomLeft());
        Line left = new Line(this.getUpperLeft(), this.getBottomLeft());
        Line right = new Line(this.getUpperRight(), this.getBottomRight());
        if (top.isIntersecting(line)) {
            list.add(top.intersectionWith(line));
        }
        if (bottom.isIntersecting(line)) {
            list.add(bottom.intersectionWith(line));
        }
        if (left.isIntersecting(line)) {
            list.add(left.intersectionWith(line));
        }
        if (right.isIntersecting(line)) {
            list.add(right.intersectionWith(line));
        }
        return list;
    }

    /**
     * Gets width.
     * @return - return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     * @return - return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets bottom left point.
     * @return the bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * Gets upper right point.
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /***
     * Gets upper left point.
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets bottom right point.
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    /**
     * Draw the rectangle on a surface with a frame.
     * @param d the draw surface to draw on
     */
    public void drawRectangle(DrawSurface d) {
        int x = (int) this.upperLeft.getX();
        int y = (int) this.upperLeft.getY();
        int recHeight = (int) this.height;
        int recWidth = (int) this.width;
        d.fillRectangle(x, y, recWidth, recHeight);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, recWidth, recHeight);
    }

    /**
     * Set the rectangle in a new location, and update all the corners.
     * @param newPoint the new upper left point of the new location of the rectangle.
     */
    public void setRectangleLocation(Point newPoint) {
        this.getUpperLeft().setPoint(newPoint);
        this.getUpperRight().setPoint(new Point(newPoint.getX() + this.getWidth(), newPoint.getY()));
        this.getBottomLeft().setPoint(new Point(newPoint.getX(), newPoint.getY() + this.getHeight()));
        this.getBottomRight().setPoint(new Point(newPoint.getX() + this.getWidth(),
                newPoint.getY() + this.getHeight()));
    }
}