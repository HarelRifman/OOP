package ScoreAndListiners;

import Sprites.Ball;
import Sprites.Block;

/**
 * The HitListener interface should be implemented by any class that wishes to listen for hit events.
 * It defines a method that is called whenever a collision occurs between a Block and a Ball.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit the Block that was hit
     * @param hitter   the Ball that hit the Block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
