/**
 * @authour Harel Rifman
 * ID 217398338
 * **/

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Paddle class represents a paddle in the game, which is controlled by the player
 * using the keyboard. The paddle can move left and right and collide with balls.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final int speed;
    private Game g;

    /**
     * Constructs a Paddle with the specified rectangle, speed, and keyboard sensor.
     *
     * @param rectangle the rectangle representing the paddle's shape and position
     * @param speed the speed of the paddle's movement
     * @param keyboard the keyboard sensor for detecting user input
     */
    public Paddle(Rectangle rectangle, int speed, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.speed = speed;
        this.keyboard = keyboard;
    }

    /**
     * @return false because paddle is not a ball
     */
    @Override
    public boolean isBall() {
        return false;
    }

    /**
     * Moves the paddle to the left by its speed, if possible.
     */
    public void moveLeft() {
        if (rectangle.getUpperLeft().getX() - Game.BORDER_WIDTH - speed >= 0) {
            if (checkBallFromSide(-1)) {
                return;
            }
            rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() - speed,
                    rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        } else {
            for (Ball b : this.g.getSprites().getBalls()) {
                if (b.getCenter().getY() >= rectangle.getUpperLeft().getY()) {
                    if (b.getCenter().getX() <= Game.WIDTH - Game.BORDER_WIDTH
                            && b.getCenter().getX() >= Game.WIDTH
                            - (Game.BORDER_WIDTH + rectangle.getWidth() + speed)) {
                        return;
                    }
                }
            }
            rectangle = new Rectangle(new Point(Game.WIDTH - Game.BORDER_WIDTH - rectangle.getWidth() - 1,
                    rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        }
    }

    /**
     * Checks if there is a ball on the side of the paddle.
     *
     * @param i 1 to check from the right, -1 to check from the left
     * @return true if there is a ball on the specified side, false otherwise
     */
    public boolean checkBallFromSide(int i) {
        if (i != 1 && i != -1) {
            return false;
        }
        for (Ball b : this.g.getSprites().getBalls()) {
            if (b.getCenter().getY() >= rectangle.getUpperLeft().getY()) {
                if (b.getCenter().getX() >= rectangle.getUpperLeft().getX() + i * speed
                        && b.getCenter().getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth() + i * speed) {
                    if (i == 1) {
                        b.setVelocity(this.hit(new Point(this.rectangle.getUpperLeft().getX() + rectangle.getWidth(),
                                b.getY()), b.getVelocity()));
                        return true;
                    }
                    b.setVelocity(this.hit(new Point(this.rectangle.getUpperLeft().getX(), b.getY()), b.getVelocity()));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Moves the paddle to the right by its speed, if possible.
     */
    public void moveRight() {
        if (rectangle.getUpperLeft().getX() + rectangle.getWidth() + speed + Game.BORDER_WIDTH <= Game.WIDTH) {
            if (checkBallFromSide(1)) {
                return;
            }
            rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX() + speed,
                    rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        } else {
            for (Ball b : this.g.getSprites().getBalls()) {
                if (b.getCenter().getY() >= rectangle.getUpperLeft().getY()) {
                    if (b.getCenter().getX() >= Game.BORDER_WIDTH
                            && b.getCenter().getX() <= Game.BORDER_WIDTH + rectangle.getWidth() + speed) {
                        return;
                    }
                }
            }
            rectangle = new Rectangle(new Point(Game.BORDER_WIDTH,
                    rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());
        }
    }

    /**
     * Updates the paddle's position based on user input.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Gets the rectangle representing the paddle's collision shape.
     *
     * @return the paddle's collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the paddle that a collision occurred at the specified collision point with the given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // Determine the region of the collision
        double paddleX = rectangle.getUpperLeft().getX();
        int region = getRegion(collisionPoint, paddleX);

        // Calculate the new angle based on the region
        int angle = 30 * region; // Adjust angle calculation to match new direction
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // Return the new velocity calculated from the angle and speed
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Determines the region of the paddle where the collision occurred based on the collision point.
     *
     * @param collisionPoint the point where the collision occurred
     * @param paddleX the X-coordinate of the upper-left corner of the paddle
     * @return the region of the paddle where the collision occurred, ranging from -2 to 2
     */
    private int getRegion(Point collisionPoint, double paddleX) {
        double paddleWidth = rectangle.getWidth();
        double collisionX = collisionPoint.getX();
        int region;
        if (collisionX > paddleX + (paddleWidth * 4 / 5)) {
            region = 2;
        } else if (collisionX > paddleX + (paddleWidth * 3 / 5)) {
            region = 1;
        } else if (collisionX > paddleX + (paddleWidth * 2 / 5)) {
            region = 0;
        } else if (collisionX > paddleX + (paddleWidth * 1 / 5)) {
            region = -1;
        } else {
            region = -2;
        }
        return region;
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game to add this paddle to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
        this.g = g;
        keyboard = g.getGui().getKeyboardSensor();
    }
}
