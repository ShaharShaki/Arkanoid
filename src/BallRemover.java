//208524181

/**
 * method ball remover implement HitListener and remove balls from the game.
 * @author Shahar shaki
 */
public class BallRemover implements HitListener {
    //fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game game
     * @param removedBalls a counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * methode remove balls from the game and decrease the remaining balls by 1.
     * @param beingHit the block
     * @param hitter the ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the ball from the game
        hitter.removeFromGame(game);
        //decrease the remaining balls by 1
        this.remainingBalls.decrease(1);
    }
}
