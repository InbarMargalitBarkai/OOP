import java.util.List;
import java.util.Map;

/**
 * In the first part,we begin with a simple interface called Expression:
 * (this interface uses generics and map).
 */
public interface Expression {

    /**
     * Evaluate the expression.
     * @param assignment - the variable values provided in the assignment.
     * @return - the result.
     * @throws Exception - if the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return - the result.
     * @throws Exception - if the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    Boolean evaluate() throws Exception;

    /**
     * Gets variables.
     * @return - Returns a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * To string.
     * @return - returns a nice string representation of the expression.
     */
    String toString();

    /**
     * Assign.
     * @param var - the variables that will be replaced.
     * @param expression - the provided expression replaced var.
     * @return - returns a new expression in which all occurrences of the variable
     * (Does not modify the current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * Nandify.
     * @return - returns the expression tree resulting from converting all the operations to the logical Nand operation.
     */
     Expression nandify();

    /**
     * Norify.
     * @return - returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * Simplify.
     * @return - returned a simplified version of the current expression.
     */
    Expression simplify();
}
