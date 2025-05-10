/**
 * @authour Harel Rifman
 * ID 217398338
 * **/

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents a block that can be collided with in the game.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle rectangle;
    private Color color;

    /**
     * Creates a new block with the specified rectangle.
     *
     * @param rectangle the rectangle representing the block's shape and position
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.white; // Default color
    }

    /**
     * Creates a new block with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block's shape and position
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Returns the "collision shape" of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Called to notify the block that time has passed.
     * Currently, this method does not perform any action for the block.
     */
    @Override
    public void timePassed() {
        // No action is required for the block as time passes
    }

    /**
     * Adds this block to the game.
     *
     * @param g the game to add this block to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Notifies the block that a collision occurred at the specified collision point with the given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double horizontalSpeed = currentVelocity.getDx();
        double verticalSpeed = currentVelocity.getDy();
        double epsilon = 0.0000000001;

        // Check for collision with the left edge
        if (Math.abs(collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) < epsilon) {
            if (currentVelocity.getDx() > 0) {
                horizontalSpeed = -horizontalSpeed;
            }
        }
        // Check for collision with the right edge
        if (Math.abs(collisionPoint.getX() - (this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth())) < epsilon) {
            if (currentVelocity.getDx() < 0) {
                horizontalSpeed = -horizontalSpeed;
            }
        }
        // Check for collision with the top edge
        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < epsilon) {
            if (currentVelocity.getDy() > 0) {
                verticalSpeed = -verticalSpeed;
            }
        }
        // Check for collision with the bottom edge
        if (Math.abs(collisionPoint.getY()
                - (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight())) < epsilon) {
            if (currentVelocity.getDy() < 0) {
                verticalSpeed = -verticalSpeed;
            }
        }

        return new Velocity(horizontalSpeed, verticalSpeed);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        if (color == null) {
            this.color = Color.white;
        }
        surface.setColor(this.color);
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        surface.fillRectangle(x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Indicates whether this collidable object is a ball.
     *
     * @return false, as this object is not a ball
     */
    @Override
    public boolean isBall() {
        return false;
    }
}
