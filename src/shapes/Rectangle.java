//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

package shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * a class that creates a rectangle object.
 */
public class Rectangle {
    // Finals
    static final int SIDES_NUM = 4;

    // Fields
    private Point upperLeft;
    private double width;
    private double height;

    // Constructors

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line a line to check rectangle's intersection points with.
     * @return ArrayList of points {intPoints}.
     */

    public List<Point> intersectionPoints(Line line) {
        // creates list for lines and lists for intersection points
        List<Point> intPoints = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        // creates lines out of rectangle points and adds them to the line-list
        lines.add(getTop());
        lines.add(getRight());
        lines.add(getBottom());
        lines.add(getLeft());
        // for each line in our list of lines
        for (Line l : lines) {
            // if line intersects with triangle, returns intersection point
            if (l.isIntersecting(line)) {
                intPoints.add(l.intersectionWith(line));
            }
        }
        return intPoints;
    }


    // Return the width and height of the rectangle

    /**
     * returns the width of the rectangle.
     *
     * @return double {width}.
     */

    public double getWidth() {
        return this.width;
    }

    /**
     * returns the height of the rectangle.
     *
     * @return double {height}.
     */

    public double getHeight() {
        return this.height;
    }

    // Getters

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return point var.
     */

    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right point of the rectangle.
     *
     * @return point var.
     */

    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * Returns the bottom-right point of the rectangle.
     *
     * @return point var.
     */

    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * Returns the bottom-left point of the rectangle.
     *
     * @return point var.
     */

    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * gets the line object for the top part of the rectangle.
     *
     * @return line object.
     */
    public Line getTop() {
        return new Line(getUpperLeft(), getUpperRight());
    }

    /**
     * gets the line object for the right side of the rectangle.
     *
     * @return line object.
     */
    public Line getRight() {
        return new Line(getUpperRight(), getBottomRight());
    }

    /**
     * gets the line object for the bottom part of the rectangle.
     *
     * @return line object.
     */
    public Line getBottom() {
        return new Line(getBottomRight(), getBottomLeft());
    }

    /**
     * gets the line object for the left side of the rectangle.
     *
     * @return line object.
     */
    public Line getLeft() {
        return new Line(getBottomLeft(), getUpperLeft());
    }
}
