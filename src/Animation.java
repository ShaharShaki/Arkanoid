//208524181

import biuoop.DrawSurface;

/**.
 * @author Shahar Shaki
 * this is animation iterface
 */
public interface Animation {
    /**.
     * thid metode do one frame
     * @param d is the drawsurface
     */
    void doOneFrame(DrawSurface d);
    /**.
     * this method is boolean should stop
     * @return false or true.
     */
    boolean shouldStop();
}