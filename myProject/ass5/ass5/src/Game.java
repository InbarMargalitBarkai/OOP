import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-03
 */

/**
 * This class will hold the sprites and the collidables,
 * and will be in charge of the animation.
 */
public class Game {
    // constant for the width of the screen
    static final int WIDTH_SCREEN = 800;
    // constant for the height of the screen
    static final int HEIGHT_SCREEN = 600;
    // constant for the width of the block
    static final int BLOCK_WIDTH = 50;
    // constant for the height of the block
    static final int BLOCK_HEIGHT = 20;
    static final int EDGES_BLOCK_WIDTH = 25;
    // constant for the radius of the ball
    static final int RADIUS = 5;
    // number of lines of blocks
    static final int LINES = 5;
    // constant for number of balls at the beginning
    static final int BALLS_NUM = 3;
    // constant for number of blocks at the first row
    static final int BLOCKS_NUM = 10;

    // Fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui = new GUI("Arkanoid", WIDTH_SCREEN, HEIGHT_SCREEN);
    private Counter blocksCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private Counter scoresCounter = new Counter();

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Adding Collidable.
     *
     * @param c - Collidable we need to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adding Sprite.
     *
     * @param s - Sprite we need to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game.
     * Create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // create the balls
        Ball ball1 = new Ball(new Point(450, 500), RADIUS, Color.WHITE),
                ball2 = new Ball(new Point(500, 450), RADIUS, Color.WHITE),
                ball3 = new Ball(new Point(400, 450), RADIUS, Color.WHITE);
        this.ballsCounter.increase(BALLS_NUM);
        ball1.setVelocity(-3, -7);
        ball2.setVelocity(0, -7);
        ball3.setVelocity(3, -7);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        ball1.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        ball3.setGameEnvironment(this.environment);
        // create the borders of the board
        Rectangle upRect = new Rectangle(new Point(0, 0 + EDGES_BLOCK_WIDTH), WIDTH_SCREEN, EDGES_BLOCK_WIDTH),
                leftRect =  new Rectangle(new Point(0, 0), EDGES_BLOCK_WIDTH, HEIGHT_SCREEN),
                downRect = new Rectangle(new Point(0, HEIGHT_SCREEN),
                        WIDTH_SCREEN, EDGES_BLOCK_WIDTH),
                rightRect = new Rectangle(new Point(WIDTH_SCREEN - EDGES_BLOCK_WIDTH, 0),
                        EDGES_BLOCK_WIDTH, HEIGHT_SCREEN);
        Block leftBlock = new Block(leftRect),
                rightBlock = new Block(rightRect),
                upBlock = new Block(upRect),
                downBlock = new Block(downRect);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        upBlock.addToGame(this);
        downBlock.addToGame(this);
        leftBlock.setColor(Color.GRAY);
        rightBlock.setColor(Color.GRAY);
        upBlock.setColor(Color.GRAY);
        downBlock.setColor(Color.GRAY);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        downBlock.addHitListener(ballRemover);
        ScoreTrackingListener scores = new ScoreTrackingListener(this.getScoresCounter());
        // initialize the color of each row of blocks in the game
        Color[] colors = new Color[LINES];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;
        colors[4] = Color.WHITE;
        Block[][] blocks = new Block[LINES][BLOCKS_NUM];
        // creating the blocks in the rows
        int counterAxeY = 0;
        for (int i = 0; i < LINES; ++i) {
            for (int j = 0; j < BLOCKS_NUM - counterAxeY; ++j) {
                Rectangle rectangle = new Rectangle((new Point(725 - BLOCK_WIDTH * j, 140 + BLOCK_HEIGHT * i)),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                blocks[i][j] = new Block(rectangle);
                blocks[i][j].setColor(colors[i]);
                blocks[i][j].addToGame(this);
                this.blocksCounter.increase(1);
                BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
                blocks[i][j].addHitListener(blockRemover);
                blocks[i][j].addHitListener(scores);
            }
            counterAxeY++;
        }
        // create the paddle
        Paddle paddle = new Paddle();
        paddle.addToGame(this);
        paddle.setKeyboard(this.gui);
        // displaying the score
        Rectangle scoresRectangle = new Rectangle(new Point(0, 0), WIDTH_SCREEN, EDGES_BLOCK_WIDTH);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoresRectangle, this.scoresCounter);
        scoreIndicator.addToGame(this);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            // green board color
            Color newColor = new Color(0, 255, 0);
            d.setColor(newColor);
            d.fillRectangle(0, 0, WIDTH_SCREEN, HEIGHT_SCREEN);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            if (this.getBlocksCounter().getValue() == 0 || this.getBallsCounter().getValue() == 0) {
                this.getBallsCounter().increase(100);
                gui.close();
                return;
            }
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes collidable.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * Removes sprite.
     * @param s - the collidable.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * Gets blocks counter.
     * @return - number of blocks.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * Gets balls counter.
     * @return - number of balls.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Gets scores counter.
     * @return - the score.
     */
    public Counter getScoresCounter() {
        return this.scoresCounter;
    }
}

