import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
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
    // the radius of the ball
    static final int RADIUS = 5;
    // the block width
    static final int WIDTH_BLOCK = 50;
    // the block height
    static final int HEIGHT_BLOCK = 30;
    // number of lines of blocks
    static final int LINES = 6;
    // Fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * Constructor.
     * @param sprites - we hold the sprites.
     * @param environment - the environment of the game.
     */
    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
    }

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Adding Collidable.
     * @param c - Collidable we need to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adding Sprite.
     * @param s - Sprite we need to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // creating the GUI
        this.gui = new GUI("Arkanoid", WIDTH_SCREEN, HEIGHT_SCREEN);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        // create the borders
        Block upRect = new Block(new Rectangle(new Point(0, 0), WIDTH_SCREEN, 40), Color.GRAY),
                leftRect = new Block(new Rectangle(new Point(0, 40), 40, HEIGHT_SCREEN - 40),
                        Color.GRAY),
                lowRect = new Block(new Rectangle(new Point(40, HEIGHT_SCREEN - 40), (WIDTH_SCREEN - 40 - 40),
                        40), Color.GRAY),
                rightRect = new Block(new Rectangle(new Point(WIDTH_SCREEN - 40, 40), 40,
                        HEIGHT_SCREEN - 40), Color.GRAY);
        upRect.addToGame(this);
        leftRect.addToGame(this);
        lowRect.addToGame(this);
        rightRect.addToGame(this);
        // create the ball
        Ball ball1 = new Ball(new Point(450, 500), RADIUS, Color.BLACK),
                ball2 = new Ball(new Point(500, 450), RADIUS, Color.BLACK);
        ball1.setVelocity(5, 5);
        ball2.setVelocity(5, 5);
        ball1.addToGame(this);
        ball2.addToGame(this);
        // create the paddle
        Paddle paddle = new Paddle(keyboard, (new Rectangle(new Point(350, 530), 100, 30)));
        paddle.addToGame(this);
        ball1.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        // initialize the color of each row of blocks in the game
        Color[] colors = new Color[LINES];
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.MAGENTA;
        colors[3] = Color.RED;
        colors[4] = Color.YELLOW;
        colors[5] = Color.PINK;
        Block[][] blocks = new Block[LINES][12];
        int counterAxeY = 0;
        // creating the blocks in the rows
        for (int i = 0; i < LINES; ++i) {
            for (int j = 0; j < 12 - counterAxeY; ++j) {
                blocks[i][j] = new Block(new Rectangle((new Point(710 - WIDTH_BLOCK * j, 140 + HEIGHT_BLOCK * i)),
                        WIDTH_BLOCK, HEIGHT_BLOCK), colors[i]);
                blocks[i][j].addToGame(this);
                blocks[i][j].setColor(colors[i]);
            }
            counterAxeY++;
        }
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new Sleeper();
        // smooth animations that displays 60 different frames in a second, if possible
        int framesPerSecond = 60;
        int millisecondsPerFrame = (1000 / framesPerSecond);
        while (true) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}