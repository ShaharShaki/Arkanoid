//208524181

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * class of level two implement the level information.
 */
public class LevelTwo implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>();
        vList.add(Velocity.fromAngleAndSpeed(20, 7));
        vList.add(Velocity.fromAngleAndSpeed(30, 7));
        vList.add(Velocity.fromAngleAndSpeed(40, 7));
        vList.add(Velocity.fromAngleAndSpeed(50, 7));
        vList.add(Velocity.fromAngleAndSpeed(60, 7));
        vList.add(Velocity.fromAngleAndSpeed(300, 7));
        vList.add(Velocity.fromAngleAndSpeed(310, 7));
        vList.add(Velocity.fromAngleAndSpeed(320, 7));
        vList.add(Velocity.fromAngleAndSpeed(330, 7));
        vList.add(Velocity.fromAngleAndSpeed(340, 7));
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new LevelTwoScreen();
    }

    @Override
    public Sprite getScreenColor() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Rectangle rec = new Rectangle(new Point(30 + i * 74, 250), 74, 27);
            Block block = new Block(rec, colors.get(i / 2));
            blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }

    @Override
    public List<Point> ballsStartPoint() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        return pointList;
    }
}
