import java.util.Map;
import java.util.TreeMap;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-12
 */

/**
 * This class is testing our code.
 */
public class ExpressionsTest {
    /**
     * Our main function.
     *
     * @param args - the parameters we get from the user.
     * @throws Exception - checking if something is'nt going well.
     */
    public static void main(String[] args) throws Exception {
        // create an expression with at least three variables
        Expression expression1 = new Nand(new Xor(new Nor(new Var("x"), new Not("y")),
                new And(new Var("z"), new Val(false))),
                new Or(new Var("x"), new Val(true)));
        // print the expression
        System.out.println(expression1.toString());
        // print the value of the expression with an assignment to every variable
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        Boolean value = expression1.evaluate(assignment);
        System.out.println("The result is: " + value);
        // print the Nandified version of the expression
        System.out.println(expression1.nandify());
        // print the Norified version of the expression
        System.out.println(expression1.norify());
        // print the simplified version of the expression
        System.out.println(expression1.simplify());
    }
}