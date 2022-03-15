import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-11
 */

/**
 * This class will hold the sprites and the collidables,
 * and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    // constant for the width of the board
    static final int WIDTH = 800;
    // constant for the height of the board
    static final int HEIGHT = 600;
    // constant for the width of the blocks in the edges of the board
    static final int EDGES_BLOCK_WIDTH = 25;
    // constant for the radius of the balls
    static final int RADIUS = 5;
    // Fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Counter blocksCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private Counter scoresCounter = new Counter();
    private AnimationRunner runner;
    private boolean running = true;
    private LevelInformation levelInformation;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * Constructor.
     * @param levelInformation the information of the levels
     * @param animationRunner  animationRunner to run with
     * @param keyboardSensor   keyboardSensor
     * @param scores           the scores in the game
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner,
                     KeyboardSensor keyboardSensor, ScoreIndicator scores) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.scoreIndicator = scores;
        this.scoreTrackingListener = new ScoreTrackingListener(scores.getScores());
    }

    /**
     * Gets blocks counter.
     * @return the counter of the remaining blocks in the game.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * Gets balls counter.
     * @return the counter of the remaining balls in the game.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Gets scores counter.
     * @return the counter of the scores achieved
     */
    public Counter getScoresCounter() {
        return this.scoresCounter;
    }

    /**
     * Add a new collidable object to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a new sprite object to the sprites.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a collidable object from the environment.
     * @param c the collidable to move
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * Remove a sprite object from the sprites.
     * @param s the sprite to add
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * Create balls on top of the paddle.
     */
    private void createBallsOnTopOfPaddle() {
        List<Velocity> list = this.levelInformation.initialBallVelocities();
        // create the balls, set their settings, and add them to the game
        for (Velocity vel : list) {
            Ball ball = new Ball(new Point(400, 500), RADIUS, Color.WHITE);
            ball.setVelocity(vel);
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * Initializes a new game.
     * Create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // backGround
        addSprite(this.levelInformation.getBackground());
        // balls
        createBallsOnTopOfPaddle();
        // edges blocks
        Rectangle rectangleLeft = new Rectangle(new Point(0, 0), EDGES_BLOCK_WIDTH, HEIGHT),
                rectangleRight = new Rectangle(new Point(WIDTH - EDGES_BLOCK_WIDTH, 0), EDGES_BLOCK_WIDTH, HEIGHT),
                rectangleTop = new Rectangle(new Point(0, -5 + EDGES_BLOCK_WIDTH), WIDTH, EDGES_BLOCK_WIDTH),
                deathRegion = new Rectangle(new Point(0, HEIGHT), WIDTH, EDGES_BLOCK_WIDTH);
        Block block1 = new Block(rectangleLeft),
                block2 = new Block(rectangleRight),
                block3 = new Block(rectangleTop),
                block4 = new Block(deathRegion);
        ArrayList<Block> blocksList = new ArrayList();
        blocksList.add(block1);
        blocksList.add(block2);
        blocksList.add(block3);
        blocksList.add(block4);
        for (int i = 0; i < blocksList.size(); i++) {
            blocksList.get(i).addToGame(this);
            blocksList.get(i).setColor(Color.GRAY);
        }
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        block4.addHitListener(ballRemover);
        // blocks
        List<Block> blocks = this.levelInformation.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            block.addToGame(this);
            this.blocksCounter.increase(1);
            BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        // paddle
        Paddle paddle = new Paddle(WIDTH / 2 - this.levelInformation.paddleWidth() / 2,
                this.levelInformation.paddleWidth(), this.levelInformation.paddleSpeed(), this.keyboard);
        paddle.addToGame(this);
        // the rectangle that display the scores
        ScoreIndicator scoreIndicator1 = this.scoreIndicator;
        scoreIndicator1.addToGame(this);
        // level name
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        addSprite(levelName);
    }

    /**
     * Run the game.
     */
    public void run() {
        // countdown starts with three and there is two seconds between each number
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }

    @Override
    /**
     * Do one frame.
     * @see Animation#doOneFrame(DrawSurface).
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // level is over
        if (this.getBlocksCounter().getValue() == 0) {
            this.scoreIndicator.getScores().increase(100);
            this.running = false;
        }
        // loose situation
        if (this.getBallsCounter().getValue() == 0) {
            this.running = false;
        }
        // pause
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P") || this.keyboard.isPressed("פ")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space",
                    new PauseScreen(keyboard)));
        }
    }

    @Override
    /**
     * Should stop.
     * @see Animation#shouldStop().
     */
    public boolean shouldStop() {
        return this.running;
    }
}