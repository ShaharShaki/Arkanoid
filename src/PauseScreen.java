//208524181

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * @author Shahar Shaki
 * this class implement the animation, its the pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**.
     * constructor
     * @param k the key board sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**.
     * this method is do one frame method
     * @param d is the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**.
     * should stop method
     * @return the bool stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}