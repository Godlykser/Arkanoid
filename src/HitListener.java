//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * hit listener interface.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}