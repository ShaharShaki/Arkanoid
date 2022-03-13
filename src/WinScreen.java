import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Shahar Shaki
 * this class is the win screen and implement the animation
 */
public class WinScreen implements Animation {
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private int score;

    /**.
     * constructor
     * @param k the key board
     * @param score the score
     */
    public WinScreen(KeyboardSensor k, int score) {
        this.keyboardSensor = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + this.score, 50);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
