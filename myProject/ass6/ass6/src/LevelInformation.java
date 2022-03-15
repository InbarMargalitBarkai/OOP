import java.util.List;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * Specifies the information required to fully describe a level.
 */
public interface LevelInformation {

    /**
     * Say how many balls.
     * @return - the number of Balls.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities.
     * @return - the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     * @return - the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Paddle width.
     * @return - the width of the paddle.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     * @return - the level name.
     */
    String levelName();

    /**
     * Gets background.
     * @return - returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Blocks.
     * @return - the Blocks that make up this level, each block contains
     *      * its size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     * @return - number of blocks that should be removed
     * before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
