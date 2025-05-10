/**
 * @authour Harel Rifman
 * ID 217398338
 * **/

/**
 * The Collidable interface represents objects that can be collided with.
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
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
