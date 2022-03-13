//208524181

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahar Shaki
 * this class is level one implement the level information
 */
public class LevelOne implements LevelInformation {

    /**.
     * constroctur
     */
    public LevelOne() {
    }

    /**.
     * the number of balls
     * @return the num
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**.
     * the start point of the balls
     * @return the start point list
     */
    public List<Point> ballsStartPoint() {
        //define new list of blocks.
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(400, 500));
        return pointList;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>();
        vList.add(Velocity.fromAngleAndSpeed(180, 7));
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    /**.
     * the paddle width
     * @return the width
     */
    @Override
    public int paddleWidth() {
        return 70;
    }

    /**.
     * the name
     * @return a string of the name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**.
     * the screen color
     * @return the screen
     */
    public Sprite getScreenColor() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.black);
    }

    @Override
    public Sprite getBackground() {
        return new LevelOneScreen();
    }

    @Override
    public List<Block> blocks() {
        //a new list of blocks.
        List<Block> blockList = new ArrayList<>();
        //create each block to the game
        Rectangle rec = new Rectangle(new Point(385, 130), 30, 30);
        Block block = new Block(rec, Color.red);
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
