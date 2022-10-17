//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import biuoop.DrawSurface;

/**
 * an interface for sprite objects.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d surface to draw sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite time has passed.
     */
    void timePassed();

    /**
     * add to game.
     *
     * @param gameLevel game object to add the sprite to.
     */
    void addToGame(GameLevel gameLevel);
}