//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import shapes.Line;
import shapes.Point;
import biuoop.DrawSurface;

/**
 * a class that creates a ball object and its methods.
 */

public class Ball implements Sprite {
    // final
    static final int RATIO = 5;
    static final int DIVIDED = 6;

    // Fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment env;

    // constructors

    /**
     * creates a ball object through point.
     *
     * @param center - center of ball object.
     * @param r      - ball's radius.
     * @param color  - ball's color.
     */

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * creates a ball object through coordinates.
     *
     * @param x     - x coordinate.
     * @param y     - y coordinate.
     * @param r     - ball's radius.
     * @param color - ball's color.
     */

    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    // getters

    /**
     * returns x value of ball's center.
     *
     * @return int variable.
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * returns y value of ball's center.
     *
     * @return int variable.
     */

    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns radius value of ball.
     *
     * @return int variable.
     */

    public Point getCenter() {
        return this.center;
    }

    /**
     * sets a new center for the ball.
     *
     * @param newCenter given new center.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * gets ball's radius.
     *
     * @return the radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * gets color value of ball.
     *
     * @return java color object.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * gets ball's velocity.
     *
     * @return velocity object.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * gets a line that starts at the ball's current location and ends at the ball's destination location.
     *
     * @return a line object.
     */
    public Line getTrajectory() {
        // gets displacement for x and y
        double dx = getVelocity().getDx();
        double dy = getVelocity().getDy();
        // returns line that starts at ball's center and ends at the location the ball will end up with cur trajectory
        return new Line(center, new Point(center.getX() + dx, center.getY() + dy));
    }

    // General Functions.

    /**
     * notifies ball time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * draws ball on given surface.
     *
     * @param surface surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // sets color and fills circle with given color
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * moves the ball one step.
     */
    public void moveOneStep() {
        // if there's no velocity, does nothing.
        if (getVelocity() == null) {
            return;
        }
        // gets trajectory line.
        Line trajectory = getTrajectory();
        // gets collision info
        CollisionInfo info = env.getClosestCollision(trajectory);
        // if there was no collision, move ball
        if (info == null) {
            this.center = (trajectory.end());
            // else, if hit an object, perform the following
        } else {
            // create a point close to the point of impact
            // moves center to almost hit location
            this.center = new Point((info.collisionPoint().getX() * RATIO + this.center.getX()) / DIVIDED,
                    ((info.collisionPoint().getY() * RATIO) + this.center.getY()) / DIVIDED);
            // gets the object hit
            Collidable hitObj = info.collisionObject();
            // gets the velocity using hit
            Velocity newVelocity = hitObj.hit(this, info.collisionPoint(), this.getVelocity());
            // sets the new velocity
            this.setVelocity(newVelocity);
        }
    }

    // setters

    /**
     * sets velocity through velocity object.
     *
     * @param ve - velocity object.
     */
    public void setVelocity(Velocity ve) {
        this.v = ve;
    }

    /**
     * adds ball to game.
     *
     * @param gameLevel game environment object to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * sets velocity through x, y displacement.
     *
     * @param dx - delta x.
     * @param dy - delta y.
     */

    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * sets the environment for the game.
     *
     * @param environment the game environment.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.env = environment;
    }

    /**
     * gets the ball's set environment.
     *
     * @return game environment.
     */
    private GameEnvironment getGameEnvironment() {
        return env;
    }

    /**
     * moves balls according to current velocity in a given square parameter.
     *
     * @param x1 - top left x coordinate.
     * @param y1 - top left y coordinate.
     * @param x2 - bottom right x coordinate.
     * @param y2 - bottom right y coordinate.
     */

    public void moveInSquare(int x1, int y1, int x2, int y2) {
        // if there's no velocity, does nothing
        if (getVelocity() == null) {
            return;
        }
        // changes ball's center's location
        this.center = this.getVelocity().applyToPoint(this.center);
        // if hits borders of given rectangle coordinates, changes velocity accordingly
        if ((getX() - getSize()) <= x1 || getX() + getSize() >= x2) {
            setVelocity(-getVelocity().getDx(), getVelocity().getDy());
        }
        if ((getY() - getSize()) <= y1 || (getY() + getSize()) >= y2) {
            setVelocity(getVelocity().getDx(), -getVelocity().getDy());
        }
    }
}
