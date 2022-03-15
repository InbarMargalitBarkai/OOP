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
 * The level who called "Wide Easy".
 */
public class WideEasy implements LevelInformation {
    // constant for the paddle speed
    static final int PADDLE_SPEED = 10;
    // constant for the paddle width
    static final int PADDLE_WIDTH = 600;
    // constant for the block width
    static final int BLOCK_WIDTH = 50;
    // constant for the block height
    static final int BLOCK_HEIGHT = 30;
    // constant for the row height of the blocks
    static final int ROW = 300;
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
        for (int i = 0; i < 5; i++) {
            this.velList.add(Velocity.fromAngleAndSpeed(180 + i, 5 + i));
            this.velList.add(Velocity.fromAngleAndSpeed(180 - i, 5 + i));
        }
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
        return "Wide Easy";
    }

    @Override
    /**
     * Get background.
     * @see LevelInformation#getBackground().
     */
    public Sprite getBackground() {
        return new WideEasyBackGroundSprite();
    }

    @Override
    /**
     * Blocks.
     * @see LevelInformation#blocks().
     */
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle1 = new Rectangle(new Point(25 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rectangle2 = new Rectangle(new Point(125 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rectangle3 = new Rectangle(new Point(225 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rectangle5 = new Rectangle(new Point(475 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rectangle6 = new Rectangle(new Point(575 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rectangle7 = new Rectangle(new Point(675 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block1 = new Block(rectangle1),
                    block2 = new Block(rectangle2),
                    block3 = new Block(rectangle3),
                    block5 = new Block(rectangle5),
                    block6 = new Block(rectangle6),
                    block7 = new Block(rectangle7);
            block1.setColor(Color.RED);
            this.blockList.add(block1);
            block2.setColor(Color.ORANGE);
            this.blockList.add(block2);
            block3.setColor(Color.YELLOW);
            this.blockList.add(block3);
            block5.setColor(Color.BLUE);
            this.blockList.add(block5);
            block6.setColor(Color.PINK);
            this.blockList.add(block6);
            block7.setColor(Color.CYAN);
            this.blockList.add(block7);
        }
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle4 = new Rectangle(new Point(325 + (BLOCK_WIDTH * i), ROW), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block4 = new Block(rectangle4);
            block4.setColor(Color.GREEN);
            this.blockList.add(block4);
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
