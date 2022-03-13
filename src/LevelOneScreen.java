//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**.
 * this class implement the sprite class
 */
public class LevelOneScreen implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawCircle(400, 150, 120);
        d.drawCircle(400, 150, 90);
        d.drawCircle(400, 150, 60);
        d.drawLine(260, 140, 380, 140);
        d.drawLine(420, 140, 540, 140);
        d.drawLine(400, 30, 400, 120);
        d.drawLine(400, 160, 400, 280);
    }

    @Override
    public void timePassed() {

    }
}
