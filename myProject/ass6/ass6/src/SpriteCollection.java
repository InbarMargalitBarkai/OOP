import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * SpriteCollection will hold a collection of sprites objects.
 */
public class SpriteCollection {
    // Fields
    private List<Sprite> spriteList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * Adding the given Sprite to the collection.
     * @param s - the Sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Call timePassed method on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); ++i) {
            (this.spriteList.get(i)).timePassed();
        }
    }

    /**
     * Call drawOn method on all sprites.
     * @param d - the surface to draw on him.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); ++i) {
            (this.spriteList.get(i)).drawOn(d);
        }
    }

    /**
     * Gets spriteList.
     * @return - list of Sprites.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * Removing the given Sprite from the collection.
     * @param s - the Sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}