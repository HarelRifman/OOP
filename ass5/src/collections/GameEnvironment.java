package collections; /**
 * @authour Harel Rifman
 * ID 217398338
 **/

import ScoreAndListiners.Collidable;
import geometryPrimitives.Line;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game environment, maintaining a collection of collidable objects.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * Creates a new game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, returns null. Else, returns the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line representing the object's trajectory
     * @return the information about the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        double closestDistance = Double.MAX_VALUE;

        for (Collidable collidable : this.collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (intersectionPoint != null) {
                double distance = trajectory.start().distance(intersectionPoint);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPoint = intersectionPoint;
                    closestCollidable = collidable;
                }
            }
        }

        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
