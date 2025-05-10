package Sprites; /**
 * The Block class represents a block that can be collided with in the game.
 *
 * @author Harel Rifman
 * ID 217398338
 */
import java.util.ArrayList;
import java.util.List;

import Run.Game;
import ScoreAndListiners.Collidable;
import ScoreAndListiners.HitListener;
import ScoreAndListiners.HitNotifier;
import biuoop.DrawSurface;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import geometryPrimitives.Velocity;

import java.awt.Color;
/**
 * Represents a block that can be collided with in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Creates a new block with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block's shape and position
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
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
     * @param hitter the ball that hits the block
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double horizontalSpeed = currentVelocity.getDx();
        double verticalSpeed = currentVelocity.getDy();
        double epsilon = 0.000001;

        // Check for collision with the left edge
        if (Math.abs(collisionPoint.getX() - this.rectangle.getUpperLeft().getX()) < epsilon) {
            if (currentVelocity.getDx() > 0) {
                horizontalSpeed = -horizontalSpeed;
            }
        }
        // Check for collision with the right edge
        if (Math.abs(collisionPoint.getX() - (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()))
                < epsilon) {
            horizontalSpeed = -horizontalSpeed;
        }

        // Check for collision with the top edge
        if (Math.abs(collisionPoint.getY() - this.rectangle.getUpperLeft().getY()) < epsilon) {
            if (currentVelocity.getDy() > 0) {
                verticalSpeed = -verticalSpeed;
            }
        }
        // Check for collision with the bottom edge
        if (Math.abs(collisionPoint.getY() - (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()))
                < epsilon) {
            if (currentVelocity.getDy() < 0) {
                verticalSpeed = -verticalSpeed;
            }
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
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
     * Checks if the color of the ball matches the color of the block.
     *
     * @param ball the ball to check against
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color == ball.getColor();
    }

    /**
     * Removes this block from the game.
     *
     * @param g the game from which to remove this block
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            return;
        }
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners == null || !this.hitListeners.contains(hl)) {
            return;
        }
        this.hitListeners.remove(hl);
    }

    // Notify all listeners about a hit event:
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }
}
