//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import biuoop.DrawSurface;

/**
 * creates a score indicator object.
 */
public class ScoreIndicator implements Sprite {
    // Finals.
    static final int FONT_SIZE = 18;
    static final int Y_POSITION = 250;
    static final int X_POSITION = 40;

    // Fields.
    private final Counter score;

    /**
     * initializes score indicator from score counter.
     *
     * @param score counter for score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d surface to draw sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Y_POSITION, X_POSITION, "Score: " + score.getValue(), FONT_SIZE);
    }

    /**
     * notify the sprite time has passed.
     */
    @Override
    public void timePassed() {
        // empty for now.
    }

    /**
     * add to game.
     *
     * @param gameLevel game object to add the sprite to.
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
