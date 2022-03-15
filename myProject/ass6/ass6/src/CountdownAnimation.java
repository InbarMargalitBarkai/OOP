import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    // Fields
    private boolean stop = true;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Constructor.
     * @param numOfSeconds - number of seconds.
     * @param countFrom - from which number needs to count.
     * @param gameScreen - all the sprites plays in the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    /**
     * Do one frame.
     * @see Animation#doOneFrame(DrawSurface).
     */
    public void doOneFrame(DrawSurface d) {
        if (this.countFrom == -1) {
            this.stop = false;
            return;
        }
        this.gameScreen.drawAllOn(d);
        String num = Integer.toString((int) countFrom);
        d.setColor(new Color(255, 192, 0));
        d.drawText(375, 350, num, 100);
        this.countFrom--;
        // the sleeper should not work at the first time (so we don't get white screen)
        if (!(countFrom == 2)) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(1000);
        }
    }

    @Override
    /**
     * Should stop.
     * @see Animation#shouldStop().
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
