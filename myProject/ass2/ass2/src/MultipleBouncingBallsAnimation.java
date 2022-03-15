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
 * Each argument is a size of a Ball,
 * and we want to make multiple of balls and moving them on the screen.
 */
public class MultipleBouncingBallsAnimation {
    // our constants
    // for balls above this size we have special case
    static final int BALL_SIZE = 50;
    // constant fot the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant fot the height of the screen
    static final int HEIGHT_SCREEN = 600;
    // constant variable speed for balls above size 50
    static final int SLOW_SPEED = 2;
    // constant for the pause
    static final int TIME = 50;
    // constant for the number of arguments we need to get
    static final int NUM_ARGS = 6;
    // the left point of the screen is (0,0)
    private static final int START_SCREEN = 0;

    /**
     * Making random colors for the ball that we will make.
     * @return random color for the balls.
     */
    public static java.awt.Color getRandomColor() {
        Random colorRand = new Random();
        return (new java.awt.Color(colorRand.nextInt(256),
              colorRand.nextInt(256), colorRand.nextInt(256)));
    }

    /**
     * Sets the speed of each ball accordingly to his size.
     * @param r - the radius of the ball we get from the sting.
     * @return the appropriate speed.
     */
    public static double setSpeed(int r) {
        // balls above size 50 can all have the same slow speed
        if (r > BALL_SIZE) {
            return SLOW_SPEED;
        } else {
            // balls under size 50 we be with high speed accordingly to their size
            return (r / SLOW_SPEED);
        }
    }

    /**
     * Storing the balls that create in an array,
     * and converting the object that in num into balls with more information about balls.
     * @param num getting an array of the sizes of the ball.
     * @return array of balls that we create.
     */
    public static Ball[] createsBalls(String[] num) {
        // creating variable that we will get him from the string array
        int radius;
        Random rand = new Random();
        // number of the arguments in the string we got
        int numOfArgs = num.length;
        // creating an array so we can keep there the balls we will make
        Ball[] ballsKeeper = new Ball[numOfArgs];
        // we need to convert any cell in the array we got into a ball object
        for (int i = 0; i < numOfArgs; i++) {
            // taking the radius and save him, so we can create with him the ball
            radius = Integer.parseInt(num[i]);
            // creating random coordinate x, in range r - (800 - r)
            int x = (rand.nextInt(WIDTH_SCREEN - 2 * radius) + radius);
            // creating random coordinate y, in range r - (600 - r)
            int y = (rand.nextInt(HEIGHT_SCREEN - 2 * radius) + radius);
            Point center = new Point(x, y);
            // making a different color for each ball
            java.awt.Color color = getRandomColor();
                ballsKeeper[i] = new Ball(center, radius, color);
            // creating speed for making velocity
            double speed = setSpeed(radius);
            // creating angel for making velocity, in range 1 - 360
            double angle = (rand.nextInt(360) + 1);
            // creating velocity for the ball
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsKeeper[i].setVelocity(v);
        }
        return ballsKeeper;
    }

    /**
     * Getting the balls radius and moving them on the screen.
     * @param args - string that keeps the radius of the balls.
     */
    public static void main(String[] args) {

        /*
         checking if we get correct number of arguments.
         we can assume that we will get correct but its good to deal with that case
         */
        if (args.length != NUM_ARGS) {
            System.out.println("Wrong number of arguments. Please try again");
            return;
        }
        // sending the parameters we got to create the array
        Ball[] balls = MultipleBouncingBallsAnimation.createsBalls(args);
        int numOfArgs = balls.length;
        // creating our window
        GUI gui = new GUI("MultipleBouncingBallsAnimation", WIDTH_SCREEN, HEIGHT_SCREEN);
        DrawSurface d = gui.getDrawSurface();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            for (int i = 0; i < numOfArgs; ++i) {
                balls[i].setBoardLeft(START_SCREEN, START_SCREEN);
                balls[i].setBoardRight(WIDTH_SCREEN, HEIGHT_SCREEN);
                balls[i].moveOneStep();
            }
            d = gui.getDrawSurface();
            for (int j = 0; j < numOfArgs; ++j) {
                balls[j].drawOn(d);
            }
            gui.show(d);
            // wait for 50 milliseconds
            sleeper.sleepFor(TIME);
        }
    }
}
