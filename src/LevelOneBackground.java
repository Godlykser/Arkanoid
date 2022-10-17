//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import shapes.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level one's background.
 */
public final class LevelOneBackground implements Sprite {
    // Finals.
    static final int X_LOC = 400;
    static final int Y_LOC = 200;
    static final int INNER_RADIUS = 75;
    static final int MID_RADIUS = 100;
    static final int OUTER_RADIUS = 125;
    static final int LINE = 150;

    // Fields.
    private static LevelOneBackground levelBackground = null;
    private final List<Ball> balls;
    private final Block background;


    // Constructor.

    /**
     * constructor for singleton instance.
     */
    private LevelOneBackground() {
        balls = new ArrayList<>();
        background = new Block(new shapes.Rectangle(new Point(0, 0), 800, 600), Color.black);
        balls.add(new Ball(new Point(X_LOC, Y_LOC), OUTER_RADIUS, Color.BLUE));
        balls.add(new Ball(new Point(X_LOC, Y_LOC), OUTER_RADIUS - 1, Color.BLACK));
        balls.add(new Ball(new Point(X_LOC, Y_LOC), MID_RADIUS, Color.BLUE));
        balls.add(new Ball(new Point(X_LOC, Y_LOC), MID_RADIUS - 1, Color.BLACK));
        balls.add(new Ball(new Point(X_LOC, Y_LOC), INNER_RADIUS, Color.BLUE));
        balls.add(new Ball(new Point(X_LOC, Y_LOC), INNER_RADIUS - 1, Color.BLACK));
    }

    /**
     * gets singleton instance of the background.
     * @return returns background.
     */
    public static Sprite getInstance() {
        if (levelBackground == null) {
            levelBackground = new LevelOneBackground();
        }
        return levelBackground;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d surface to draw sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (Ball ball : balls) {
            ball.drawOn(d);
        }
        background.drawOn(d);
        d.drawLine(X_LOC + LINE, Y_LOC, X_LOC - LINE, Y_LOC);
        d.drawLine(X_LOC, Y_LOC + LINE, X_LOC, Y_LOC - LINE);
    }

    /**
     * notify the sprite time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add to game.
     *
     * @param gameLevel game object to add the sprite to.
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(background);
        for (Ball ball : balls) {
            gameLevel.addSprite(ball);
        }
    }
}
