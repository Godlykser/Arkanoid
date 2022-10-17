//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import animation.Animation;
import biuoop.DrawSurface;

/**
 * creates an end screen (win or lose).
 */
public class EndScreen implements Animation {
    // Fields.
    private boolean stop;
    private final Counter score;
    private final String winOrLose;


    /**
     * initializes screen object.
     *
     * @param score        counter for score.
     * @param winCondition whether won or lost.
     */
    public EndScreen(Counter score, boolean winCondition) {
        // sets values.
        this.score = score;
        this.stop = false;
        // if won, sets sentence to Win; otherwise, Lose.
        winOrLose = (winCondition) ? "Win" : "Lose";
    }


    /**
     * does a single animation.
     *
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {

        d.drawText(10, d.getHeight() / 2, "You " + winOrLose + "! Your score is " + score.getValue(), 32);
    }

    /**
     * returns whether or not the animation should stop.
     *
     * @return stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
