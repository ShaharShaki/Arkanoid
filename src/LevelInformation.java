//208524181
import java.util.List;

/**
 * @author Shahar Shaki
 * this interface have method to draw levels
 */
public interface LevelInformation {
    /**.
     * this methos return the number of balls
     * @return the num of balls
     */
    int numberOfBalls();

    /**.
     * The initial velocity of each ball
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**.
     * paddle speed
     * @return the speed
     */
    int paddleSpeed();

    /**.
     * paddle width
     * @return the width
     */
    int paddleWidth();

    /**.
     * the level name
     * @return the name
     */
    String levelName();

    /**.
     * the background
     * @return the background
     */
    Sprite getBackground();

    /**.
     * screen color
     * @return the screen color
     */
    Sprite getScreenColor();

    /**.
     * a list of blocks to draw
     * @return the list
     */
    List<Block> blocks();

    /**.
     * the num of blocks to remove
     * @return the num
     */
    int numberOfBlocksToRemove();

    /**
     * a list of point to start.
     * @return the list
     */
    List<Point> ballsStartPoint();
}