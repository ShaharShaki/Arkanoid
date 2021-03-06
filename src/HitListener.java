//208524181

/**.
 * hit listener interface
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that hits
     * @param hitter the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}