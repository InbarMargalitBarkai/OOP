import biuoop.DrawSurface;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * Sprite is a game object that can be drawn to the screen.
 *  In our design, all of the game objects (Ball, Block, Paddle...) are Sprites.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d - the surface to draw on him.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}