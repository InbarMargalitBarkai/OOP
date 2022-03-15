/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-03
 */

/**
 * Update this counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    // Fields
    private Counter currentScore;

    /**
     * Our constructor.
     * @param scoreCounter - the counter of score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Gets score.
     * @return - the score.
     */
    public int getScore() {
        return this.currentScore.getValue();
    }

    @Override
    /**
     * Hit Event.
     * @see HitListener#hitEvent(Block, Ball).
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // hitting a block is worth 5 points
        this.currentScore.increase(5);
    }

    /**
     * Update the score.
     * @param num - the number we want to add to the current score.
     */
    public void updateScore(int num) {
        this.currentScore.increase(num);
    }
}