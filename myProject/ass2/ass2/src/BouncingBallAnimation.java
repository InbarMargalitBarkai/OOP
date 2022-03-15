import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * Creating one bouncing ball,
 * with information from the commandline.
 */
public class BouncingBallAnimation {
    // constant for the pause
    static final int TIME = 50;
    // num of args we need to get from the main method
    static final int NUM_ARGS = 4;
    // constant fot the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant fot the height of the screen
    static final int HEIGHT_SCREEN = 600;
    // the left point of the screen is (0,0)
    static final int START_SCREEN = 0;
    static final int RADIUS = 30;

    /**
     * Drawing the ball on the screen.
     * @param start - the point of the ball
     * @param dx - the change in position on the 'x' axe.
     * @param dy - the change in position on the 'y' axe.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        // creating the window
        GUI gui = new GUI("BouncingBallAnimation", WIDTH_SCREEN, HEIGHT_SCREEN);
        Sleeper sleeper = new Sleeper();
        // creating ball with the information
        Ball ball = new Ball(start, RADIUS, java.awt.Color.BLACK);
        ball.setBoardLeft(START_SCREEN, START_SCREEN);
        ball.setBoardRight(WIDTH_SCREEN, HEIGHT_SCREEN);
        // checking if the first position of the ball include the radius within the boundaries of the screen
        if (ball.getX() + RADIUS >= ball.getRightSide().getX() || ball.getX() - RADIUS <= ball.getLeftSide().getX()) {
          // in this case we can choose what will happen and how to deal with it
          Point newPoint = new Point(50, ball.getY());
          // I choosed a nw decision center of the point
          ball.setCenter(newPoint);
        }
        if (ball.getY() + RADIUS >= ball.getRightSide().getY() || ball.getY() - RADIUS <= ball.getLeftSide().getY()) {
            Point newPoint = new Point(ball.getX(), 50);
            ball.setCenter(newPoint);
        }
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds
            sleeper.sleepFor(TIME);
        }
    }

        /**
         * Getting the ball information to invoke the drawing.
         * @param args - string that keeps the information of the ball.
         */
        public static void main(String[] args) {
            int numOfArgs = args.length;
            // checking if we got the correct num of arguments to display this program
            if (numOfArgs != NUM_ARGS) {
                throw new RuntimeException("It's not a real number.");
            } else {
                // getting the information from the commandline and saving him
                double x = (double) Integer.parseInt(args[0]);
                double y = (double) Integer.parseInt(args[1]);
                double dx = (double) Integer.parseInt(args[2]);
                double dy = (double) Integer.parseInt(args[3]);
                Point center = new Point(x, y);
                // sending the information to create the ball
                drawAnimation(center, dx, dy);
            }
        }
    }
