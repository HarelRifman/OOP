package collections;
import ScoreAndListiners.Collidable;
import geometryPrimitives.Point;
/**
 * @authour Harel Rifman
 * ID 217398338
 **/
/**
 * Represents information about a collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collidable;

    /**
     * Creates a new CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point where the collision occurs
     * @param collidable the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}