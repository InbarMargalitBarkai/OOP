import biuoop.KeyboardSensor;
import java.util.List;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * This class will be in charge of creating the different levels,
 * and moving from one level to the next.
 */
public class GameFlow {
    // constant for the rectangle width
    static final int REC_WIDTH = 800;
    // constant for the rectangle height
    static final int REC_HEIGHT = 25;
    // Fields
    private AnimationRunner animationRunner;
    private ScoreIndicator scoreIndicator;
    private Counter scoresCounter = new Counter();
    private int endGame = 0;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor.
     *
     * @param ar the Animation Runner to run the game with.
     * @param ks KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run all the levels in the list, until the list is over (win), or lose.
     *
     * @param levels the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel level;
        Rectangle rectangle = new Rectangle(new Point(0, 0), REC_WIDTH, REC_HEIGHT);
        ScoreIndicator scoreIndicator1 = new ScoreIndicator(rectangle, scoresCounter);
        for (LevelInformation levelInfo : levels) {
            level = new GameLevel(levelInfo,
                    this.animationRunner, this.keyboardSensor, scoreIndicator1);
            level.initialize();

            while (level.shouldStop()) {
                level.run();
            }
            // if there is no balls left its means we go to loose situation
            if (level.getBallsCounter().getValue() == 0) {
                endGame = 1;
                break;
            }
        }
        // end game screen
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(endGame, keyboardSensor, scoresCounter.getValue())));
        animationRunner.getGui().close();
    }

    /**
     * Gets score indicator.
     * @return the score indicator
     */
    public ScoreIndicator getScoreIndicator() {
        return this.scoreIndicator;
    }
}


