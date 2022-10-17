//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * countdown animation.
 */
public class CountdownAnimation implements Animation {
    // Finals.
    static final int X_LOCATION = 250;
    static final int Y_LOCATION = 400;
    static final int FONT = 32;

    // Fields.
    private final long seconds;
    private final int from;
    private int counter;
    private final SpriteCollection sprites;
    private boolean stop;

    /**
     * counts down from {countFrom} in intervals of {numOfSeconds}.
     *
     * @param numOfSeconds number of ms.
     * @param countFrom    count from.
     * @param gameScreen   what to draw in the background.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        seconds = (long) numOfSeconds;
        from = countFrom;
        counter = from;
        sprites = gameScreen;
        stop = false;
    }

    /**
     * does a single animation.
     *
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        d.setColor(Color.DARK_GRAY);
        d.drawText(X_LOCATION, Y_LOCATION, "" + counter, FONT);
        if (counter == 0) {
            stop = true;
        }
        if (counter < from) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(seconds);
        }
        counter--;
    }

    /**
     * returns whether or not the animation should stop.
     *
     * @return stop.
     */
    public boolean shouldStop() {
        return stop;
    }
}