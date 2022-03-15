/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-03
 */

/**
 * This class display all our program,
 * and create the total game.
 */
public class Ass5Game {

    /**
     * Display the program.
     * @param args - the parameters we get from the user
     */
    public static void main(String[] args) {
        // create a game object
        Game gameObj = new Game();
        gameObj.initialize();
        gameObj.run();
    }
}