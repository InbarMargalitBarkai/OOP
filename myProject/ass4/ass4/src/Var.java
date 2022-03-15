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
 * Representing variables.
 */
public class Var implements Expression {
    // Fields
    private String variable;

    /**
     * Constructor.
     * @param var - our variable.
     */
    public Var(String var) {
        this.variable = var;
    }

    @Override
    /**
     * Evaluate.
     * @see Expression#evaluate(Map).
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (assignment.get(this.variable));
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
        Exception ex = new Exception();
        throw ex;
    }

    @Override
    /**
     * Gets variable.
     * @see Expression#getVariables().
     */
    public List<String> getVariables() {
        List<String> list = new LinkedList<String>();
        list.add(this.variable);
        return list;
    }

    @Override
    /**
     * To string.
     * @see Expression#toString() .
     */
    public String toString() {
        return this.variable;
    }

    @Override
    /**
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
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
