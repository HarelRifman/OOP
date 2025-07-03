package collections; /**
 * @authour Harel Rifman
 * ID 217398338
 **/
import Sprites.Sprite;
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
     * Removes a sprite from the collection.
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
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
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
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
}
