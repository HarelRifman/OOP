/**
 * @authour Harel Rifman
 * ID 217398338
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game environment, maintaining a collection of collidable objects.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

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
        double closestDistance = -1;

        for (Collidable collidable : this.collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            List<Point> intersectionPointsList = rect.intersectionPoints(trajectory);
            for (Point point : intersectionPointsList) {
                double distance = trajectory.start().distance(point);
                if (distance < closestDistance || closestDistance == -1) {
                    closestDistance = distance;
                    closestPoint = point;
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
