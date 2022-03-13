//208524181

/**
 * @author Shahar Shaki
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    //Fields
    private double dx;
    private double dy;

    /**.
     * constructor
     * @param dx double
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**.
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     * @param p Point
     * @return new Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**.
     * method set a velocity of a ball with given angle and speed.
     * @param angle double
     * @param speed double
     * @return new Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = speed * -1 * Math.cos(angle);
        return new Velocity(dx, dy);
    }

    /**.
     * accessor
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**.
     * set a new dx to velocity
     * @param newDx the new dx after multiply by (-1)
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**.
     * accessor
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**.
     * set a new dy to velocity.
     * @param newDy the new dy after multiply by (-1)
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}