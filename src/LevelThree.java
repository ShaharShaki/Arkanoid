//208524181

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * this class implement the level information
 */
public class LevelThree implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>();
        vList.add(Velocity.fromAngleAndSpeed(315, 7));
        vList.add(Velocity.fromAngleAndSpeed(45, 7));
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new LevelThreeScreen();
    }

    @Override
    public Sprite getScreenColor() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(62, 142, 15));
    }

    @Override
    public List<Block> blocks() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.RED);
        colors.add(Color.yellow);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        List<Block> blockList = new ArrayList<>();
        int newRow = 0;
        for (int j = 0; j < 5; j++) {
            for (int k = j; k < 10; k++) {
                Rectangle rectangle = new Rectangle(new Point(270 + (k * 50), 120 + newRow), 50, 20);
                Block block = new Block(rectangle, colors.get(j));
                blockList.add(block);
            }
            newRow += 20;
        }
        return  blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public List<Point> ballsStartPoint() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(400, 510));
        pointList.add(new Point(400, 510));
        return pointList;
    }
}
