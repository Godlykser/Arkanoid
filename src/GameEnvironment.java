import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * a class for game environments.
 */
//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * game environment class.
 */
public class GameEnvironment {

    // Fields
    private final List<Collidable> collidableList;

    // Constructors

    /**
     * creates a game environment object.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c - collidable object
     */

    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory line of trajectory.
     * @return returns
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        // creates closest collision point and hit object variables.
        Point closestPt = null;
        Collidable obj = null;
        // for each collidable object in the game-frame.
        for (Collidable c : collidableList) {
            // gets the respective rectangle.
            Rectangle rect = c.getCollisionRectangle();
            // gets the collision points.
            List<Point> intersections = rect.intersectionPoints(trajectory);
            // checks for collisions and if found sets variables appropriately.
            for (Point pt : intersections) {
                if (closestPt == null || pt.distance(trajectory.start()) < closestPt.distance(trajectory.start())) {
                    closestPt = pt;
                    obj = c;
                }
            }
        }
        // if no collision point found, return null.
        if (closestPt == null) {
            return null;
        }
        // else returns the collisionInfo
        return new CollisionInfo(closestPt, obj);
    }


    // getters

    /**
     * gets collidable list.
     *
     * @return collidable list.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * draws on surface.
     *
     * @param surface - given surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        // for each collidable item in collidable list.
        for (Collidable c : getCollidableList()) {
            // gets relevant information from the collidable.
            int startX = (int) c.getCollisionRectangle().getUpperLeft().getX();
            int startY = (int) c.getCollisionRectangle().getUpperLeft().getY();
            int width = (int) c.getCollisionRectangle().getWidth();
            int height = (int) c.getCollisionRectangle().getHeight();
            // draws the rectangle.
            surface.fillRectangle(startX, startY, width, height);
        }
    }

    // Removers.

    /**
     * removes collidable object from list.
     *
     * @param c collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
}