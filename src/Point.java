//208524181

/**
 * @author Shahar Shaki
 * the class creat an object "Point", get it distance from another point, check if 2 points are equal
 * and get the x and y value of a point.
 */
public class Point {

    private static final double EPSILON = Math.pow(10, -2);

    /*
    Fields
     */
    private double x;
    private double y;

    /**.
     * constructor
     * @param x int
     * @param y int
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the method return the distance between two points.
     * @param other Line
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((other.x - this.getX()) * (other.getX() - this.getX()))
                + ((other.y - this.getY()) * (other.y - this.getY())));
    }

    /**
     * the method check if two line are equal.
     * @param other a point to check on.
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return ((Math.abs(other.getX() - this.getX()) < EPSILON && Math.abs(other.getY() - this.getY()) < EPSILON));
    }

    /**.
     * @return x value of point
     */
    public double getX() {
        return this.x;
    }

    /**.
     * @return y value of point
     */
    public double getY() {
        return this.y;
    }

    /**.
     * setter x value
     * @param newX get the new x value
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**.
     * setter y value
     * @param newY get new y value
     */
    public void setY(double newY) {
        this.y = newY;
    }
}