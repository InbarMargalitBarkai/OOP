import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * Creating and drawing ten lines
 * with middle and intersection points.
 */
public class AbstractArtDrawing {
    // our constants
    // const variable for the numbers of the lines
    static final int LINES  = 10;
    // const variable for the radius of the circles
    static final int RADIUS  = 3;

    /**
     * @return a random line that we create
     * and saving his start and end points.
     */
    public Line generateRandomLine() {
            // create a random-number generator
            Random rand = new Random();
            // get number in range 1 - 200
            double xStart = rand.nextInt(200) + 1;
            // get number in range 1 - 200
            double yStart = rand.nextInt(200) + 1;
            // get number in range 1 - 200
            double xEnd = rand.nextInt(200) + 1;
            // get number in range 1 - 200
            double yEnd = rand.nextInt(200) + 1;
            Point start = new Point(xStart, yStart);
            Point end = new Point(xEnd, yEnd);
            // creating the new line.
            Line l = new Line(start, end);
            return l;
    }

    /**
     * Drawing the random lines that we made in generateRandomLine method
     * and also drawing their middle and intersection points,
     * and display the lines with the points.
     */
    public void drawRandom() {
        // create a random number generator
        Random rand = new Random();
        // creating a list that we will keep the lines we make
        List<Line> linesKeeper = new ArrayList<Line>();
        // create a window with the title "Random Circles Example"
        // which is 200 pixels wide and 200 pixels high.
        GUI gui = new GUI("Random Circles Example", 200, 200);
        DrawSurface d = gui.getDrawSurface();
        // creating ten lines
        for (int i = 0; i < LINES; ++i) {
            // entering the lines that creates into the array
            linesKeeper.add(i, generateRandomLine());
            d.setColor(Color.BLACK);
            // we need to do casting because this method gets integers and our coordinates are double
            // drawing the lines that we made
            int xStart = (int) linesKeeper.get(i).start().getX();
            int yStart = (int) linesKeeper.get(i).start().getY();
            int xEnd = (int) linesKeeper.get(i).end().getX();
            int yEnd = (int) linesKeeper.get(i).end().getY();
            d.drawLine(xStart, yStart, xEnd, yEnd);
            Line ln = new Line(xStart, yStart, xEnd, yEnd);
            linesKeeper.add(ln);
            // creating the middle points
            // checking the line length if it's legal
            Point s = linesKeeper.get(i).start();
            Point e = linesKeeper.get(i).end();
            if (linesKeeper.get(i).length(s, e) != 0) {
                // the middle points need to be blue
                d.setColor(Color.BLUE);
                // radius of the points need to be three
                int r = RADIUS;
                Point mid = ln.middle();
                // the x coordinate of the middle point
                int xMid = ((int) mid.getX());
                // the y coordinate of the middle point
                int yMid = ((int) mid.getY());
                d.fillCircle(xMid, yMid, r);
            }
        }
        for (int j = 0; j < LINES; ++j) {
            for (int k = 0; k < LINES; ++k) {
                // checking if their is intersection point between the lines
                if (linesKeeper.get(j).isIntersecting(linesKeeper.get(k))) {
                    // radius of the points need to be three
                    int r = RADIUS;
                    Point intersect = linesKeeper.get(j).intersectionWith(linesKeeper.get(k));
                    // the x coordinate of the middle point
                    int x = (int) intersect.getX();
                    // the y coordinate of the middle point
                    int y = (int) intersect.getY();
                    // the intersection points need to be red
                    d.setColor(Color.RED);
                    d.fillCircle(x, y, r);
                }
            }
        }
        gui.show(d);
    }

    /**
     * Play all the methods by call to drawRandom method which call to generateRandomLine.
     * @param args string of parameters we get from the user.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandom();
    }
}