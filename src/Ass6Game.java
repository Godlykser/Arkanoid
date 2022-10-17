//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * main game function.
 */
public class Ass6Game {
    /**
     * starts the game.
     *
     * @param args - user input, currently useless.
     */
    public static void main(String[] args) {
        // initializes level list.
        List<LevelInformation> levels = initLevels(args);
        // creates GUI, animation runner and keyboard sensor.
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        // initializes GameFlow.
        GameFlow game = new GameFlow(ar, keyboardSensor, gui);
        // runs levels.
        game.runLevels(levels);
    }

    /**
     * initializes levels.
     *
     * @param args arguments given at command line.
     * @return list of levels.
     */
    public static List<LevelInformation> initLevels(String[] args) {
        // initializes list.
        List<LevelInformation> levels = new ArrayList<>();
        // assigns levels by arguments.
        for (String string : args) {
            switch (string) {
                case "1":
                    levels.add(LevelOne.getInstance());
                case "2":
                    levels.add(LevelTwo.getInstance());
                case "3":
                    levels.add(LevelThree.getInstance());
                case "4":
                    levels.add(LevelFour.getInstance());
                default:
            }
        }
        // if no levels were assigned, assigns default.
        if (levels.isEmpty()) {
            levels.add(LevelOne.getInstance());
            levels.add(LevelTwo.getInstance());
            levels.add(LevelThree.getInstance());
            levels.add(LevelFour.getInstance());
        }
        return levels;
    }
}