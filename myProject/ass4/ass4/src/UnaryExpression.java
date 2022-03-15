import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * This class will inherite to: Not class.
 */
public abstract class UnaryExpression extends BaseExpression {

    @Override
    /**
     * Evaluate.
     * @see BaseExpression#evaluate(Map).
     */
    protected abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Gets variables.
     * @param exp - the expression.
     * @return - returns a list of the variables in the expression.
     */
    public List<String> getVariables(Expression exp) {
        return (exp.getVariables());
    }
}
