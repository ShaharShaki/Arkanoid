//208524181

/**
 * @author Shahar shaki
 * ScoreTrackingListener implement the HitListener, this method tracking the user score and increase the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**.
     * Constructor
     * @param scoreCounter score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**.
     * getter methode
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * methode increase by 5 the score if ball hit the block.
     * @param beingHit the block that hitted
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}