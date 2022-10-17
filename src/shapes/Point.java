//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

package shapes;

/**
 * a class that defines a point object and its methods.
 */

public class Point {
    // Fields
    private double x;
    private double y;

    // constructor

    /**
     * a constructor for a point.
     *
     * @param x - x value of point.
     * @param y - y value of point.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point

    /**
     * returns distance from other point.
     *
     * @param other - another point.
     * @return double variable.
     */

    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2));
    }

    // equals -- return true is the points are equal, false otherwise

    /**
     * returns whether one point is equal to another.
     *
     * @param other - another point.
     * @return boolean expression.
     */

    public boolean equals(Point other) {
        // returns result of comparison between points
        return getX() == other.getX() && getY() == other.getY();
    }

    // getters

    /**
     * returns x value of point.
     *
     * @return double variable.
     */

    public double getX() {
        return this.x;
    }

    /**
     * returns y value of point.
     *
     * @return double variable.
     */

    public double getY() {
        return this.y;
    }
}
