package ScoreAndListiners;

import Sprites.Ball;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import geometryPrimitives.Velocity;

/**
 * The Collidable interface represents objects that can be collided with.
 *
 * @author Harel Rifman
 * ID 217398338
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collision point with the given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param hitter the ball that hits the object
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
