//208524181

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author Shahar Shaki
 * this class is the spirit collection, hold an array with spirits and draw them.
 */
public class SpriteCollection {

    //field
    private ArrayList<Sprite> spriteArrayList;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteArrayList = new ArrayList<Sprite>();
    }

    /**
     * add a new spirit to the list.
     * @param s spirit
     */
    public void addSprite(Sprite s) {
        spriteArrayList.add(s);
    }

    /**
     * call the timePassed method on all spirit in the array.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteArrayList.size(); i++) {
            spriteArrayList.get(i).timePassed();
        }
    }

    /**
     * call the draw on method on all the spirits.
     * @param d draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteArrayList.size(); i++) {
            spriteArrayList.get(i).drawOn(d);
        }
    }
    /**.
     * getter method for the array list of sprites
     * @return the array list
     */
    public ArrayList<Sprite> getSpriteArrayList() {
        return this.spriteArrayList;
    }
}