package ScoreAndListiners;

import Run.Game;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;

/**
 * The BallRemover class is responsible for removing balls from the game
 * when they hit a block and updating the count of remaining balls.
 *
 * @author Harel Rifman
 * ID 217398338
 */
public class BallRemover implements HitListener {
    private final Game game;
    private Counter ballsLeft;
    private Paddle paddle;
    /**
     * Constructs a BallRemover.
     *
     * @param game      the game instance from which balls are to be removed
     * @param ballsLeft the counter that keeps track of the remaining balls
     * @param paddle    the paddle associated with the game
     */
    public BallRemover(Game game, Counter ballsLeft, Paddle paddle) {
        this.game = game;
        this.ballsLeft = ballsLeft;
        this.paddle = paddle;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Removes the ball from the game and decreases the balls counter.
     *
     * @param beingHit the block that is hit by the ball
     * @param hitter   the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game, paddle);
        ballsLeft.decrease(1);
    }
}
