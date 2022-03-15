import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * This class will inherite to: And, Or, Xor, Nand, Nor and Xnor classes.
 */
public abstract class BinaryExpression extends  BaseExpression {

    @Override
    /**
     * Evaluate.
     * @see BaseExpression#evaluate(Map).
     */
    protected abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Gets variables.
     * @param exp1 - the first expression.
     * @param exp2 - the second expression.
     * @return - returns a list of the variables in the expression.
     */
    public List<String> getVariables(Expression exp1, Expression exp2) {
        // making two lists
        List<String> expOneList = exp1.getVariables();
        List<String> expTwoList = exp2.getVariables();
        // checking if there are variables in one of the lists
        if (expOneList != null || expTwoList != null) {
            List<String> varsList = new LinkedList<>();
            if (expOneList != null) {
                varsList.addAll(expOneList);
            }
            if (expTwoList != null) {
                varsList.addAll(expTwoList);
            }
            // avoiding from duplicates variable
            LinkedHashSet<String> hashSet = new LinkedHashSet<>(varsList);
            List<String> listWithoutDuplicates = new ArrayList<String>();
            listWithoutDuplicates.addAll(hashSet);
            return listWithoutDuplicates;
        }
        return null;
    }
}
