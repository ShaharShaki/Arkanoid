//208524181

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * this class will support the countdown fetcher. also implement tha animation.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private Sleeper sleeper;
    private boolean stop;
    private double framesPerSecond;
    private boolean isFirstCount;

    /**.
     * constructor
     * @param numOfSeconds the number of second
     * @param countFrom the count from
     * @param gameScreen the game screen from sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.sleeper = new Sleeper();
        this.stop = false;
        this.framesPerSecond = numOfSeconds / countFrom;
        this.isFirstCount = true;
    }

    /**
     * this method is the do one frame.
     * @param d is the drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        if (isFirstCount) {
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.ORANGE);
            d.drawText(370, 300, this.countFrom + " ", 100);
            isFirstCount = false;
        } else {
            double frameTimer = 1000 * framesPerSecond;
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.ORANGE);
            d.drawText(370, 300, this.countFrom + " ", 100);
            if (this.countFrom == 0) {
                this.stop = true;
            }
            //timing
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long leftToSleep = (long) frameTimer - usedTime;
            if (leftToSleep > 0) {
                this.sleeper.sleepFor(leftToSleep);
            }
        }
        countFrom--;
    }

    /**.
     * should stop method
     * @return the bool stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}