//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * a score tracking listener object.
 */
public class ScoreTrackingListener implements HitListener {
    // Fields.
    private Counter currentScore;

    // Constructor.

    /**
     * constructs a ScoreTrackingListener object.
     *
     * @param scoreCounter counter for current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    // functions.

    /**
     * what to do if ball hits block.
     *
     * @param beingHit the block hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeListener(this);
    }
}
