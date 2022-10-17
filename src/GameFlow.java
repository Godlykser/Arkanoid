//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * a class that manages the game itself, aka levels and stuff.
 */
public class GameFlow {
    // Finals.


    // Fields.
    private final AnimationRunner ar;
    private final KeyboardSensor keyboard;
    private final Counter scoreCounter;
    private final GUI gui;

    /**
     * creates a gameflow object that manages the games' levels.
     *
     * @param ar  Animation runner.
     * @param ks  keyboard sensor.
     * @param gui the graphical interface.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        // assigns animation runner, keyboard and GUI from main.
        this.ar = ar;
        this.keyboard = ks;
        this.gui = gui;
        // creates a counter to keep score across levels.
        scoreCounter = new Counter();
    }

    /**
     * runs the game's levels.
     *
     * @param levels list of level information objects.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean flag = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ar, keyboard, gui, scoreCounter);
            level.initialize();
            while (level.getRemainingBalls().getValue() != 0 && level.getRemainingBlocks().getValue() != 0) {
                level.run();
            }
            if (level.getRemainingBalls().getValue() == 0) {
                flag = false;
                break;
            }
        }
        ar.run(new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, new EndScreen(scoreCounter, flag)));
        gui.close();
    }
}

