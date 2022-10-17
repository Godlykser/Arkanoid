//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

package shapes;

import java.util.List;

/**
 * a class that creates a line object and its methods.
 */

public class Line {

    // Fields
    private Point start;
    private Point end;

    // constructors

    /**
     * a constructor that works with points.
     *
     * @param start - starting point of line.
     * @param end   - end point of line.
     */

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * a constructor that works with coordinates.
     *
     * @param x1 - x coordinate for start.
     * @param x2 - x coordinate for end.
     * @param y1 - y coordinate for start.
     * @param y2 - y coordinate for end.
     */

    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return double variable.
     */

    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return point variable.
     */

    public Point middle() {

        // returns a new point representing the middle of the line
        return new Point((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return point variable.
     */

    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * Returns the end point of the line.
     *
     * @return point variable.
     */

    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other - another line.
     * @return whether the lines are intersecting or not.
     */

    public boolean isIntersecting(Line other) {

        // returns whether or not intersection point is null
        return (intersectionWith(other) != null);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other - another line.
     * @return point of intersection.
     */

    public Point intersectionWith(Line other) {

        // first line into equation form
        double a1 = end().getY() - start().getY();
        double b1 = start().getX() - end().getX();
        double c1 = (start().getX() * a1 + start().getY() * b1);

        // second line into equation form
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = other.start().getX() * a2 + other.start().getY() * b2;

        // calculate the matrix's determinant
        double det = a1 * b2 - b1 * a2;
        // if det is 0 the lines are parallel and returns null
        if (det == 0) {
            return null;
        }
        // else lines intersect
        // calculate x, y vals
        double newX = (b2 * c1 - b1 * c2) / det;
        double newY = (a1 * c2 - a2 * c1) / det;
        // create intersection point
        Point iPoint = new Point(newX, newY);
        // if distance from middle of the lines is greater than line length/2 return null
        if (middle().distance(iPoint) > length() / 2 || other.middle().distance(iPoint) > other.length() / 2) {
            return null;
        }
        // else returns intersection point
        return iPoint;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other - another line.
     * @return true or false.
     */

    public boolean equals(Line other) {
        return (start().equals(other.start()) && end().equals(other.end()));
    }

    /**
     * gets closest intersection point to the line's starting point.
     *
     * @param rect a rectangle.
     * @return closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // gets the intersection points list
        List<Point> pts = rect.intersectionPoints(this);
        // if list is empty there are no intersection points, so returns null
        if (pts.isEmpty()) {
            return null;
        }
        // gets first point for reference
        Point closest = pts.get(0);
        // checks distance between each point
        for (Point pt : pts) {
            if (pt.distance(this.start()) < closest.distance(this.start())) {
                closest = pt;
            }
        }
        return closest;
    }

    /**
     * we know that the sum of two lines in a triangle is always greater than the third, hence if the point is on
     * the current line then the sum of the distances between the point and the start and end of the line will only be
     * bigger that the length of the line if the point is not on it.
     *
     * @param pt - a point we want to check if exists on current line
     * @return true or false
     */
    public boolean isPtOnLine(Point pt) {
        return (pt.distance(this.start()) + pt.distance(this.end) <= this.start.distance(this.end));
    }
}
