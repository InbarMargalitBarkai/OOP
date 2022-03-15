import biuoop.DrawSurface;

/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
 */

/**
 * Rectangle objects we collide into.
 */
public class Block implements Collidable, Sprite {
    // Fields
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * Constructor.
     * @param rect - our rectangle which is the shape of the block.
     * @param color - the color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Sets color.
     * @param newColor - the color to set.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Adding the objects to the game.
     * @param g - the game to add the block to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    /**
     * Gets collision Rectangle.
     * @see Collidable#getCollisionRectangle() .
     */
    public Rectangle getCollisionRectangle() {
        return (this.rect);
    }

    @Override
    /**
     * Hit.
     * @see Collidable#hit(Point, Velocity) .
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double leftX = this.rect.getUpperLeft().getX();
        double rightX = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        double upperY = this.rect.getUpperLeft().getY();
        double lowerY = this.rect.getUpperLeft().getY() + this.rect.getHeight();
        // checking if the ball hits an horizontal edge of the block
        if ((collisionPoint.getX() >= leftX && collisionPoint.getX() <= rightX)
                && (collisionPoint.getY() == upperY || collisionPoint.getY() == lowerY)) {
            // the vertical direction should change
            return (new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy()));
        } else {
            // checking if the ball hits a vertical edge of the block
            if (collisionPoint.getY() >= upperY && collisionPoint.getY() <= lowerY) {
                // the horizontal direction should change
                return (new Velocity(-currentVelocity.getDx(),
                        currentVelocity.getDy()));
            } else {
                // there is no need to change
                return currentVelocity;
            }
        }
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface) .
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.rect.getLeftX(), (int) this.rect.getUpperY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getLeftX(), (int) this.rect.getUpperY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
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
}
