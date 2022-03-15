import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * The level who called "Direct Hit".
 */
public class DirectHit implements LevelInformation {
    // constant for the paddle speed
    static final int PADDLE_SPEED = 10;
    // constant for the paddle width
    static final int PADDLE_WIDTH = 100;
    // constant for the block width
    static final int BLOCK_WIDTH = 35;
    // constant for the block height
    static final int BLOCK_HEIGHT = 35;
    // Fields
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private Sprite backGround;
    private List<Block> listOfBlocks;

    /**
     * Constructor.
     */
    public DirectHit() {
        // velocity of the ball
        this.velocityList = new ArrayList<Velocity>();
        this.velocityList.add(new Velocity(0, -5));
        // paddle speed
        this.paddleSpeed = PADDLE_SPEED;
        // paddle width
        this.paddleWidth = PADDLE_WIDTH;
        // back ground
        this.backGround = new DirectHitBackGroundSprite();
        // there is only one block to pop
        Rectangle rectangle = new Rectangle(new Point(385, 180), BLOCK_WIDTH, BLOCK_HEIGHT);
        Block b = new Block(rectangle);
        b.setColor(Color.RED);
        this.listOfBlocks = new ArrayList<Block>();
        this.listOfBlocks.add(b);
    }

    @Override
    /**
     * Number of balls.
     * @see LevelInformation#numberOfBalls().
     */
    public int numberOfBalls() {
        return this.velocityList.size();
    }

    @Override
    /**
     * Initial ball velocities.
     * @see LevelInformation#initialBallVelocities().
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    /**
     * Paddle speed.
     * @see LevelInformation#paddleSpeed().
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    /**
     * Paddle width.
     * @see LevelInformation#paddleWidth().
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    /**
     * Level name.
     * @see LevelInformation#levelName().
     */
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    /**
     * Get background.
     * @see LevelInformation#getBackground().
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    /**
     * Blocks.
     * @see LevelInformation#blocks().
     */
    public List<Block> blocks() {
        return this.listOfBlocks;
    }

    @Override
    /**
     * Number of blocks to remove.
     * @see LevelInformation#numberOfBlocksToRemove().
     */
    public int numberOfBlocksToRemove() {
        return this.listOfBlocks.size();
    }
}
