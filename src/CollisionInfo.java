//208524181

/**
 * @author Shahar Shaki
 * the class return the information about the collidable. which point and object.
 */
public class CollisionInfo {

    /*
    Fields
     */
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructor.
     * @param collisionPoint Point
     * @param collidableObject Collidable.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidableObject;
    }
}