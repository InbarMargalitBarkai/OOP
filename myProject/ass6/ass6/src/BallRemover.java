/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
 */

/**
 * HitListener called BallRemover that will be in charge of removing balls,
 * and updating an available - balls counter.
 */
public class BallRemover implements HitListener {
    // Fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game - the game.
     * @param remainingBalls - counts how many balls remaining in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Gets how many balls remaining in the game.
     * @return - the number of the remaining balls.
     */
    public int getRemainingBalls() {
        return (this.remainingBalls.getValue());
    }

    @Override
    /**
     * hitEvent.
     * @see HitListener#hitEvent(Block, Ball).
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.getBallsCounter().decrease(1);
    }

    /**
     * Update the number of balls.
     * @param num - the number of balls we want to add to the remaining balls.
     */
    public void updateRemainingBalls(int num) {
        this.remainingBalls.increase(num);
    }
}