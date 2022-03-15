import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
 */

/**
 * Rectangle objects we collide into.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Fields
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param rectangle the rectangle to build the block with
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.black;
        this.hitListeners = new LinkedList<>();
    }

    @Override
    /**
     * Gets collision Rectangle.
     * @see Collidable#getCollisionRectangle() .
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Sets color.
     * @param newColor - the color to set.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }


    @Override
    /**
     * Hit.
     * @see Collidable#hit(Point, Velocity) .
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        if (new Line(this.rectangle.getBottomLeft(),
                this.rectangle.getBottomRight()).pointInLine(collisionPoint)) { // bottom
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (new Line(this.rectangle.getUpperLeft(),
                this.rectangle.getUpperRight()).pointInLine(collisionPoint)) { //up
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (new Line(this.rectangle.getUpperLeft(),
                this.rectangle.getBottomLeft()).pointInLine(collisionPoint)) { //left
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else { // right
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface) .
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        this.rectangle.drawRectangle(d);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed() .
     */
    public void timePassed() {
        /*
         for the block, currently we do nothing.
         */
    }

    /**
     * Adding the objects to the game.
     * @param g - the game to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove the objects from the game.
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    /**
     * Add hit listener.
     * @see HitNotifier#addHitListener(HitListener).
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * Remove hit listener.
     * @see HitNotifier#removeHitListener(HitListener).
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Will be called whenever a hit() occurs,
     * and will notify all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter - the ball that hits.
     */
    private void notifyHit(Ball hitter) {
        // make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
