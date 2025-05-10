package Sprites; /**
 * @authour Harel Rifman
 * ID 217398338
 * **/

import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on the screen and
 * can be notified when time passes. This interface can be implemented by any
 * object that needs to be part of the game's graphical representation and needs
 * to update its state over time.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen.
     *
     * @param d the DrawSurface object on which the sprite should be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state
     * accordingly. This method is typically called by the game loop to ensure
     * the sprite's state is updated.
     */
    void timePassed();
}