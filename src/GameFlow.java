//208524181

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Shahar Shaki
 * this class is the game flow.
 */
public class GameFlow {
    private Counter score;
    private GUI gui;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private boolean lastLevel;

    /**.
     * constructor
     * @param gui the gui
     * @param animationRunner the animation runner
     * @param keyboardSensor the key board sensor
     */
    public GameFlow(GUI gui, AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.score = new Counter(0);
        this.gui = gui;
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.lastLevel = false;
    }

    /**.
     * this method get a list of levels and run the game.
     * @param levels the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {

        //this loop on all levels
        for (int i = 0; i < levels.size(); i++) {
            if (i == levels.size() - 1) {
                lastLevel = true;
            }

            //send the level to the game level class so it will run on the screen
            GameLevel level = new GameLevel(levels.get(i), this.score, keyboardSensor, animationRunner, gui, lastLevel);

            //init the level
            level.initialize();

            while (level.getNumOfBlocks() != 0 && level.getNumOfBalls() != 0) {
                level.run();
            }

            if (level.getNumOfBalls() == 0) {
                break;
            }
        }
    }
}