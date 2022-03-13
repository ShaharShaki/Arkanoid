//208524181

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahar Shaki
 * this calss is creating the object "rectangle"
 */
public class Rectangle {

    private static final double EPSILON = Math.pow(10, -2);

    /*
    Fields
     */
    private double width;
    private double height;
    private Point upperLeft;
    private Line up;
    private Line down;
    private Line right;
    private Line left;

    /**.
     * constructor
     * @param upperLeft point
     * @param width double
     * @param height double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //create all the rectangle points.
        Point downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        //create all the rectangle ribs
        this.up = new Line(upperLeft, upperRight);
        this.down = new Line(downLeft, downRight);
        this.right = new Line(upperRight, downRight);
        this.left = new Line(upperLeft, downLeft);
    }

    /**.
     * check the intersection points between a given line the the rectangle.
     * @param line Line
     * @return an array of objects Points, can be empty if there is no intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //create the array of points.
        List<Point> intersectionList = new ArrayList<>();
        //get the intersection with the up line if existing.
        if (line.intersectionWith(this.up) != null) {
            intersectionList.add(line.intersectionWith(this.up));
        }
        //get the intersection with the down line if existing.
        if (line.intersectionWith(this.down) != null) {
            intersectionList.add(line.intersectionWith(this.down));
        }
        //get the intersection with the right line if existing.
        if (line.intersectionWith(this.right) != null) {
            intersectionList.add(line.intersectionWith(this.right));
        }
        //get the intersection with the left line if existing.
        if (line.intersectionWith(this.left) != null) {
            intersectionList.add(line.intersectionWith(this.left));
        }
        //return the array.
        return intersectionList;
    }

    /**
     * method get the collision point and check on the given rectangle, return true. false else.
     * @param collisionPoint the collision point
     * @return true or false
     */
    public boolean isIntersectingWithRectangle(Point collisionPoint) {
        //if the collision point is on the up side
        if (this.onUp(collisionPoint)) {
            return true;
        }
        //if the collision point is on the down side
        if (this.onDown(collisionPoint)) {
            return true;
        }
        //if the collision point is on the right side
        if (this.onRight(collisionPoint)) {
            return true;
        }
        //if the collision point is on the left side
        if (this.onLeft(collisionPoint)) {
            return true;
        }
        //no collision, return false.
        return false;
    }

    /**
     * boolean method check if a given point is on the up line of the rectangle.
     * @param p the point
     * @return true or false depend if the point is on the up line
     */
    public boolean onUp(Point p) {
        return (this.getUp().start().getX() <= EPSILON + p.getX() && p.getX()
                <= this.getUp().end().getX() - EPSILON && Math.abs(p.getY() - this.getUp().start().getY()) < EPSILON);
    }

    /**
     * boolean method check if a given point is on the down line of the rectangle.
     * @param p the point
     * @return true or false depend if the point is on the down line
     */
    public boolean onDown(Point p) {
        return (this.getDown().start().getX() <= EPSILON + p.getX() && p.getX()
                <= this.getDown().end().getX() - EPSILON
                && Math.abs(p.getY() - this.getDown().start().getY()) < EPSILON);
    }

    /**
     * boolean method check if a given point is on the right line of the rectangle.
     * @param p the point
     * @return true or false depend if the point is on the right line
     */
    public boolean onRight(Point p) {
        return (this.getRight().start().getY() - EPSILON <= p.getY() && p.getY()
                <= this.getRight().end().getY() + EPSILON
                && Math.abs(p.getX() - this.getRight().start().getX()) < EPSILON);
    }

    /**
     * boolean method check if a given point is on the left line of the rectangle.
     * @param p the point
     * @return true or false depend if the point is on the left line
     */
    public boolean onLeft(Point p) {
        return (this.getLeft().start().getY() - EPSILON <= p.getY() && p.getY()
                <= this.getLeft().end().getY() + EPSILON
                && Math.abs(p.getX() - this.getLeft().start().getX()) < EPSILON);

    }
    /**
     * getter width of rectangle.
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**.
     * getter height of rectangle.
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**.
     * getter upper left point of the rectangle
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**.
     * getter up line of rectangle.
     * @return the up line
     */
    public Line getUp() {
        return this.up;
    }

    /**.
     * getter down line of rectangle.
     * @return the down line
     */
    public Line getDown() {
        return this.down;
    }

    /**.
     * getter left line of rectangle.
     * @return the left line
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * getter right line of rectangle.
     * @return the right line
     */
    public Line getRight() {
        return this.right;
    }
}