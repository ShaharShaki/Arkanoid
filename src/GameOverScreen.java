//208524181

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * this class implement animation and create the game over screen
 */
public class GameOverScreen implements Animation {

    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private int score;

    /**.
     * constructor
     * @param k the key board
     * @param score the score the user got
     */
    public GameOverScreen(KeyboardSensor k, int score) {
        this.keyboardSensor = k;
        this.stop = false;
        this.score = score;
    }

    /**.
     * method draw the text to the user that the game is over
     * @param d is the drawsurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + this.score, 50);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**.
     * this method is should stop.
     * @return the bool stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
