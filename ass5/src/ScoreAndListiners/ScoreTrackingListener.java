package ScoreAndListiners;

import Sprites.Ball;
import Sprites.Block;

/**
 * The ScoreTrackingListener class implements HitListener to track and update the score
 * when a block is hit by a ball in the game.
 * <p>
 * This class increases the current score by 5 for each hit event and removes itself
 * as a listener from the block that was hit.
 * </p>
 * <p>
 * Author: Harel Rifman
 * ID: 217398338
 * </p>
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates the score when a block is hit by increasing it by 5.
     * Removes itself as a listener from the block that was hit.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
