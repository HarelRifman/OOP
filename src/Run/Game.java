/**
 * @authour Harel Rifman
 * ID 217398338
 **/
package Run;

import ScoreAndListiners.BallRemover;
import ScoreAndListiners.BlockRemover;
import ScoreAndListiners.Collidable;
import ScoreAndListiners.ScoreTrackingListener;
import ScoreAndListiners.Counter;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collections.GameEnvironment;
import collections.SpriteCollection;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import geometryPrimitives.Velocity;

import java.awt.Color;

/**
 * The Game class represents the main logic and management of the Arkanoid game.
 * It handles initialization, running the game loop, and managing game elements.
 * This class uses biuoop library for GUI and game management.
 */
public class Game {
    // Game constants
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final Color SCREEN_COLOR = new Color(24, 121, 3);
    private static final int SPEED_PADDLE = 20;
    private static final int NUM_BALLS = 3;
    private static final int NUM_ROWS = 5;
    public static final int BORDER_WIDTH = 35;

    // Game objects
    private final SpriteCollection sprites;        // Collection of all sprites in the game
    private final GameEnvironment gameE;           // Game environment containing all collidable objects
    private final GUI gui;                         // GUI object for displaying the game
    private final Counter numOfBlocks = new Counter();
    private final Counter numOfBalls = new Counter();
    private final Counter score = new Counter();
    private final BlockRemover blockRemover = new BlockRemover(this, numOfBlocks);
    private BallRemover ballRemover;
    private final ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
    private Block deathRegion = createBlock(0, 0, 0, 0, Color.blue);
    private final ScoreIndicator scoreIndicator = new ScoreIndicator(score);

    /**
     * Constructs a new Game object.
     * Initializes the sprite collection, game environment, and GUI.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameE = new GameEnvironment();
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.gameE.addCollidable(c);
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.gameE.removeCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Increases the number of blocks in the game by 1.
     */
    private void addBlock() {
        this.numOfBlocks.increase(1);
    }

    /**
     * Initializes the game by creating blocks, paddle, edges, and balls,
     * and adding them to the game.
     */
    public void initialize() {
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        scoreIndicator.addToGame(this);

        // create the game block which gives the green color in background
        Block board = createBlock(0, BORDER_WIDTH, SCREEN_WIDTH, SCREEN_HEIGHT - BORDER_WIDTH, SCREEN_COLOR);
        board.addToGame(this);

        // add the paddle to the game
        Paddle paddle = new Paddle(new Rectangle(new Point(100, SCREEN_HEIGHT - BORDER_WIDTH),
                110, 25), SPEED_PADDLE, keyboard);
        paddle.addToGame(this);
        addBoundariesAndDeathRegion(paddle);
        addBlocks();
        addBallsToGame(paddle);
    }

    /**
     * Adds the boundaries and death region to the game.
     *
     * @param gamePaddle the paddle in the game
     */
    public void addBoundariesAndDeathRegion(Paddle gamePaddle) {
        // Create and add the death region
        Block deathRegionBlock = createBlock(0, SCREEN_HEIGHT - 1, SCREEN_WIDTH, 2, Color.GREEN);
        ballRemover = new BallRemover(this, numOfBalls, gamePaddle);
        deathRegionBlock.addHitListener(ballRemover);
        deathRegionBlock.addToGame(this);

        // Create and add the boundaries
        addBoundary(0, BORDER_WIDTH, SCREEN_WIDTH, BORDER_WIDTH, Color.GRAY);
        addBoundary(0, BORDER_WIDTH, BORDER_WIDTH, SCREEN_HEIGHT - BORDER_WIDTH, Color.GRAY);
        addBoundary(SCREEN_WIDTH - BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, SCREEN_HEIGHT - BORDER_WIDTH, Color.GRAY);
    }

