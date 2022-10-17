//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import shapes.Point;

/**
 * a class for collision information.
 */
public class CollisionInfo {

    // Fields
    private Point pt;
    private Collidable obj;

    // constructor

    /**
     * constructor for collision info objects.
     *
     * @param pt  point of collision.
     * @param obj collided object.
     */
    public CollisionInfo(Point pt, Collidable obj) {
        this.pt = pt;
        this.obj = obj;
    }

    /**
     * returns point of collision.
     *
     * @return the point at which the collision occurs.
     */

    public Point collisionPoint() {
        return this.pt;
    }

    /**
     * returns the object with which the collision occurred.
     *
     * @return the collidable object involved in the collision.
     */

    public Collidable collisionObject() {
        return this.obj;
    }
}