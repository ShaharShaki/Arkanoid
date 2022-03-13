//208524181

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * @author Shahar Shaki
 * this class implement animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**.
     * constructor
     * @param sensor the key board
     * @param key the key that the user press
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**.
     * this method do one frame on the animation
     * @param d is the drawsurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //send the d to the method
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        }
        this.isAlreadyPressed = false;
    }

    /**
     * should stop method.
     * @return the bool stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}