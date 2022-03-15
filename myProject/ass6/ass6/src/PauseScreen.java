import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * Adding a stop-game option when the user press the key P.
 */
public class PauseScreen implements Animation {
    // Fields
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = true;
    }

    @Override
    /**
     * Do one frame.
     * @see Animation#doOneFrame(DrawSurface).
     */
    public void doOneFrame(DrawSurface d) {
        //background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(230, d.getHeight() / 2, "paused", 100);
        d.drawText(90, d.getHeight() / 2 + 100, "press space to continue", 60);
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
