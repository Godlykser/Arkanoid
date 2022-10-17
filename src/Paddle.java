//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import shapes.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * a class for paddle objects.
 */
public class Paddle implements Sprite, Collidable {

    // Finals
    static final int HEIGHT = 20;
    static final int GUI_HEIGHT = 600;
    static final int BORDER_THICKNESS = 15;
    static final int PADDLE_Y_LOCATION = 580 - BORDER_THICKNESS;
    static final int LEFT_BORDER = 1;
    static final int RIGHT_BORDER = 799;
    static final int PADDLE_SPEED = 10;
    static final int NUM_AREAS = 5;
    static final int AREA_1_ANGLE = 300;
    static final int AREA_2_ANGLE = 330;
    static final int AREA_4_ANGLE = 30;
    static final int AREA_5_ANGLE = 60;
    static final double SAFETY = 2;

    // Fields
    private final KeyboardSensor keyboard;
    private shapes.Rectangle rect;
    private final double dx;
    private final Color color;
    private GameLevel gameLevel;
    private final LevelInformation level;

    /**
     * constructor for paddle.
     *
     * @param keyboard - a keyboard sensor that helps the paddle know whether we want to move right or left.
     * @param level    - the current level.
     */
    public Paddle(KeyboardSensor keyboard, LevelInformation level) {
        // assigns sensor.
        this.keyboard = keyboard;
        // assigns level.
        this.level = level;
        // assigns default color.
        this.color = Color.orange;
        // assigns speed from level.
        this.dx = level.paddleSpeed();
        // creates rectangle from level.
        this.rect = new shapes.Rectangle(new Point(400 - (level.paddleWidth() >> 1), PADDLE_Y_LOCATION),
                level.paddleWidth(), 5);
    }

    /**
     * moves the paddle left.
     */
    public void moveLeft() {
        double curX = getCollisionRectangle().getUpperLeft().getX();
        // if the paddle is to the left of the border:
        if (curX > LEFT_BORDER + BORDER_THICKNESS) {
            // moves ball if it's in the way.
            hitBallLeft(PADDLE_SPEED);
            // moves paddle.
            rect = new shapes.Rectangle(new shapes.Point(curX - dx, PADDLE_Y_LOCATION), rect.getWidth(),
                    rect.getHeight());
        }
    }

    /**
     * move the paddle right.
     */
    public void moveRight() {
        // gets currents x value location.
        double curX = getCollisionRectangle().getUpperLeft().getX();
        // if the paddle is to the left of the border:
        if (curX < (RIGHT_BORDER - level.paddleWidth() - BORDER_THICKNESS)) {
            // moves ball if it's in the way.
            hitBallRight(PADDLE_SPEED);
            // moves paddle.
            rect = new shapes.Rectangle(new shapes.Point(curX + dx, PADDLE_Y_LOCATION), level.paddleWidth(), HEIGHT);
        }
    }