    /**
     * Adds the blocks to the game.
     */
    public void addBlocks() {
        Color[] colors = initializeColors();
        int y = 130;
        final int yLen = 30;
        final int xLen = 50;

        for (int row = NUM_ROWS; row > 0; row--) {
            y += yLen;
            int x = SCREEN_WIDTH - BORDER_WIDTH - xLen;
            int blocksInRow = 5 + row;

            // iterating and adding the blocks
            for (int col = 0; col < blocksInRow; col++) {
                createAndAddBlock(x, y, xLen, yLen, colors[NUM_ROWS - row]);
                x -= xLen;
            }
        }
    }

    /**
     * Adds balls to the game.
     *
     * @param playerPaddle the paddle in the game
     */
    public void addBallsToGame(Paddle playerPaddle) {
        // Create and add balls
        Ball[] gameBalls = new Ball[NUM_BALLS];
        for (int index = 0; index < gameBalls.length; index++) {
            int ballSpeed = 4;
            int ballAngle = (45 * index) / NUM_BALLS;
            int ballRadius = 5;
            int startX = 400;
            int startY = SCREEN_HEIGHT - 100;
            Color ballColor = Color.WHITE;

            gameBalls[index] = createBall(startX, startY, ballRadius, ballColor, ballAngle, ballSpeed);
            gameBalls[index].addToGame(this);
        }
        numOfBalls.increase(NUM_BALLS);

        // Set the balls for the paddle
        playerPaddle.setBalls(gameBalls);
    }

    /**
     * Runs the game animation loop.
     * Manages the animation and timing of the game elements.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (numOfBlocks.getValue() <= 0) {
                deathRegion.removeHitListener(ballRemover);
                score.increase(100);
                gui.close();
                return;
            }
            if (numOfBalls.getValue() <= 0) {
                deathRegion.removeHitListener(ballRemover);
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis();
            Sleeper sleeper = new Sleeper();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long sleepTime = millisecondsPerFrame - usedTime;
            if (sleepTime > 0) {
                sleeper.sleepFor(sleepTime);
            }
        }
    }

    /**
     * Gets the GUI object associated with the game.
     *
     * @return the GUI object
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Initializes the colors for the blocks.
     *
     * @return an array of colors for the blocks
     */
    private Color[] initializeColors() {
        Color[] colors = new Color[NUM_ROWS];
        colors[0] = new Color(110, 110, 110);
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;
        colors[4] = Color.WHITE;
        return colors;
    }

    /**
     * Creates and adds a block to the game.
     *
     * @param x     the x-coordinate of the block
     * @param y     the y-coordinate of the block
     * @param xLen  the width of the block
     * @param yLen  the height of the block
     * @param color the color of the block
     */
    private void createAndAddBlock(int x, int y, int xLen, int yLen, Color color) {
        Block block = new Block(new Rectangle(new Point(x, y), xLen, yLen), color);
        block.addHitListener(blockRemover);
        block.addHitListener(scoreTrackingListener);
        block.addToGame(this);
        addBlock();
    }

    /**
     * Creates a block with the given parameters.
     *
     * @param x      the x-coordinate of the block
     * @param y      the y-coordinate of the block
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     * @return the created block
     */
    private Block createBlock(int x, int y, int width, int height, Color color) {
        return new Block(new Rectangle(new Point(x, y), width, height), color);
    }

    /**
     * Adds a boundary block to the game.
     *
     * @param x      the x-coordinate of the boundary
     * @param y      the y-coordinate of the boundary
     * @param width  the width of the boundary
     * @param height the height of the boundary
     * @param color  the color of the boundary
     */
    private void addBoundary(int x, int y, int width, int height, Color color) {
        Block boundaryBlock = createBlock(x, y, width, height, color);
        boundaryBlock.addToGame(this);
    }

    /**
     * Creates a ball with the given parameters.
     *
     * @param x      the x-coordinate of the ball
     * @param y      the y-coordinate of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     * @param angle  the angle of the ball's velocity
     * @param speed  the speed of the ball's velocity
     * @return the created ball
     */
    private Ball createBall(int x, int y, int radius, Color color, int angle, int speed) {
        Ball newBall = new Ball(new Point(x, y), radius, color, gameE);
        newBall.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        return newBall;
    }
}
