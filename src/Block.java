//208524181
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shahar Shaki
 * block calss implement the collidable and spirit and create a new block to the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private static final double EPSILON = Math.pow(10, -2);
    private static final int CHANGESIDE = -1;
    /*
    Fields
     */
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * .
     * constructor get rectangle and color to create a block
     *
     * @param rectangle rectangle
     * @param color     color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * method get a surface and set the color, paint the rectangles.
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * just return, the block is not moving.
     */
    public void timePassed() {
    }

    /**
     * methode add the spirit and the collidable to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * .
     * getter method
     *
     * @return return the rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * .
     * next method called only if there was a hit! so it will change the velocity.
     *
     * @param collisionPoint  Point
     * @param currentVelocity Velocity
     * @param hitter the ball
     * @return new velocity by which object was hitting.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        this.notifyHit(hitter);
        //we will use the upper left get x, so ill make a copy of it to make is more easy to read.
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        /*
        check the up and down hit. change the velocity of the dy by (-1).
         */
        if (upperLeftX <= collisionPoint.getX() && collisionPoint.getX() <= upperLeftX + this.rectangle.getWidth()) {
            if ((Math.abs(collisionPoint.getY() - this.rectangle.getUp().start().getY()) <= EPSILON)
                    || (Math.abs(collisionPoint.getY() - this.rectangle.getDown().start().getY()) <= EPSILON)) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (CHANGESIDE));
            }
        }
        return new Velocity(currentVelocity.getDx() * (CHANGESIDE), currentVelocity.getDy());
    }

    /**
     * method remove from game the block.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**.
     * method notify about the hit
     * @param hitter the ball
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hitListener : hitListeners) {
            hitListener.hitEvent(this, hitter);
        }

    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

