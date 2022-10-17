//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * creates a level one singleton object.
 */
public final class LevelOne implements LevelInformation {
    // Finals.
    private static final int NUM_BALLS = 1;
    private static final int BALL_SPEED = -5;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCK_XLOC = 375;
    private static final int BLOCK_YLOC = 175;
    private static final int BLOCK_SIZE = 50;
    private static final String LEVEL_NAME = "Direct Hit";


    // Fields.
    private static LevelOne curLevel = null;
    private final List<Velocity> velocities;
    private final Sprite background;
    private final List<Block> blocks;
    private final int remainingBlocks;

    /**
     * initializes a level One object.
     */
    private LevelOne() {
        this.velocities = new ArrayList<>();
        this.blocks = new ArrayList<>();
        remainingBlocks = 1;
        velocities.add(new Velocity(0, BALL_SPEED));
        background = LevelOneBackground.getInstance();
        blocks.add(new Block(new Rectangle(new Point(BLOCK_XLOC, BLOCK_YLOC), BLOCK_SIZE, BLOCK_SIZE),
                Color.RED));

    }

    /**
     * returns the singleton instance for the level.
     *
     * @return level one object.
     */
    public static LevelOne getInstance() {
        if (curLevel == null) {
            curLevel = new LevelOne();
        }
        return curLevel;
    }

    /**
     * the amount of balls.
     *
     * @return number of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of ball velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    /**
     * speed of paddle for current level.
     *
     * @return speed of paddle.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * width of paddle for current level.
     *
     * @return width of paddle.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return String with the name of the level.
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the sprite with the background.
     */
    @Override
    public Sprite getBackground() {
        return background;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return list of block objects.
     */
    @Override
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return remainingBlocks;
    }
}
