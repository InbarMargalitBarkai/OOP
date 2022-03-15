import biuoop.DrawSurface;
import java.awt.Color;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The background for "Final Four" level.
 */
public class FinalFourBackGroundSprite implements Sprite {

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        // the background color
        d.setColor(Color.BLUE);
        d.fillRectangle(20, 40, 760, 600);
        // the rain
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + (10 * i), 380, 60 + (10 * i), 600);
            d.drawLine(590 + (10 * i), 500, 550 + (10 * i), 600);
        }
        // the clouds
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(100, 370, 22);
        d.fillCircle(120, 390, 25);
        d.fillCircle(590, 490, 22);
        d.fillCircle(610, 510, 25);
        d.setColor(Color.GRAY);
        d.fillCircle(140, 360, 28);
        d.fillCircle(630, 480, 28);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(160, 390, 18);
        d.fillCircle(180, 380, 30);
        d.fillCircle(650, 510, 18);
        d.fillCircle(670, 500, 30);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }
}
