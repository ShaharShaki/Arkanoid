//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shahar Shaki
 * this class implement the sprite interface so it will draw the screen
 */
public class LevelFourScreen implements Sprite {
    /**.
     * draw the sprite to the screen
     * @param d draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        //draw the lines
        d.setColor(new Color(214, 215, 219));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0) {
                    d.drawLine(95 + (10 * j), 415, 80 + (10 * j), 600);
                }
                d.drawLine(600 + (10 * j), 500, 580 + (10 * j), 600);
            }
        }
        //clouds
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                //first cloud
                if (i == 0) {
                    if (j % 2 == 0) {
                        d.setColor(new Color(210, 210, 210));
                        d.fillCircle(100 + (18 * j), 400, 25 + j);
                    } else {
                        d.setColor(new Color(185, 185, 185));
                        d.fillCircle(100 + (18 * j), 370, 25 + j);
                    }
                }
                if (j % 2 == 0) {
                    d.setColor(new Color(200, 200, 200));
                    d.fillCircle(600 + (18 * j), 500, 25 + j);
                } else {
                    d.setColor(new Color(180, 180, 180));
                    d.fillCircle(600 + (18 * j), 470, 25 + j);
                }
            }
        }
    }

    /**
     * .
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }
}
