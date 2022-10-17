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
 * creates a level three singleton object.
 */
public final class LevelThree implements LevelInformation {
    // Finals.
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int NUM_BALLS = 2;
    static final int BALL_SPEED = -4;
    static final int PADDLE_SPEED = 8;
    static final int PADDLE_WIDTH = 100;
    static final int BORDER_WIDTH = 20;
    static final int BLOCK_Y_LOC = 150;
    static final int BLOCK_WIDTH = 60;
    static final int BLOCK_X_LOC = 800 - BORDER_WIDTH - 10 * BLOCK_WIDTH;
    static final int BLOCK_HEIGHT = 30;
    static final int ANGLE = 60;
    static final int MAX_BLOCKS = 10;
    static final String LEVEL_NAME = "Green 3";
    static final Color[] COLORS = {Color.darkGray, Color.red, Color.yellow, Color.blue, Color.white};
    static final int MAX_ROWS = 5;

    // Fields.
    private static LevelThree curLevel = null;
    private final List<Velocity> velocities;
    private final Sprite background;
    private final List<Block> blocks;
    private final int remainingBlocks;

    /**
     * initializes singleton instance of level three.
     */
    private LevelThree() {
        background = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT), Color.GREEN);
        // initialize arrays.
        velocities = new ArrayList<>();
        blocks = new ArrayList<>();
        // sets num of remaining blocks.
        remainingBlocks = 40;
        // adds balls' velocities to list.
        for (int i = 1; i <= numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(90 + ANGLE * i, BALL_SPEED));
        }
        // background = ;
        // adds blocks to list.
        // initializes blocks in order.
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_BLOCKS - i; j++) {
                blocks.add(new Block(new shapes.Rectangle(new Point(BLOCK_X_LOC + ((j + i) * (BLOCK_WIDTH + 1)),
                        BLOCK_Y_LOC + BLOCK_HEIGHT * i + 1), BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[i]));
            }
        }
    }

    /**
     * return singleton instance.
     *
     * @return singleton level three.
     */
    public static LevelThree getInstance() {
        if (curLevel == null) {
            curLevel = new LevelThree();
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
