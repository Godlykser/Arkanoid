//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
package animation;

import biuoop.DrawSurface;

/**
 * animation interface.
 */
public interface Animation {

    /**
     * does a single animation.
     *
     * @param d draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns whether or not the animation should stop.
     *
     * @return stop.
     */
    boolean shouldStop();
}
