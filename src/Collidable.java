//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import shapes.Point;
import shapes.Rectangle;

/**
 * collidable interface.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * gets the collided object's rectangle.
     *
     * @return rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the ball's current velocity.
     * @param hitter          ball.
     * @return a new velocity after the impact.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
