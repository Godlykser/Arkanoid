//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * ball remover class.
 */
public class BallRemover implements HitListener {
    // Fields.
    private GameLevel gameLevel;
    private Counter remainingBalls;

    // Constructors.

    /**
     * constructor for the BallRemover class.
     *
     * @param gameLevel      current game.
     * @param remainingBalls counter for remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    /**
     * what to do when a hit occurs.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
