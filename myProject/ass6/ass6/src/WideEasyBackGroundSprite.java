import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The background for "Wide Easy" level.
 */
public class WideEasyBackGroundSprite implements Sprite {

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        // the background color
        d.setColor(Color.WHITE);
        d.fillRectangle(20, 40, 760, 600);
        // the rays of the sun
        d.setColor(Color.YELLOW);
        for (int i = 20; i < 720; i += 10) {
            d.drawLine(150, 150, i, 300);
        }
        // the sun
        d.fillCircle(150, 150, 70);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.YELLOW);
        d.fillCircle(150, 150, 30);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }
}
