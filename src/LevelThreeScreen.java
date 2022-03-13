//208524181

import biuoop.DrawSurface;
import java.awt.Color;

/**.
 * this class implement the sprite
 */
public class LevelThreeScreen implements Sprite {
    /**.
     * draw the sprite to the screen
     * @param d draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(90, 90, 90));
        d.fillRectangle(95, 215, 10, 180);
        d.setColor(new Color(65, 65, 65));
        d.fillRectangle(85, 375, 32, 50);
        d.setColor(new Color(50, 50, 50));
        d.fillRectangle(50, 425, 103, 173);
        d.setColor(Color.white);
        int newRow = 0;
        int newCol = 0;
        //create the white rectangles inside the building
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                d.fillRectangle(60 + (newCol * 18), 438 + newRow, 10, 25);
                newCol++;
                if (newCol == 5) {
                    newCol = 0;
                }
            }
            newRow += 30;
        }
        d.setColor(new Color(255, 244, 40));
        d.fillCircle(100, 200, 16);
        d.setColor(new Color(245, 120, 115));
        d.fillCircle(100, 200, 11);
        d.setColor(Color.WHITE);
        d.fillCircle(100, 200, 4);
    }

    /**.
     * notify the sprite that time has passed
     */
    @Override
    public void timePassed() {
    }
}
