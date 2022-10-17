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
 * creates a level two singleton object.
 */
public final class LevelTwo implements LevelInformation {
    // Finals.
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int NUM_BALLS = 8;
    static final int BALL_SPEED = -5;
    static final int PADDLE_SPEED = 5;
    static final int PADDLE_WIDTH = 600;
    static final int BLOCK_X_LOC = 25;
    static final int BLOCK_Y_LOC = 150;
    static final int BLOCK_WIDTH = 49;
    static final int BLOCK_HEIGHT = 25;
    static final int ANGLE = 15;
    static final String LEVEL_NAME = "Wide Easy";
    static final Color[] COLORS = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink,
            Color.cyan};

    // Fields.
    private static LevelTwo curLevel = null;
    private final List<Velocity> velocities;
    private final Sprite background;
    private final List<Block> blocks;
    private final int remainingBlocks;

    /**
     *
     */
    private LevelTwo() {
        background = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT), Color.WHITE);
        velocities = new ArrayList<>();
        blocks = new ArrayList<>();
        remainingBlocks = 15;
        for (int i = 1; i <= numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(105 + ANGLE * i, BALL_SPEED));
        }
        // background = ;
        for (int i = 1; i <= remainingBlocks; i++) {
            if (i <= 7) {
                blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                        BLOCK_Y_LOC), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[i - 1]));
            } else if (i == 8) {
                blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                        BLOCK_Y_LOC), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[i - 2]));
            } else {
                blocks.add(new Block(new Rectangle(new Point(BLOCK_X_LOC + ((BLOCK_WIDTH + 1) * (i - 1)),
                        BLOCK_Y_LOC), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[remainingBlocks - i]));
            }
        }
    }

    /**
     * returns the singleton instance for the level.
     *
     * @return level two object.
     */
    public static LevelTwo getInstance() {
        if (curLevel == null) {
            curLevel = new LevelTwo();
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
