//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import animation.Animation;
import animation.AnimationRunner;
import animation.PauseScreen;
import shapes.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a class that creates a game object.
 */
public class GameLevel implements Animation {

    // Finals
    public static final Color BALL_COLOR = Color.GRAY;
    static final double TOP_LEFT = 0;
    static final double WIDTH = 800;
    static final double HEIGHT = 600;
    static final double BLOCK_SIZE = 25;
    static final int RADIUS = 7;
    static final int LEVEL_CLEARED = 100;

    // Fields
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Ball[] balls;
    private final GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter currentScore;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboardSensor;
    private final LevelInformation level;


    // Constructors.

    /**
     * constructor for Game objects.
     *
     * @param level        current level.
     * @param ar           animation runner object.
     * @param keyboard     keyboard sensor.
     * @param gui          Graphics interface.
     * @param currentScore counter for score.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor keyboard,
                     GUI gui, Counter currentScore) {
        // initializes sprite list.
        List<Sprite> spriteList = new ArrayList<>();
        // sets parameters to fields.
        this.level = level;
        this.gui = gui;
        this.currentScore = currentScore;
        runner = ar;
        keyboardSensor = keyboard;
        // creates environment, sprite collection and counters.
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection(spriteList);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        // creates a list of ball objects.
        balls = new Ball[level.numberOfBalls()];
        // counts remaining balls.
        remainingBlocks.increase(level.numberOfBlocksToRemove());
    }


    // Adders.

    /**
     * adds collidable to environment.
     *
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a score indicator to the game.
     */
    public void addScoreIndicator() {
        ScoreIndicator score = new ScoreIndicator(currentScore);
        addSprite(score);
    }

    /**
     * adds a name indicator.
     */
    private void addNameIndicator() {
        NameIndicator name = new NameIndicator(level.levelName());
        addSprite(name);
    }

    /**
     * adds sprite to collection.
     *
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * adds pedal to the game.
     *
     * @param keyboard a keyboard sensor.
     */
    private void addPaddle(KeyboardSensor keyboard) {
        // creates a new paddle object.
        Paddle paddle = new Paddle(keyboard, level);
        // adds it to game.
        paddle.addToGame(this);
    }

    /**
     * adds blocks to the game in default pattern.
     */
    public void addBlocks() {
        // creates listeners.
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(currentScore);
        // for each row.
        for (Block block : level.blocks()) {
            block.addListener(blockRemover);
            block.addListener(scoreTracker);
            block.addToGame(this);
        }
    }

    /**
     * adds appropriate borders to the field.
     */
    public void addBorders() {
        // creates list of blocks.
        List<Block> borders = new ArrayList<>();
        // adds upper border and side borders with default sizes to the list.
        borders.add(new Block(new shapes.Rectangle(new Point(TOP_LEFT, TOP_LEFT), WIDTH, BLOCK_SIZE), Color.GRAY));
        borders.add(new Block(new shapes.Rectangle(new shapes.Point(TOP_LEFT, TOP_LEFT), BLOCK_SIZE, HEIGHT),
                Color.GRAY));
        borders.add(new Block(new shapes.Rectangle(new shapes.Point(WIDTH - BLOCK_SIZE, TOP_LEFT), BLOCK_SIZE,
                HEIGHT), Color.GRAY));
        // adds special death-zone block.
        Block b = new Block(new shapes.Rectangle(new shapes.Point(TOP_LEFT, HEIGHT - 1), WIDTH,
                BLOCK_SIZE), Color.GRAY);
        remainingBalls.increase(level.numberOfBalls());
        b.addListener(new BallRemover(this, remainingBalls));
        borders.add(b);
        // adds each block in the list to the game.
        for (Block border : borders) {
            border.addToGame(this);
        }
    }

    /**
     * creates balls and adds them to the game.
     */
    public void addBalls() {
        for (int i = 1; i <= level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point((400), 500), RADIUS, BALL_COLOR);
            ball.setVelocity(level.initialBallVelocities().get(i - 1));
            ball.setEnvironment(getGameEnvironment());
            ball.addToGame(this);
            balls[i - 1] = ball;
        }
    }


    // Getters

    /**
     * gets game environment.
     *
     * @return game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return environment;
    }

    /**
     * gets sprite collection.
     *
     * @return collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * gets array of balls in game.
     *
     * @return array of balls.
     */
    public Ball[] getBalls() {
        return this.balls;
    }

    /**
     * gets counter of remaining blocks.
     *
     * @return counter of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * gets counter of remaining balls.
     *
     * @return counter of remaining balls.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * gets the gui object.
     *
     * @return GUI object.
     */
    public GUI getGui() {
        return gui;
    }

    // game functions.

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // adds objects to the game.
        level.getBackground().addToGame(this);
        addPaddle(this.keyboardSensor);
        addBalls();
        addBlocks();
        addBorders();
        addScoreIndicator();
        addNameIndicator();
        runner.run(new CountdownAnimation(2000.0 / 3, 3, getSprites()));
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
//        this.createBallsOnTopOfPaddle(NUM_BALLS); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // initialize sleeper.
        Sleeper sleeper = new Sleeper();
        // set FPS and ms per frame.
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // while game is running
        // starts logging time
        long startTime = System.currentTimeMillis(); // timing
        // draw sprites on surface
        this.sprites.drawAllOn(d);
        // notify sprites to start doing stuff, like moving etc.
        this.sprites.notifyAllTimePassed();
        // if no blocks or balls are left
        if (getRemainingBlocks().getValue() == 0 || getRemainingBalls().getValue() == 0) {
            // if level cleared, increases score by 100.
            if (getRemainingBlocks().getValue() == 0) {
                currentScore.increase(LEVEL_CLEARED);
            }
            // closes game.
            this.running = false;
            return;
        }
        // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (keyboardSensor.isPressed("p") || keyboardSensor.isPressed("P")) {
            runner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    // Removers

    /**
     * removes a collidables object from collidable list.
     *
     * @param c collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removes sprite object from SpriteCollection.
     *
     * @param s sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}





























