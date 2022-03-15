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
 * The level who called "Final Four".
 */
public class FinalFour implements LevelInformation {
    // constant for paddle speed
    static final int PADDLE_SPEED = 10;
    // constant for paddle width
    static final int PADDLE_WIDTH = 100;
    // constant for number of blocks in a row
    static final int BLOCK_NUM = 15;
    // constant for number of rows
    static final int ROW_NUM = 7;
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
        this.velList.add(Velocity.fromAngleAndSpeed(180, 5));
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
        return "Final Four";
    }

    @Override
    /**
     * Get background.
     * @see LevelInformation#getBackground().
     */
    public Sprite getBackground() {
        return new FinalFourBackGroundSprite();
    }

    @Override
    /**
     * Blocks.
     * @see LevelInformation#blocks().
     */
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Rectangle rec1 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 100), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect2 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 130), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect3 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 160), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect4 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 190), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect5 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 220), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect6 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 250), BLOCK_WIDTH, BLOCK_HEIGHT),
                    rect7 = new Rectangle(new Point(EDGES_WIDTH + (BLOCK_WIDTH * i), 280), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block1 = new Block(rec1),
                    block2 = new Block(rect2),
                    block3 = new Block(rect3),
                    block4 = new Block(rect4),
                    block5 = new Block(rect5),
                    block6 = new Block(rect6),
                    block7 = new Block(rect7);
            block1.setColor(Color.GRAY);
            this.blockList.add(block1);
            block2.setColor(Color.RED);
            this.blockList.add(block2);
            block3.setColor(Color.YELLOW);
            this.blockList.add(block3);
            block4.setColor(Color.GREEN);
            this.blockList.add(block4);
            block5.setColor(Color.WHITE);
            this.blockList.add(block5);
            block6.setColor(Color.PINK);
            this.blockList.add(block6);
            block7.setColor(Color.CYAN);
            this.blockList.add(block7);
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

