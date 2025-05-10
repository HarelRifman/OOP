/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;

/**
 * Represents the game, including its environment, sprites, and the main game loop.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Paddle paddle;
    private GUI gui;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 30;
    public static final int BORDER_WIDTH = 30;

    /**
     * Constructor to initialize the game environment and sprite collection.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Adds a collidable to the game environment.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Returns the sprite collection of the game.
     *
     * @return the sprite collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Returns the GUI of the game.
     *
     * @return the GUI.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Initialize a new game: create the Blocks, Balls, and Paddle, and add them to the game.
     */
    public void initialize() {
        gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        for (int i = 0; i < 1000; i++) {
            Ball b = new Ball(new Point(200, 100), 4, Color.white, environment);
            b.setVelocity(Velocity.fromAngleAndSpeed(360 * i / 1000, 3));
            b.addToGame(this);
        }
        // Create and add the balls
        Ball ball1 = new Ball(new Point(100, 100), 4, Color.white, environment);
        Ball ball2 = new Ball(new Point(100, 100), 4, Color.white, environment);
        Random rand = new Random();
        int randomNumber1 = rand.nextInt(2) + 3;
        int randomNumber2 = rand.nextInt(2) + 3;
        ball1.setVelocity(randomNumber2, randomNumber1);
        ball1.addToGame(this);
        ball2.setVelocity(randomNumber1, randomNumber2);
        ball2.addToGame(this);

        // Create and add blocks in a grid pattern
        int startX = WIDTH - BORDER_WIDTH;
        int startY = 100;
        int blockWidth = 60;
        int blockHeight = 25;
        int rows = 6;
        int cols = 10;
        Color[] blockColors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - row; col++) {
                int x = startX - col * blockWidth;
                int y = startY + row * blockHeight;
                Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), blockColors[row]);
                block.addToGame(this);
            }
        }

        // Add boundary blocks
        Block bottomBoundary = new Block(new Rectangle(new Point(0, HEIGHT - BORDER_WIDTH), WIDTH, BORDER_WIDTH),
                Color.gray);
        Block topBoundary = new Block(new Rectangle(new Point(0, 0), WIDTH, BORDER_WIDTH), Color.gray);
        Block leftBoundary = new Block(new Rectangle(new Point(0, 0), BORDER_WIDTH, HEIGHT), Color.gray);
        Block rightBoundary = new Block(new Rectangle(new Point(WIDTH - BORDER_WIDTH, 0), BORDER_WIDTH, HEIGHT),
                Color.gray);
        bottomBoundary.addToGame(this);
        topBoundary.addToGame(this);
        leftBoundary.addToGame(this);
        rightBoundary.addToGame(this);

        // Create and add the paddle
        Point paddleUpperLeft = new Point(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - PADDLE_HEIGHT - BORDER_WIDTH);
        Rectangle paddleRect = new Rectangle(paddleUpperLeft, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle = new Paddle(paddleRect, 10, keyboard);
        paddle.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // Set the background color to blue
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);
            this.sprites.drawAllOn(d);
            gui.show(d);

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
