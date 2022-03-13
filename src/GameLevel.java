//208524181

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahar Shaki
 * next class is the init and run the game to the user's screen.
 */
public class GameLevel implements Animation {

    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levels;
    private BlockRemover blockRemover;
    private KeyboardSensor ks;
    private boolean lastLevel;

    /**.
     * constructor
     * @param level the level information
     * @param score the score
     * @param ks the key board
     * @param ar the animation runner
     * @param gui the gui
     * @param lastLevel bool about last level
     */
    public GameLevel(LevelInformation level, Counter score, KeyboardSensor ks, AnimationRunner ar, GUI gui,
                     boolean lastLevel) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levels = level;
        this.blockCounter = new Counter(this.levels.numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballCounter = new Counter(levels.numberOfBalls());
        this.score = score;
        this.runner = ar;
        this.running = true;
        this.gui = gui;
        this.ks = ks;
        this.lastLevel = lastLevel;
    }

    /**
     * method add the collidable object to a list of objects.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.getCollidableList().add(c);
    }

    /**.
     * this method remove the collidable
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
        }

    /**
     * next method add the given sprite to a list.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.getSpriteArrayList().add(s);
    }

    /**.
     * this method remove the sprite
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteArrayList().remove(s);
    }

    /**.
     * method get the num of blocks
     * @return the num of blocks
     */
    public int getNumOfBlocks() {
        return this.blockCounter.getValue();
    }

    /**.
     * method get the num of balls
     * @return the num of balls
     */
    public int getNumOfBalls() {
        return this.ballCounter.getValue();
    }

    /**
     * next method init a new game. creating all objects: ball, block, paddle and add them to the game.
     */
    public void initialize() {
        //give the window a color and add it to the game.
        sprites.addSprite(this.levels.getScreenColor());
        sprites.addSprite(this.levels.getBackground());

        //score tracking and indicator
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        //add the blocks to the game
        List<Block> blocks = this.levels.blocks();
        this.blockRemover.getRemainingBlocks().setCounter(this.levels.numberOfBlocksToRemove());
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(blockRemover);
            blocks.get(i).addHitListener(scoreTrackingListener);
        }

        //create ball remover
        BallRemover ballRemover = new BallRemover(this, ballCounter);

        /*
        creating limits using blocks to the board.
         */
            //up side.
            Rectangle upSide = new Rectangle(new Point(0, 0), 800, 30);
            Block up = new Block(upSide, Color.GRAY);
            up.addToGame(this);
            //down side. the "death-region"
            Rectangle downSide = new Rectangle(new Point(0, 600), 800, 10);
            Block down = new Block(downSide, Color.GRAY);
            down.addToGame(this);
            //add the ball remover as a listener
            down.addHitListener(ballRemover);
            //left side.
            Rectangle leftSide = new Rectangle(new Point(0, 30), 30, 570);
            Block left = new Block(leftSide, Color.GRAY);
            left.addToGame(this);
            //right side.
            Rectangle rightSide = new Rectangle(new Point(770, 30), 30, 570);
            Block right = new Block(rightSide, Color.GRAY);
            right.addToGame(this);
            Collidable upside = new Block(upSide, Color.GRAY);
            Collidable downside = new Block(downSide, Color.GRAY);
            Collidable leftside = new Block(leftSide, Color.GRAY);
            Collidable rightside = new Block(rightSide, Color.GRAY);
            //adding to the game.
            this.addCollidable(upside);
            this.addCollidable(downside);
            this.addCollidable(leftside);
            this.addCollidable(rightside);

        //create the paddle and add it to the game.
        Paddle paddle = new Paddle(new Rectangle(new Point(400 - (levels.paddleWidth() / 2), 555),
                this.levels.paddleWidth(), 15), this.ks, this.levels.paddleSpeed());
        paddle.addToGame(this);

        /*
        //create 3 balls, set there velocity and add it to the game.
         */
            ArrayList<Ball> ballsArray = new ArrayList<>();
            for (int i = 0; i < levels.numberOfBalls(); i++) {
                Ball ball = new Ball(levels.ballsStartPoint().get(i), 5, Color.WHITE, environment);
                ball.setBallLimit(new Point(5, 5), new Point(495, 495));
                ball.setVelocity(this.levels.initialBallVelocities().get(i));
                ballsArray.add(ball);
                ball.addToGame(this);
            }
            paddle.setArrayBalls(ballsArray);
        //create the score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        //add it to the game
        scoreIndicator.addToGame(this);
        }

    /**
     * this method run the game and start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        d.drawText(600, 20, "Level Name: " + this.levels.levelName(), 16);
        if (this.ks.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.ks, "space", new PauseScreen(this.ks)));
        }
        if (this.lastLevel) {
            if (blockCounter.getValue() <= 0) {
                this.score.increase(100);
                this.running = false;
                this.runner.run(new KeyPressStoppableAnimation(this.ks, "space",
                        new WinScreen(this.ks, this.score.getValue())));
                gui.close();
            }
        }
        if (blockCounter.getValue() <= 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (ballCounter.getValue() <= 0) {
            this.running = false;
            this.runner.run(new KeyPressStoppableAnimation(this.ks, "space",
                    new GameOverScreen(this.ks, this.score.getValue())));
            gui.close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}