    /**
     * draws paddle on surface.
     *
     * @param d given surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // sets color according to given color.
        d.setColor(getColor());
        // gets upper left values.
        int xPt = (int) rect.getUpperLeft().getX();
        int yPt = (int) rect.getUpperLeft().getY();
        // draws rectangle.
        d.fillRectangle(xPt, yPt, level.paddleWidth(), HEIGHT);
    }

    // Sprite

    /**
     * a function that gets called to notify the paddle time has passed.
     */
    public void timePassed() {
        // if left-key is pressed move left.
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
            // else if right-key is pressed move right.
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    // Collidable

    /**
     * returns the paddle's rectangle.
     *
     * @return the paddle's rectangle.
     */
    public shapes.Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * a function that's used to notify the paddle it's been hit.
     *
     * @param collisionPoint  the point of collision with the foreign object.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          ball object.
     * @return new velocity appropriate to hit location.
     */
    @Override
    public Velocity hit(Ball hitter, shapes.Point collisionPoint, Velocity currentVelocity) {
        // calls helper to get which area was hit.
        int area = hitArea(collisionPoint);
        // calculates current ball speed.
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // assigns velocity according to assignment requirements.
        switch (area) {
            // hits first part of the top area.
            case 1:
                return Velocity.fromAngleAndSpeed(AREA_1_ANGLE, speed);
            // hits second part of the top area.
            case 2:
                return Velocity.fromAngleAndSpeed(AREA_2_ANGLE, speed);
            // hits middle part of the top area.
            case 3:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // hits fourth part of the top area.
            case 4:
                return Velocity.fromAngleAndSpeed(AREA_4_ANGLE, speed);
            // hits final part of the top area.
            case 5:
                return Velocity.fromAngleAndSpeed(AREA_5_ANGLE, speed);
            // if side was hit, returns appropriate velocity.
            default:
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g current game.
     */
    public void addToGame(GameLevel g) {
        // sets game.
        this.gameLevel = g;
        // adds to list of sprites and collidables.
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * checks if the paddle will hit the ball on the left with current speed.
     *
     * @param speed the paddle's speed.
     */
    public void hitBallLeft(int speed) {
        // gets list of balls from game.
        Ball[] balls = getGame().getBalls();
        // gets upper left corner.
        shapes.Point upperLeft = getCollisionRectangle().getUpperLeft();
        // checks for each ball.
        for (Ball ball : balls) {
            // gets ball x, y coordinates.
            int x = ball.getX();
            int y = ball.getY();
            // if the ball will be inside the paddle after the move, move ball with paddle.
            if (y >= PADDLE_Y_LOCATION && x >= (upperLeft.getX() - speed) && x <= (upperLeft.getX()
                    + level.paddleWidth())) {
                // on the way, tests to see if ball entered side or bottom borders.
                shapes.Point newCenter = isBallInBorders(new shapes.Point(ball.getX() - (speed * SAFETY), ball.getY()));
                // sets new center.
                ball.setCenter(newCenter);
            }
        }
    }

    /**
     * checks if the paddle will hit the ball on the right with current speed.
     *
     * @param speed the paddle's speed.
     */
    public void hitBallRight(int speed) {
        // gets list of balls from game.
        Ball[] balls = getGame().getBalls();
        // gets upper right corner.
        shapes.Point upperRight = getCollisionRectangle().getUpperRight();
        // checks for each ball.
        for (Ball ball : balls) {
            // gets ball x, y coordinates.
            int x = ball.getX();
            int y = ball.getY();
            // if the ball will be inside the paddle or after the move, move ball with paddle.
            if (y >= PADDLE_Y_LOCATION && x <= (upperRight.getX() + speed) && x >= (upperRight.getX()
                    - level.paddleWidth())) {
                // on the way, tests to see if ball entered side or bottom borders.
                shapes.Point newCenter = isBallInBorders(new shapes.Point(ball.getX() + (speed * SAFETY), ball.getY()));
                // sets new center.
                ball.setCenter(newCenter);
            }
        }
    }

    /**
     * checks if the ball's new location is inside any of the borders.
     *
     * @param pt the ball's new location.
     * @return an appropriate location outside the borders.
     */

    public shapes.Point isBallInBorders(shapes.Point pt) {
        // checks if the ball is inside the lower border, and sets an appropriate variable with its thickness.
        int raise = (pt.getY() >= GUI_HEIGHT - BORDER_THICKNESS) ? 0 : BORDER_THICKNESS;
        // if the ball is inside the left border returns a point outside of it.
        if (pt.getX() <= LEFT_BORDER) {
            return new Point(LEFT_BORDER + 1, PADDLE_Y_LOCATION - 1);
        }
        // if the ball is inside the right border, returns a point outside of it.
        if (pt.getX() >= RIGHT_BORDER) {
            return new shapes.Point(RIGHT_BORDER - 1, PADDLE_Y_LOCATION - 1);
        }
        // else returns the ball default location and raises the ball if necessary.
        return new shapes.Point(pt.getX(), pt.getY() - raise);
    }

    // getters

    /**
     * gets color.
     *
     * @return the paddle's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * gets the current game the paddle is assigned to.
     *
     * @return current game.
     */
    public GameLevel getGame() {
        return gameLevel;
    }

    /**
     * a function that determines which area of the paddle the ball has hit.
     *
     * @param collisionPoint ball's point of collision.
     * @return area.
     */
    public int hitArea(shapes.Point collisionPoint) {
        // gets top left point of paddle.
        shapes.Point topLeft = getCollisionRectangle().getUpperLeft();
        // collision point's distance from top left point.
        int distance = (int) collisionPoint.distance(topLeft);
        // if there's no collision with top returns 0.
        if (!getCollisionRectangle().getTop().isPtOnLine(collisionPoint)) {
            return 0;
        }
        // for each area we divide in top.
        for (int i = 1; i <= NUM_AREAS; i++) {
            // if ball hits within the area, returns area number.
            if (distance <= (level.paddleWidth() * i / NUM_AREAS)) {
                return i;
            }
        }
        // if doesn't hit any area, return -1.
        return -1;
    }
}