//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shahar Shaki
 * Ball class create an object "Ball", set it center point, velocity, color, draw on a screen and also
 * check it's limit for moving on a given screen.
 */
public class Ball implements Sprite {
    private static final double EPSILON = Math.pow(10, -2);

    //Fields
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private Point startPoint;
    private Point endPoint;
    private GameEnvironment gameEnvironment;

    /**
     * .
     * constructor
     *
     * @param center point
     * @param r      int
     * @param color  Color
     * @param gameEnvironment Game Environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.startPoint = new Point(0, 0);
        this.endPoint = new Point(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * .
     * constructor
     *
     * @param x      double
     * @param y      double
     * @param r1     int radius
     * @param color1 Color
     * @param gameEnvironment Game Enviroment
     */
    public Ball(double x, double y, int r1, java.awt.Color color1, GameEnvironment gameEnvironment) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r1;
        this.color = color1;
        this.gameEnvironment = gameEnvironment;

    }

    /**
     * method set the ball's limits with start and end points.
     *
     * @param start Point.
     * @param end   Point
     */
    public void setBallLimit(Point start, Point end) {
        this.startPoint = new Point(start.getX(), start.getY());
        this.endPoint = new Point(end.getX(), end.getY());
    }

    /**
     * @return the x value of the center.
     */
    public double getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y value of the center.
     */
    public double getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size (radius) of a given Ball.
     */
    public double getSize() {
        return this.r;
    }

    /**
     * @return the color of a given Ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * .
     * draw the ball on the given DrawSurface
     *
     * @param surface get the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), (int) getSize());
    }

    /**
     * method call mvoe one step for the object.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * method give the Ball it's Velocity.
     *
     * @param v Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * method set the velocity.
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * setter method for the center.
     * @param newPoint the new point
     */
    public void setCenter(Point newPoint) {
        this.center = newPoint;
    }

    /**
     * method add the ball to the game.
     * @param g game board
     */
    public void addToGame(GameLevel g) {
        g.addSprite((Sprite) this);
    }

    /**
     * .
     * get the velocity of a given ball
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * method check if there is a necessity to change ball direction. the method keep the ball moving.
     */

    public void moveOneStep() {
        //creating new line with 2 points. the center and the center after changing dx and dy.
        Point startP = new Point(this.center.getX(), this.center.getY());
        Point endP = this.getVelocity().applyToPoint(startP);
        Line trajectory = new Line(startP, endP);
        //get the info if there was a collision with the ball trajectory.
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        //if not, keep the ball moving.
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        /*
        there was a collision! change the velocity depend with it hit.
         */
        if (info.collisionObject().getCollisionRectangle().isIntersectingWithRectangle(info.collisionPoint())) {

            if (info.collisionObject().getCollisionRectangle().onDown(info.collisionPoint())) {
                this.center.setY(info.collisionPoint().getY() + this.r + EPSILON);
            } else if (info.collisionObject().getCollisionRectangle().onUp(info.collisionPoint())) {
                this.center.setY(info.collisionPoint().getY() - this.r - EPSILON);
            } else if (info.collisionObject().getCollisionRectangle().onLeft(info.collisionPoint())) {
                this.center.setX(info.collisionPoint().getX() - this.r - EPSILON);
            } else if (info.collisionObject().getCollisionRectangle().onRight(info.collisionPoint())) {
                 this.center.setX(info.collisionPoint().getX() + this.r + EPSILON);
            }
            //set the velocity of the ball rather where it hits.
            Velocity v = (info.collisionObject().hit(info.collisionPoint(), this.getVelocity(), this));
            this.setVelocity(v);
        }
    }

    /**
     * remove from game method remove the ball from the game.
     * @param game the game level
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

