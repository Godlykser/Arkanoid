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
 * creates a level four instance.
 */
public final class LevelFour implements LevelInformation {
    // Finals.
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int NUM_BALLS = 3;
    static final int BALL_SPEED = -4;
    static final int PADDLE_SPEED = 12;
    static final int PADDLE_WIDTH = 100;
    static final int BLOCK_X_LOC = 25;
    static final int BLOCK_Y_LOC = 75;
    static final int BLOCK_WIDTH = 49;
    static final int BLOCK_HEIGHT = 20;
    static final int ANGLE = 30;
    static final int BLOCKS_PER_ROW = 15;
    static final String LEVEL_NAME = "Final Four";
    static final Color[] COLORS = {Color.red, Color.orange, Color.yellow, Color.green, Color.white, Color.pink,
            Color.cyan};
    static final int NUM_ROWS = 6;

    // Fields.
    private static LevelFour curLevel = null;
    private final List<Velocity> velocities;
    private final Sprite background;
    private final List<Block> blocks;
    private final int remainingBlocks;

    /**
     * initializes a singleton instance.
     */
    private LevelFour() {
        background = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT), Color.blue);
        // initializes arrays.
        velocities = new ArrayList<>();
        blocks = new ArrayList<>();
        // adds 7 rows of 15 blocks each.
        remainingBlocks = 7 * 15;
        // initializes velocities.
        for (int i = 1; i <= numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(120 + ANGLE * i, BALL_SPEED));
        }
        // background = ;
        // adds blocks to list.
        for (int j = 0; j < NUM_ROWS; j++) {
            for (int i = 1; i <= BLOCKS_PER_ROW; i++) {
                if (i <= 7) {
                    blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                            BLOCK_Y_LOC + (BLOCK_HEIGHT * j)), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[j]));
                } else if (i == 8) {
                    blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                            BLOCK_Y_LOC + (BLOCK_HEIGHT * j)), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[j]));
                } else {
                    blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                            BLOCK_Y_LOC + (BLOCK_HEIGHT * j)), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[j]));
                }
            }
        }
    }

    /**
     * return singleton instance.
     *
     * @return singleton LevelFour.
     */
    public static LevelFour getInstance() {
        if (curLevel == null) {
            curLevel = new LevelFour();
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
     * The Blocks that make up this level, each block contains
     * its size, color and location.
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
