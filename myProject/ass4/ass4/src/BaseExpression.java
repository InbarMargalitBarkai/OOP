import java.util.Map;
import java.util.TreeMap;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * This class will inherite to: BinaryExpression and UnaryExpression classes.
 */
public abstract class BaseExpression {
    /**
     * Evaluate the expression.
     * @param assignment - variable values provided in the assignment.
     * @return -  return the result.
     * @throws Exception - if the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    protected abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return - return the result.
     * @throws Exception - if the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    protected Boolean evaluate() throws Exception {
        try {
            return evaluate(new TreeMap<String, Boolean>());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
