//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */


import shapes.Point;

/**
 * a class that creates a velocity object and its methods.
 */

public class Velocity {

    // fields
    private double x;
    private double y;
    // constructor

    /**
     * creates a velocity object based on x and y displacements.
     *
     * @param dx - delta x.
     * @param dy - delta y.
     */

    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    // getters

    /**
     * creates a velocity object from angle and speed.
     *
     * @param angle - the angle.
     * @param speed - the speed.
     * @return velocity object.
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * gets object's x displacement value.
     *
     * @return double variable.
     */

    public double getDx() {
        return this.x;
    }

    /**
     * gets object's y displacement value.
     *
     * @return double variable.
     */

    public double getDy() {
        return this.y;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - changes a point's placement.
     * @return the point after displacement.
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + x, p.getY() + y);
    }
}