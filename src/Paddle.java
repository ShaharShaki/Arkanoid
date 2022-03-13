//208524181

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Shahar Shaki
 * next mathod is the paddle of the game, implement the spirit and collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {

    private static final double EPSILON = Math.pow(10, -2);
    /*
    Fields
     */
    private biuoop.KeyboardSensor keyboard;
    private Color color = new Color(255, 205, 0);
    private Rectangle rectangle;
    private ArrayList<Ball> ballArrayList = new ArrayList<>();
    private int speed;

    /**
     * Constructor.
     *
     * @param rectangle Rectangle
     * @param keyboard  KeyboardSensor
     * @param speed is the paddle speed
     */
    public Paddle(Rectangle rectangle, KeyboardSensor keyboard, int speed) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * move the paddle to the left only if its inside the limits.
     */
    public void moveLeft() {
        boolean flag = false;

        for (int i = 0; i < ballArrayList.size(); i++) {
            if (this.isBallInsidePaddle(this.ballArrayList.get(i))) {
                ballArrayList.get(i).setCenter(new Point(this.rectangle.getUpperLeft().getX() + EPSILON,
                        this.rectangle.getUpperLeft().getY()));
                ballArrayList.get(i).setVelocity(ballArrayList.get(i).getVelocity().getDx() * -1,
                        ballArrayList.get(i).getVelocity().getDy());
                flag = true;
            }
        }
        if (!flag) {
            if (this.rectangle.getUpperLeft().getX() > 30) {
                this.rectangle.getUpperLeft().setX(this.rectangle.getUpperLeft().getX() - this.speed);
                if (this.rectangle.getUpperLeft().getX() < 30) {
                    this.rectangle.getUpperLeft().setX(30);
                }
                this.rectangle = new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(),
                        this.rectangle.getHeight());
            }
        }
    }

    /**
     * move the paddle to the right only if its inside the limits.
     */
    public void moveRight() {
        boolean flag = false;
        for (int i = 0; i < ballArrayList.size(); i++) {
            if (this.isBallInsidePaddle(this.ballArrayList.get(i))) {
                ballArrayList.get(i).setCenter(new Point(this.rectangle.getUpperLeft().getX()
                        + this.rectangle.getWidth() - 0.01, this.rectangle.getUpperLeft().getY() - EPSILON));
                ballArrayList.get(i).setVelocity(ballArrayList.get(i).getVelocity().getDx() * -1,
                        ballArrayList.get(i).getVelocity().getDy());
                flag = true;
            }
        }
        if (!flag) {
            if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < 770) {
                this.rectangle.getUpperLeft().setX(this.rectangle.getUpperLeft().getX() + this.speed);
                if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() > 770) {
                    this.rectangle.getUpperLeft().setX(770 - this.rectangle.getWidth());
                }
                this.rectangle = new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(),
                        this.rectangle.getHeight());
            }
        }
    }

    /**
     * call the methods left and right, depend which key press.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }

    }

    /**
     * draw on the paddle with the color on the surface.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * @return the object (paddle).
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * next method called only if there was a hit, and change the ball velocity depend on where it hit.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity velocity of the ball.
     * @param hitter the ball
     * @return the new velocity after hitting.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        //get the size of the paddle after the division of the paddle
        double divisionRegion = this.rectangle.getWidth() / 5;
        //upper left x value of the paddle.
        double upLeftX = this.rectangle.getUpperLeft().getX();
        //init new speed.
        double newSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        /*
        check if the collision occur on the x range.
         */
        if (upLeftX <= collisionPoint.getX() && collisionPoint.getX() <= upLeftX + this.rectangle.getWidth()) {
            /*
            if it hits on the sides of the paddle, change velocity by (-1).
             */
            if (this.rectangle.getDown().start().getY() <= collisionPoint.getY() && collisionPoint.getY()
                    <= this.rectangle.getUp().start().getY()) {
                if (Math.abs(collisionPoint.getX() - upLeftX) < EPSILON) {
                    return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
                }
                if (Math.abs(collisionPoint.getX() - this.rectangle.getRight().start().getX()) < EPSILON) {
                    return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
                }
            }
            /*
            if it on the first region change the ball velocity by 300 degree
             */
            if (collisionPoint.getX() < divisionRegion + upLeftX) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, newSpeed);
                return currentVelocity;
            }
            /*
            if it on the second region change the ball velocity by 330 degree
             */
            if (divisionRegion <= collisionPoint.getX() && collisionPoint.getX() < upLeftX + divisionRegion * 2) {
                currentVelocity = Velocity.fromAngleAndSpeed(330, newSpeed);
                return currentVelocity;
            }
            /*
            if it on the third region change the ball velocity by 0 degree
             */
            if (divisionRegion * 2 <= collisionPoint.getX() && collisionPoint.getX() < upLeftX + divisionRegion * 3) {
                currentVelocity = Velocity.fromAngleAndSpeed(0, newSpeed);
                return currentVelocity;
            }
            /*
            if it on the fourth region change the ball velocity by 30 degree
             */
            if (divisionRegion * 3 <= collisionPoint.getX() && collisionPoint.getX() < upLeftX + divisionRegion * 4) {
                currentVelocity = Velocity.fromAngleAndSpeed(30, newSpeed);
                return currentVelocity;
            }
            /*
            if it on the fifth region change the ball velocity by 60 degree
             */
            if (divisionRegion * 4 <= collisionPoint.getX() && collisionPoint.getX() <= upLeftX + divisionRegion * 5) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, newSpeed);
                return currentVelocity;
            }
        }
        //of there was no hit, just in case return the current velocity.
        return currentVelocity;
    }

    /**
     * .
     * add this paddle to the game
     *
     * @param g the game board.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**.
     * setter method for the ball array.
     * @param ballArray the ball array
     */
    public void setArrayBalls(ArrayList<Ball> ballArray) {
        this.ballArrayList = ballArray;
    }


    /**.
     * method check if a given ball is inside the paddle limits.
     * @param ball the ball the check on
     * @return true if the ball inside and false otherwise.
     */
    public boolean isBallInsidePaddle(Ball ball) {
        //if the x value of the ball is between the x value of the paddle.
        if (this.rectangle.getUpperLeft().getX() < ball.getX() + EPSILON && ball.getX() - EPSILON
                < this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
            //if the y value of the ball is inside the y value of the paddle.
            if (this.rectangle.getUpperLeft().getY() < ball.getY() + EPSILON && ball.getY() - EPSILON
                    < this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
                //return true if the ball is inside the paddle
                return true;
            }
        }
        //false otherwise.
        return false;
    }
}