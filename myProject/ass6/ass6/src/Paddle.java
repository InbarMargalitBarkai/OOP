import biuoop.DrawSurface;
import biuoop.GUI;
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
    // constant for the movement of the paddle
    static final int PADDLE_MOVE = 5;
    // constant for the width of the paddle
    static final int PADDLE_WIDTH = 170;
    // constant for the height of the paddle
    static final int PADDLE_HEIGHT = 15;
    // constant for the first position of the paddle on the board
    static final Point PADDLE_START_POINT = new Point(365, 555);
    // constant for number of region in the paddle
    static final int REGION_NUM = 5;
    // constant for the part of the paddle
    static final Point PADDLE_PARTS = new Point(PADDLE_WIDTH / REGION_NUM, 0);
    // Fields
    private KeyboardSensor keyboard;
    private Block paddle;
    private int speed;

    /**
     * Constructor.
     */
    public Paddle() {
        Rectangle rectangle = new Rectangle(PADDLE_START_POINT, PADDLE_WIDTH, PADDLE_HEIGHT);
        Block block = new Block(rectangle);
        block.setColor(Color.YELLOW);
        this.paddle = block;
    }

    /**
     * Constructor.
     *
     * @param upperLeftX     the x of the upper left point of the paddle
     * @param width          the width of the paddle
     * @param newSpeed       the speed of the paddle
     * @param keyboardSensor KeyboardSensor
     */
    public Paddle(int upperLeftX, int width, int newSpeed, KeyboardSensor keyboardSensor) {
        Point upperLeft = new Point(upperLeftX, PADDLE_START_POINT.getY());
        Rectangle rectangle = new Rectangle(upperLeft, width, PADDLE_HEIGHT);
        Block block = new Block(rectangle);
        block.setColor(new Color(255, 192, 0));
        this.paddle = block;
        this.speed = newSpeed;
        this.keyboard = keyboardSensor;
    }

    /**
     * Sets the keyboard to the gui.
     * @param gui the gui
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Knows how to move to the left.
     */
    public void moveLeft() {
        Point newPoint = new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX() - PADDLE_MOVE,
                this.paddle.getCollisionRectangle().getUpperLeft().getY());
        this.paddle.getCollisionRectangle().setRectangleLocation(newPoint);
    }

    /**
     * Knows how to move right.
     */
    public void moveRight() {
        Point newPoint = new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX() + PADDLE_MOVE,
                this.paddle.getCollisionRectangle().getUpperLeft().getY());
        this.paddle.getCollisionRectangle().setRectangleLocation(newPoint);
    }

    @Override
    /**
     * Time passed.
     * @see Sprite#timePassed().
     */
    public void timePassed() {
        if (keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)
                && this.paddle.getCollisionRectangle().getUpperLeft().getX() + this.speed > 35) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.paddle.getCollisionRectangle().getUpperRight().getX() + this.speed < 785) {
            this.moveRight();
        }
    }

    /**
     * Draw the paddle on a surface.
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    @Override
    /**
     * Get collision rectangle.
     * @see Collidable#getCollisionRectangle() .
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    @Override
    /**
     * Hit.
     * @see Collidable#hit(Point, Velocity) .
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point point1 = this.paddle.getCollisionRectangle().getUpperLeft();
        Point point2 = point1.addPoints(PADDLE_PARTS);
        Point point3 = point2.addPoints(PADDLE_PARTS);
        Point point4 = point3.addPoints(PADDLE_PARTS);
        Point point5 = point4.addPoints(PADDLE_PARTS);
        Point point6 = point5.addPoints(PADDLE_PARTS);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point4);
        Line line4 = new Line(point4, point5);
        Line line5 = new Line(point5, point6);
        if (line1.pointInLine(collisionPoint)) {
            double vel = currentVelocity.speed();
            Velocity v = Velocity.fromAngleAndSpeed(300, vel);
            return v;
        } else if (line2.pointInLine(collisionPoint)) {
            double vel = currentVelocity.speed();
            Velocity v = Velocity.fromAngleAndSpeed(330, vel);
            return v;
        } else if (line3.pointInLine(collisionPoint)) {
            double vel = currentVelocity.speed();
            Velocity v = Velocity.fromAngleAndSpeed(0, vel);
            return v;
        } else if (line4.pointInLine(collisionPoint)) {
            double vel = currentVelocity.speed();
            Velocity v = Velocity.fromAngleAndSpeed(30, vel);
            return v;
        } else { // line5.pointInLine(collisionPoint))
            double vel = currentVelocity.speed();
            Velocity v = Velocity.fromAngleAndSpeed(60, vel);
            return v;
        }
    }

    /**
     * Add this paddle to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}