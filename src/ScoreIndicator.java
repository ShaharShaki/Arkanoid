//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shahar shaki
 * this methode implement sprite
 */
public class ScoreIndicator implements Sprite {
    //field
    private Counter scoreCounter;

    /**.
     * Constructor
     * @param theScore counter
     */
    public ScoreIndicator(Counter theScore) {
        this.scoreCounter = theScore;
    }

    /**.
     * method draw on the surface the "score"
     * @param d draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 20, "Score:" + scoreCounter.getValue(), 16);
    }

    /**
     * not need to use this method because the score always written.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adding the score to the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
