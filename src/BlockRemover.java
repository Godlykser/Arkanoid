//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    // Fields.

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    // Removers.

    /**
     * creates a BlockRemover object.
     *
     * @param gameLevel       current game.
     * @param remainingBlocks counter for remaining blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     *
     * @param beingHit hit block.
     * @param hitter   the ball that hit the block.
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        // removes block from game.
        gameLevel.removeCollidable(beingHit);
        gameLevel.removeSprite(beingHit);
        // removes listener from block.
        beingHit.removeListener(this);
        remainingBlocks.decrease(1);
    }
}
