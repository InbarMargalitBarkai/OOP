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
 * The level who called "Green 3".
 */
public class Green3 implements LevelInformation {
    // constant for the paddle speed
    static final int PADDLE_SPEED = 10;
    // constant for the paddle width
    static final int PADDLE_WIDTH = 100;
    // constant for the block width
    static final int BLOCK_WIDTH = 50;
    // constant for the block height
    static final int BLOCK_HEIGHT = 30;
    // constant for the width of the blocks in the edges of the board
    static final int EDGES_WIDTH = 25;
    // Fields
    private List<Velocity> velList;
    private List<Block> blockList;

    @Override
    /**
     * Number of balls.
     * @see LevelInformation#numberOfBalls().
     */
    public int numberOfBalls() {
        return this.velList.size();
    }

    @Override
    /**
     * Initial ball velocities.
     * @see LevelInformation#initialBallVelocities().
     */
    public List<Velocity> initialBallVelocities() {
        this.velList = new ArrayList<Velocity>();
        this.velList.add(Velocity.fromAngleAndSpeed(150, 5));
        this.velList.add(Velocity.fromAngleAndSpeed(-150, 5));
        return this.velList;
    }

    @Override
    /**
     * Paddle speed.
     * @see LevelInformation#paddleSpeed().
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    /**
     * Paddle width.
     * @see LevelInformation#paddleWidth().
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    /**
     * Level name.
     * @see LevelInformation#levelName().
     */
    public String levelName() {
        return "Green 3";
    }

    @Override
    /**
     * Get back ground.
     * @see LevelInformation#getBackground().
     */
    public Sprite getBackground() {
        return new Green3BackGroundSprite();
    }

    @Override
    /**
     * Blocks.
     * @see LevelInformation#blocks().
     */
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 10; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - (BLOCK_WIDTH * i), 100 + (BLOCK_HEIGHT * 0)),
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(Color.GRAY);
            this.blockList.add(block);
        }
        for (int i = 0; i < 9; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - (BLOCK_WIDTH * i), 100 + BLOCK_HEIGHT * 1),
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(Color.RED);
            this.blockList.add(block);
        }
        for (int i = 0; i < 8; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - (BLOCK_WIDTH * i), 100 + (BLOCK_HEIGHT * 2)),
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(Color.YELLOW);
            this.blockList.add(block);
        }
        for (int i = 0; i < 7; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - (BLOCK_WIDTH * i), 100 + (BLOCK_HEIGHT * 3)),
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(Color.BLUE);
            this.blockList.add(block);
        }
        for (int i = 0; i < 6; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - (BLOCK_WIDTH * i), 100 + (BLOCK_HEIGHT * 4)),
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(Color.WHITE);
            this.blockList.add(block);
        }
        return this.blockList;
    }

    @Override
    /**
     * Number of blocks to remove.
     * @see LevelInformation#numberOfBlocksToRemove().
     */
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }
}
