import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-14
 */

/**
 * The end that say if the player win or lose.
 */
public class EndScreen implements Animation {
    // Fields:
    private boolean stop;
    private int i;
    private KeyboardSensor keyboardSensor;
    private int scores;

    /**
     * Constructor.
     * @param i              flag that indicate winning (0) or losing (1)
     * @param keyboardSensor Keyboard
     * @param scores         the scores in the game
     */
    public EndScreen(int i, KeyboardSensor keyboardSensor, int scores) {
        this.i = i;
        this.stop = true;
        this.keyboardSensor = keyboardSensor;
        this.scores = scores;
    }

    @Override
    /**
     * Do one frame.
     * @see Animation#doOneFrame(DrawSurface).
     */
    public void doOneFrame(DrawSurface d) {
        // loosing situation
        if (i == 1) {
            d.drawText(50, d.getHeight() / 2 - 50,
                    "Game Over. Your score is " + Integer.toString(this.scores), 55);
        }
        // winning situation
        if (i == 0) {
            d.drawText(55, d.getHeight() / 2 - 50,
                    "You Win! Your score is " + Integer.toString(this.scores), 55);
        }
        // option to exit
        d.drawText(210, 550, " (press space to close)", 35);
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
