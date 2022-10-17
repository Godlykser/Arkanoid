//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * hit notifier interface.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener to add.
     */
    void addHitListener(HitListener hl);

    //

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener to remove.
     */
    void removeHitListener(HitListener hl);
}