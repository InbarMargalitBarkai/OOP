import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The background for "Green 3" level.
 */
public class Green3BackGroundSprite implements Sprite {

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        // the background color
        d.setColor(Color.GREEN);
        d.fillRectangle(20, 40, 760, 600);
        // the building
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 400, 100, 200);
        // the windows
        d.setColor(Color.WHITE);
        for (int i = 70; i < 150; i += 17) {
            d.fillRectangle(i, 410, 10, 30);
            d.fillRectangle(i, 448, 10, 30);
            d.fillRectangle(i, 486, 10, 30);
            d.fillRectangle(i, 524, 10, 30);
            d.fillRectangle(i, 562, 10, 30);
        }
        // the top of the building
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(85, 330, 40, 70);
        d.setColor(Color.GRAY);
        d.fillRectangle(100, 170, 10, 160);
        d.setColor(Color.ORANGE);
        d.fillCircle(105, 165, 15);
        d.setColor(Color.RED);
        d.fillCircle(105, 165, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(105, 165, 3);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }
}
