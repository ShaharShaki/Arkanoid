import java.util.ArrayList;

/**
 * @author Shahar Shaki
 * calass game enviroment check the closest collision and had a list of collidable objects.
 */
public class GameEnvironment {

    /*
    Field
     */
    private ArrayList<Collidable> collidableList;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * // add the given collidable to the environment.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }


    /**
     * next method get the trajectory of a ball and return the information about the collision if occur.
     * @param trajectory Line
     * @return the info about the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //create a list of point and objects.
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Collidable> objects = new ArrayList<>();
        /*
        put all the collidable in the array.
         */
        for (int i = 0; i < this.collidableList.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(collidableList.get(i).getCollisionRectangle()) != null) {
                points.add(trajectory.closestIntersectionToStartOfLine(collidableList.get(i).getCollisionRectangle()));
                objects.add(collidableList.get(i));
            }
        }
        //if there is no points return null, means there is no colloidal.
        if (points.isEmpty()) {
            return null;
        }
        //init both temp
        Collidable tempCollidable = objects.get(0);
        Point tempPoint = points.get(0);
        /*
        next block check which point in the closest to the start point of the trajectory and it's object.
         */
        for (int j = 1; j < points.size(); j++) {
            if (points.get(j).distance(trajectory.start()) < tempPoint.distance(trajectory.start())) {
                tempPoint = points.get(j);
                tempCollidable = objects.get(j);
            }
        }
        //return the information about the collision.
        return new CollisionInfo(tempPoint, tempCollidable);
    }

    /**.
     * getter method for the collidable list
     * @return the collidable list
     */
    public ArrayList<Collidable> getCollidableList() {
        return this.collidableList;
    }
}