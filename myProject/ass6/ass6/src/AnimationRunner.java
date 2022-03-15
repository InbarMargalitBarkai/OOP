import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-11
 */

/**
 * Running the animation with frames.
 */
public class AnimationRunner {
    // Fields
    private GUI gui;
    private int framesPerSecond = 60;
    private biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * Constructor.
     * @param gui the given gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * As long as not ordered differently, run the game:
     * do one frame, and show it.
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     * @return the gui of the animation runner
     */
    public GUI getGui() {
        return this.gui;
    }
}
