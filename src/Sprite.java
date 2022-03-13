//208524181

import biuoop.DrawSurface;

/**.
 * @author Shahar Shaki
 * spirit interface get 2 methods. drawn on and time passed.
 */
public interface Sprite {

    /**.
     * draw the sprite to the screen
     * @param d draw surface
     */
    void drawOn(DrawSurface d);

    /**.
     * notify the sprite that time has passed
     */
    void timePassed();
}