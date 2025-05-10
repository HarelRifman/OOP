package Sprites;

import Run.Game;
import ScoreAndListiners.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score on the game screen.
 * It implements the Sprite interface to be updated and drawn during the game loop.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private static final int TXT_SIZE = 30;

    /**
     * Constructs a ScoreIndicator with the specified score counter.
     *
     * @param score the counter holding the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String text = String.format("Score: %d", score.getValue());
        int height = 20;
        d.drawText((Game.SCREEN_WIDTH - TXT_SIZE * text.length()) / 2 + 1,
                (height + TXT_SIZE) / 2 + 1, text, TXT_SIZE);
    }

    /**
     * Currently does nothing for the ScoreIndicator.
     * It's implemented due to the Sprite interface requirement.
     */
    @Override
    public void timePassed() {
        // Currently does nothing for the ScoreIndicator
    }

    /**
     * Adds the ScoreIndicator to the game's sprite collection.
     *
     * @param g the game to add this ScoreIndicator to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
