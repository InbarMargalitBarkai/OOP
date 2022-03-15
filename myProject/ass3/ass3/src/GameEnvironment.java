import java.util.ArrayList;
import java.util.List;

/**
 * assignment 3.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-04-15
 */

/**
 * During the game, there are going to be many objects a Ball can collide with.
 * The GameEnvironment class will be a collection of such things.
 * The ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    // the first value of the minDistance variable, so we will be able to change it's value
    static final double MAX_DIS = 1000;
    // Fields
    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Gets the list of collidable.
     * @return - collidables.
     */
    public List<Collidable> getCollidables() {
        return (this.collidables);
    }

    /**
     * Adding the given collidable to the environment.
     * @param c - the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assuming an object moving from line.start() to line.end().
     * @param trajectory - the trajectory of the ball.
     * @return - if this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestMeeting = null;
        Point meet;
        Collidable col = null;
        double dist = MAX_DIS;
        int i = 0;
        // check about each collidable if there is a collision with the line trajectory
        for (Collidable c : collidables) {
            if (c.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                meet = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                if ((meet != null) && (meet.distance(trajectory.start()) < dist)) {
                    closestMeeting = meet;
                    col = this.collidables.get(i);
                    dist = meet.distance(trajectory.start());
                }
            }
            i++;
        }
        // if there is'nt a collision
        if (closestMeeting == null) {
            return null;
        }
        return new CollisionInfo(closestMeeting, col);
    }

    /**
     * Get the closest collision corner.
     * @param center - the point center of the ball.
     * @param size - the radius of the ball.
     * @return - the closest point of collision in the corner of the rectangle, if there is.
     * Else, return null.
     */
    public Point getClosestCollisionCorner(Point center, int size) {
        Point tempPoint;
        for (int i = 0; i < this.collidables.size(); i++) {
            tempPoint = this.collidables.get(i).getCollisionRectangle().hitPointCorner(center, size);
            if (tempPoint != null) {
                return tempPoint;
            }
        }
        return null;
    }
}
