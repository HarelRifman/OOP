/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class manages a collection of Sprite objects.
 * It provides methods to add sprites to the collection, notify all sprites
 * that time has passed, and draw all sprites on a given DrawSurface.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * initializes the sprite colection.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s the Sprite to be added
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection,
     * notifying them that time has passed and allowing them to update
     * their state accordingly.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }
    /**
     * Calls the drawOn(DrawSurface d) method on all sprites in the collection,
     * drawing them on the provided DrawSurface.
     *
     * @param d the DrawSurface on which all sprites should be drawn
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns a list of all sprites in the collection that are also instances of Ball.
     *
     * @return a list of Ball objects
     */
    public List<Ball> getBalls() {
        List<Ball> balls = new ArrayList<>();
        for (Sprite sprite : sprites) {
            if (sprite.isBall()) {
                balls.add((Ball) sprite);
            }
        }
        return balls;
    }
}
