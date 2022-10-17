//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
package animation;

import biuoop.DrawSurface;

public class PauseScreen implements Animation {
    // Fields.
    private boolean stop;

    public PauseScreen() {
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    public boolean shouldStop() { return this.stop; }
}
