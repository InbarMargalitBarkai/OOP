import java.util.ArrayList;
import java.util.List;

/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-01
 */

/**
 * During the game, there are going to be many objects a Ball can collide with.
 * The GameEnvironment class will be a collection of such things.
 * The ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    // Fields
    private List<Collidable> collidableList;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * Adding the given collidable to the game environment.
     * @param c - the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Removing the given collidable from the game environment.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Get the closest collision point between all the collidable to a line.
     * Go over all the collidable, the ones that collide with the line, add to a list.
     * If there is non return null, else, find the closest one to the start of the line,
     * and return it.
     * @param trajectory the line to check the collision with
     * @return the information of the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> possibleCollisions = new ArrayList<CollisionInfo>();
        for (Collidable c : this.collidableList) {
            //if the line is colliding with the object, add it to the list
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                CollisionInfo possible = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(
                        c.getCollisionRectangle()), c);
                possibleCollisions.add(possible);
            }
        }
        if (possibleCollisions.size() == 0) {
            return null;
        }
        CollisionInfo collisionInfo = closestCollision(possibleCollisions, trajectory.start());
        collisionInfo.setCollisionSide();
        return collisionInfo;
    }

    /**
     * Get from a list of collision points the closest one to a point.
     * Go over the list, and every time save the minimum distance.
     * @param possibleCollisions the list of collision points
     * @param startPoint the point to compare to (this is the start of a line)
     *
     * @return the information of the closest point
     */
    private CollisionInfo closestCollision(List<CollisionInfo> possibleCollisions, Point startPoint) {
        CollisionInfo closest = possibleCollisions.get(0);
        for (CollisionInfo c : possibleCollisions) {
            if (c.collisionPoint().distance(startPoint) < closest.collisionPoint().distance(startPoint)) {
                closest = c;
            }
        }
        return closest;
    }

    /**
     * Gets the list of collidable.
     * @return - collidables.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }
}
