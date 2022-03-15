import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
 */

/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    // number of regions we have in the paddle
    static final int REGION_NUM = 5;
    static final int SPEED = 10;
    static final int EXPONENT = 2;

    // Fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private double y;

    /**
     * Constructor.
     * @param keyboard - the keyboard of the user that control the paddle.
     * @param rect     - the paddle is a rectangle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.y = rect.getUpperY();
    }

    /**
     * Know how to move to the left.
     */
    public void moveLeft() {
        if (this.rect.getLeftX() > 40) {
            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - SPEED, y));
        }
    }

    /**
     * Know how to move to the right.
     */
    public void moveRight() {
        if (this.rect.getRightX() < 760) {
            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + SPEED, y));
        }
    }

    /**
     * Add this paddle to the game.
     * @param g - the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    /**
     * Get collision rectangle.
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
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), EXPONENT)
                + Math.pow(currentVelocity.getDy(), EXPONENT));
        /*
         In order for the game to be more enjoyable, we require the following behavior from the paddle:
         think of the paddle as having 5 equally-spaced regions. Finding the place of the hit in the paddle
         and according to the place the ball will move in the appropriate degree
         */
        if (collisionPoint.getX() >= this.rect.getUpperLeft().getX()
                && collisionPoint.getX() < this.rect.getUpperLeft().getX() + this.rect.getWidth() / REGION_NUM) {
            v = Velocity.fromAngleAndSpeed(240, speed);
        } else if (collisionPoint.getX() >= this.rect.getUpperLeft().getX() + this.rect.getWidth() / REGION_NUM
                && collisionPoint.getX() < this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 2) {
            v = Velocity.fromAngleAndSpeed(210, speed);
        } else if (collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 2
                && collisionPoint.getX() < this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 3) {
            v = new Velocity(-v.getDx(), -v.getDy());
        } else if (collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 3
                && collisionPoint.getX() < this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 4) {
            v = Velocity.fromAngleAndSpeed(120, speed);
        } else if (collisionPoint.getX() >= this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 4
                && collisionPoint.getX() < this.rect.getUpperLeft().getX() + (this.rect.getWidth() / REGION_NUM) * 5) {
            v = Velocity.fromAngleAndSpeed(150, speed);
        }
        return v;
    }

    @Override
    /**
     * Draw on.
     * @see Sprite#drawOn(DrawSurface) .
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle((int) this.rect.getLeftX(),
                (int) this.rect.getUpperY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed() .
     */
    public void timePassed() {
        // Checking which of the keys are pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
}
