import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * assignment 2.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-05
 */

/**
 * Creating two frames and bounce balls in their.
 */
public class MultipleFramesBouncingBallsAnimation {
    // our constants
    // one frame is a rectangle from (50, 50)
    static final int START_FIRST = 50;
    // one frame is a rectangle to (500, 500)
    static final int END_FIRST = 500;
    // second frame is a rectangle from (450,450)
    static final int START_SECOND = 450;
    // second frame is a rectangle to (600,600)
    static final int END_SECOND = 600;
    // constant fot the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant fot the height of the screen
    static final int HEIGHT_SCREEN = 600;
    // constant for the pause
    static final int TIME = 50;

    /**
     * Storing the balls that create in an array,
     * and converting the object that in num into balls with more information about balls.
     * @param num getting an array of the sizes of the ball.
     * @return array of balls that we create.
     */
    public static Ball[] createsBalls(String[] num) {
        // number of the arguments in the string we got
        int numOfArgs = num.length;
        int halfNumOfArgs = (numOfArgs / 2);
        Ball[] ballsKeeper = new Ball[numOfArgs];
        // enters the first half of balls into the array we made
        for (int i = 0; i < halfNumOfArgs; ++i) {
            // we want to create random information about the ball
            Random rand = new Random();
            // the radius of the ball, we get from the string
            int r = Integer.parseInt(num[i]);
            // creating random coordinate for the point, in range (50 + r) - (500 - r)
            int x = (rand.nextInt((END_FIRST - START_FIRST) - 2 * r) + START_FIRST + r);
            int y = (rand.nextInt((END_FIRST - START_FIRST) - 2 * r) + START_FIRST + r);
            Point center = new Point(x, y);
            // making a different color for each ball
            java.awt.Color color = MultipleBouncingBallsAnimation.getRandomColor();
            ballsKeeper[i] = new Ball(center, r, color);
            // creating speed for making velocity
            double speed = MultipleBouncingBallsAnimation.setSpeed(r);
            // creating angel for making velocity, in range 1 - 360
            double angle = (rand.nextInt(360) + 1);
            // creating velocity for the ball
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsKeeper[i].setVelocity(v);
        }
        // enters the second half of balls into the array we made
        for (int j = halfNumOfArgs; j < numOfArgs; ++j) {
            // we want to create random information about the ball
            Random rand = new Random();
            // the radius of the ball, we get from the string
            int r = Integer.parseInt(num[j]);
            // creating random coordinate for the point, in range (450 + r) - (600 - r)
            int x = (rand.nextInt((END_SECOND - START_SECOND) - 2 * r) + START_SECOND + r);
            int y = (rand.nextInt((END_SECOND - START_SECOND) - 2 * r) + START_SECOND + r);
            Point center = new Point(x, y);
            // making a different color for each ball
            java.awt.Color color = MultipleBouncingBallsAnimation.getRandomColor();
            ballsKeeper[j] = new Ball(center, r, color);
            // creating speed for making velocity
            double speed = MultipleBouncingBallsAnimation.setSpeed(r);
            // creating angel for making velocity, in range 1 - 360
            double angle = (rand.nextInt(360) + 1);
            // creating velocity for the ball
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsKeeper[j].setVelocity(v);
        }
        return ballsKeeper;
    }

    /**
     * Getting the balls radius and moving them on the screen.
     * @param args - string that keeps the radius of the balls.
     */
    public static void main(String[] args) {
        // create a window with the title "MultipleFramesBouncingBallsAnimation"
        // which is 800 pixels wide and 600 pixels high
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", WIDTH_SCREEN, HEIGHT_SCREEN);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball[] balls = MultipleFramesBouncingBallsAnimation.createsBalls(args);
        int numOfArgs = balls.length;
        // assume an even number of balls, so we will get all the numbers
        int halfNumOfArgs = (numOfArgs / 2);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // one of them is a gray rectangle from (50, 50) to (500, 500) so the width and the height are 450
            d.setColor(Color.GRAY);
            d.fillRectangle(START_FIRST, START_FIRST, 450, 450);
            // other frame is a yellow rectangle from (450, 450) to (600, 600) so the width and the height are 150
            d.setColor(Color.YELLOW);
            d.fillRectangle(START_SECOND, START_SECOND, 150, 150);
            // the first half of the balls to bounce inside the first frame
            for (int i = 0; i < halfNumOfArgs; ++i) {
                balls[i].setBoardLeft(START_FIRST, START_FIRST);
                balls[i].setBoardRight(END_FIRST, END_FIRST);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
        }
            // the second half of the balls to bounce inside the second frame
            for (int i = halfNumOfArgs; i < numOfArgs; ++i) {
                balls[i].setBoardLeft(START_SECOND, START_SECOND);
                balls[i].setBoardRight(END_SECOND, END_SECOND);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            // wait for 50 milliseconds.
            sleeper.sleepFor(TIME);
        }
    }
}
