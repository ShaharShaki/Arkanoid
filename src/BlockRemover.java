//208524181

/** a BlockRemover is in charge of removing blocks from the game, as well as keeping count
    of the number of blocks that remain.
 * @author Shahar shaki
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game game
     * @param removedBlocks a counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * method get the remaining blocks.
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     *     from the game. Also remove listener from the block
     *     that is being removed from the game.
     * @param beingHit a block that was hitten
     * @param hitter the object that hits, probably the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the block from the game.
        beingHit.removeFromGame(game);
        //decrease the remaining blocks by 1
        remainingBlocks.decrease(1);
    }
}