import biuoop.DrawSurface;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The name of the level.
 */
public class LevelName implements Sprite {
    // Fields
    private String name;

    /**
     * Constructor.
     * @param name - the name of the level.
     */
    public LevelName(String name) {
        this.name = name;
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface).
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(550, 19, "Level Name: " + this.name, 15);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
    }
}
