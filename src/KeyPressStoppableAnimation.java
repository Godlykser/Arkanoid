//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * a keyboard sensor decorator.
 */
public class KeyPressStoppableAnimation implements Animation {

    // Fields.
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * KeyPressStoppableAnimation constructor.
     *
     * @param sensor    keyboard sensor.
     * @param key       String key.
     * @param animation Animation to activate.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        isAlreadyPressed = true;
    }

    /**
     * does a single animation.
     *
     * @param d draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboard.isPressed(key)) {
            if (isAlreadyPressed) {
                isAlreadyPressed = false;
            } else {
                this.stop = true;
            }
        }
    }

    /**
     * returns whether or not the animation should stop.
     *
     * @return stop.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
