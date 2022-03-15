import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * assignment 6.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-13
 */

/**
 * Class with a main method that starts a game with four levels,
 * that supports all of the features described above.
 */
public class Ass6Game {

    /**
     * Starts the animations.
     * @param args - the user values.
     */
    public static void main(String[] args) {
        int x;
        List<LevelInformation> l = new ArrayList<LevelInformation>();
        DirectHit firstLevel = new DirectHit();
        WideEasy secondLevel = new WideEasy();
        Green3 thirdLevel = new Green3();
        FinalFour fourthLevel = new FinalFour();
        if (args.length == 0) {
            l.add(firstLevel);
            l.add(secondLevel);
            l.add(thirdLevel);
            l.add(fourthLevel);
        } else {
            for (int i = 0; i < args.length; i++) {
                try {
                    x = Integer.parseInt(args[i]);
                } catch (Exception ex) {
                    continue;
                }
                if (x == 1) {
                    l.add(firstLevel);
                }
                if (x == 2) {
                    l.add(secondLevel);
                }
                if (x == 3) {
                    l.add(thirdLevel);
                }
                if (x == 4) {
                    l.add(fourthLevel);
                }
            }
            if (l.isEmpty()) {
                System.out.println("No levels to run.");
                return;
            }
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runLevels(l);
    }
}