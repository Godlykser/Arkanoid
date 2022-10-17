//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import java.util.List;

/**
 * level information interface.
 */
public interface LevelInformation {

    /**
     * the amount of balls.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * speed of paddle for current level.
     *
     * @return speed of paddle.
     */
    int paddleSpeed();

    /**
     * width of paddle for current level.
     *
     * @return width of paddle.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String with the name of the level.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the sprite with the background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return list of block objects.
     */

    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks to remove.
     */

    int numberOfBlocksToRemove();
}