//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * creates an animation runner.
 */
public class AnimationRunner {
    // Fields.
    private final GUI gui;
    private final int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor for animation runner.
     *
     * @param gui graphical interface.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * runs animation.
     *
     * @param animation animation object.
     */
    public void run(Animation animation) {
        this.sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / 60;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * returns num fps.
     * @return fps.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }
}
