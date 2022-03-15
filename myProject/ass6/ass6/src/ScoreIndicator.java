import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-03
 */

/**
 * Will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    // Fields
    private Rectangle scoresDisplay;
    private Counter scores;

    /**
     * Constructor.
     * @param scoresDisplay the rectangle the scores will bo show on.
     * @param scores        the given scores.
     */
    ScoreIndicator(Rectangle scoresDisplay, Counter scores) {
        this.scores = scores;
        this.scoresDisplay = scoresDisplay;
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        this.scoresDisplay.drawRectangle(d);
        d.setColor(Color.BLACK);
        d.drawText(370, 19, "Score: " + this.scores.getValue(), 15);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }

    /**
     * Add the score indicator to the game.
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Gets scores.
     * @return the scores
     */
    public Counter getScores() {
        return this.scores;
    }
}
