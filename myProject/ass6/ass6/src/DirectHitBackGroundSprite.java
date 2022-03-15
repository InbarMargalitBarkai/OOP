import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The background for "Direct Hit" level.
 */
public class DirectHitBackGroundSprite implements Sprite {

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.drawText(600, 20, "Level: Direct Hit", 20);
        // background color
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 25, 800, 600);
        // draw circles
        d.setColor(Color.BLUE);
        d.drawCircle(400, 195, 150);
        d.drawCircle(400, 195, 100);
        d.drawCircle(400, 195, 50);
        // draw lines
        d.drawLine(220, 200, 580, 200);
        d.drawLine(400, 10, 400, 370);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }

    /**
     * Adds the Sprite object to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
