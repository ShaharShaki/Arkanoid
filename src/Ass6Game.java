//208524181

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahar Shaki
 * class have a main that init and run the game.
 */
public class Ass6Game {

    /**.
     * main method that run and init the game
     * @param args none
     */
    public static void main(String[] args) {
        //creating a list of levels.
        List<LevelInformation> levels = new ArrayList<>();
        //put inside the list the wanted levels by the user.
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new LevelOne());
                }
                if (args[i].equals("2")) {
                    levels.add(new LevelTwo());
                }
                if (args[i].equals("3")) {
                    levels.add(new LevelThree());
                }
                if (args[i].equals("4")) {
                    levels.add(new LevelFour());
                }
                //else, if the user don't give arguments, put all the levels into the game
            }
            if (levels.isEmpty()) {
                levels.add(new LevelOne());
                levels.add(new LevelTwo());
                levels.add(new LevelThree());
                levels.add(new LevelFour());
             }
        //creating the gui
        GUI gui = new GUI("Arkanoid", 800, 600);
        //creating the animation runner
        AnimationRunner animationRunner = new AnimationRunner(gui);
        //create the key board sensor.
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        //create a game flow
        GameFlow gameFlow = new GameFlow(gui, animationRunner, keyboardSensor);
        //give the game flow the levels to run
        gameFlow.runLevels(levels);
    }
}
