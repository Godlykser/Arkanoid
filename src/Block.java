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
 * a class for block objects.
 */
public class Block implements Collidable, Sprite {

    // Fields.

    private final shapes.Rectangle rect;
    private final Color color;
    private final List<HitListener> hitListeners;

    // Constructor.

    /**
     * a constructor for block objects.
     *
     * @param rect  the block's rectangle.
     * @param color the block's color.
     */

    public Block(shapes.Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    // Adders

    /**
     * adds block to the game.
     *
     * @param gameLevel game to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        // adds to both sprites and collidables.
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * gets the rectangle appropriate for the collided block.
     *
     * @return rectangle.
     */

    /**
     * adds a listener to the block object.
     *
     * @param listener listener object to add.
     */
    public void addListener(HitListener listener) {
        hitListeners.add(listener);
    }

    // Removers

    /**
     * removes listener from block.
     *
     * @param hl listener to remove.
     */
    public void removeListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    // Getters

    @Override
    /**
     * gets block's rectangle.
     */
    public shapes.Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * gets the block's color.
     *
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }

    // Observer functions.

    /**
     * notifies observers of hit occurring.
     *
     * @param hitter the ball hitting the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * returns number of listeners.
     *
     * @return num listeners.
     */
    public int getNumListeners() {
        return hitListeners.size();
    }

    // General functions.

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  point of collision with rectangle {rect}.
     * @param currentVelocity the current velocity of the object.
     * @param hitter the ball hitting the block.
     * @return a new velocity matching the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // notifies listeners.
        this.notifyHit(hitter);
        // checks if object hits.
        if (rect.getTop().isPtOnLine(collisionPoint) || rect.getBottom().isPtOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (rect.getLeft().isPtOnLine(collisionPoint) || rect.getRight().isPtOnLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * draws the block on the given surface.
     *
     * @param d surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        shapes.Rectangle r = getCollisionRectangle();
        int x = (int) r.getUpperLeft().getX();
        int y = (int) r.getUpperLeft().getY();
        int w = (int) r.getWidth();
        int h = (int) r.getHeight();
        d.fillRectangle(x, y, w, h);
    }

    /**
     * notifies the block time has passed.
     */
    @Override
    public void timePassed() {
        // currently supposed to be empty.
    }


}
