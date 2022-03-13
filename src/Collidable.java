//208524181
import biuoop.DrawSurface;

/**
 * @author Shahar Shaki
 * this interface get the method "hit", "drawOn", "getCollisionRectangle".
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     *      a given velocity.
     *     The return is the new velocity expected after the hit (based on
     *      the force the object inflicted on us)
     * @param collisionPoint Point
     * @param currentVelocity Velocity of the ball
     * @param hitter the ball
     * @return the new velocity after hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);

    /**
     * draw on method.
     * @param d draw surface.
     */
    void drawOn(DrawSurface d);
}