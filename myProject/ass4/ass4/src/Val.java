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
 * Representing truth values.
 */
public class Val implements Expression {
    // Fields
    private Boolean value;

    /**
     * Constructor.
     * @param val - our value.
     */
    public Val(Boolean val) {
        this.value = val;
    }

    @Override
    /**
     * Evaluate.
     * @see Expression#evaluate(Map).
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return this.value;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    /**
     * Evaluate.
     * @see Expression#evaluate().
     */
    public Boolean evaluate() throws Exception {
        try {
            return this.value;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    /**
     * Gets variable.
     * @see Expression#getVariables().
     */
    public List<String> getVariables() {
        return new LinkedList<String>();
    }

    @Override
    /**
     * To string.
     * @see Expression#toString().
     */
    public String toString() {
        String val = String.valueOf(this.value);
        if (val == "true") {
            val = "T";
        } else {
            val = "F";
        }
        return val.toString();
    }

    @Override
    /**
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    /**
     * Nandify.
     * @see Expression#nandify().
     */
    public Expression nandify() {
        return this;
    }

    @Override
    /**
     * Norify.
     * @see Expression#norify().
     */
    public Expression norify() {
        return this;
    }

    @Override
    /**
     * Simplify.
     * @see Expression#simplify().
     */
    public Expression simplify() {
        return this;
    }
}
