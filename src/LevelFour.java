//208524181

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * this is the level four class that implement the level information
 */
public class LevelFour implements LevelInformation {
    /**.
     * this method get the number of balls
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**.
     * init the ball velocity.
     * @return the balls velocity inna list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>();
        vList.add(Velocity.fromAngleAndSpeed(0, 7));
        vList.add(Velocity.fromAngleAndSpeed(45, 7));
        vList.add(Velocity.fromAngleAndSpeed(315, 7));
        return vList;
    }

    /**.
     * the paddle speed
     * @return return the speed
     */
    @Override
    public int paddleSpeed() {
        return 9;
    }

    /**.
     * the paddle width
     * @return return the width
     */
    @Override
    public int paddleWidth() {
        return 70;
    }

    /**.
     * the level name
     * @return the name
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**.
     * call the level four screen class to draw the background
     * @return the screen
     */
    @Override
    public Sprite getBackground() {
        return new LevelFourScreen();
    }

    /**.
     * thes creen color
     * @return the screen color
     */
    @Override
    public Sprite getScreenColor() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(51, 153, 255));
    }

    /**.
     * a list of blocks to draw on the screen
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.white);
        colors.add(Color.pink);
        colors.add(Color.CYAN);
        List<Block> blockList = new ArrayList<>();
        int newRow = 0;
        for (int j = 0; j < 7; j++) {
            for (int k = 0; k < 16; k++) {
                Rectangle rectangle = new Rectangle(new Point(25 + (k * 47), 120 + newRow), 47, 20);
                Block block = new Block(rectangle, colors.get(j));
                blockList.add(block);
            }
            newRow += 20;
        }

        return blockList;
    }

    /**.
     * the number of blocks to remove
     * @return the num
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 112;
    }

    /**.
     * a list of the start point of the balls
     * @return the list
     */
    @Override
    public List<Point> ballsStartPoint() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        return pointList;
    }
}
