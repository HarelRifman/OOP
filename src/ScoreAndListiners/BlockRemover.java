package ScoreAndListiners;

import Run.Game;
import Sprites.Ball;
import Sprites.Block;
import com.sun.jdi.BooleanType;

/**
 * The BlockRemover class is responsible for removing blocks from the game
 * when they are hit by a ball, and updating the count of remaining blocks.
 *
 * @author Harel Rifman
 * ID 217398338
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game the game instance from which blocks are to be removed
     * @param remainingBlocks the counter that keeps track of the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Removes the block from the game and decreases the remaining blocks counter.
     * Also removes this listener from the block being removed.
     *
     * @param beingHit the block that is hit by the ball
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}
