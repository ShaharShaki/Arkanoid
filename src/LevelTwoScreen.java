//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**.
 * @author Shahar Shaki
 * this class implement the sprite interface
 */
public class LevelTwoScreen implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(250, 230, 90));
        int i = 0;
        //line painting
        while (i < 650) {
            d.drawLine(100, 170, i, 250);
            i = i + 8;
        }
        //sun painting
        d.setColor(new Color(240, 233, 216));
        d.fillCircle(130, 150, 80);
        d.setColor(new Color(226, 213, 34));
        d.fillCircle(130, 150, 67);
        d.setColor(new Color(250, 230, 90));
        d.fillCircle(130, 150, 54);
    }

    @Override
    public void timePassed() {

    }
}
