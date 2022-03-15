import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * Unary expression - represents as Not(x) = ~(x).
 * In logic, negation, also called the logical complement,
 * is an operation that takes a proposition P to another proposition "not P", written ¬P, ~P or P̅.
 * It is interpreted intuitively as being true when P is false, and false when P is true.
 * Negation is thus a unary (single-argument) logical connective.
 * It may be applied as an operation on notions, propositions, truth values, or semantic values more generally.
 */
public class Not extends UnaryExpression implements Expression {
    // Fields
    private Expression expression1;

    /**
     * Constructor.
     *
     * @param exp - our expression.
     */
    public Not(Expression exp) {
        this.expression1 = exp;
    }

    /**
     * Constructor for value.
     *
     * @param val - our value.
     */
    public Not(Boolean val) {
        this.expression1 = new Val(val);
    }

    /**
     * Constructor for variable.
     *
     * @param var - our variable.
     */
    public Not(String var) {
        this.expression1 = new Var(var);
    }

    @Override
    /**
     * Evaluate.
     * @see Expression#evaluate(Map).
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (!(this.expression1.evaluate(assignment)));
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
            return (!(this.expression1.evaluate()));
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
        return super.getVariables(this.expression1);
    }

    @Override
    /**
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        return new Not(this.expression1.assign(var, expression));
    }

    @Override
    /**
     * To string.
     * @see Expression#toString().
     */
    public String toString() {
        return ("~(" + this.expression1.toString() + ")");
    }

    @Override
    /**
     * Nandify - Q = NOT( A ) = A NAND A.
     * @see Expression#nandify().
     */
    public Expression nandify() {
        Expression baseExpressionOne = this.expression1.nandify();
        return new Nand(baseExpressionOne, baseExpressionOne);
    }

    @Override
    /**
     * Norify - Q = NOT( A ) = A NOR A.
     * @see Expression#norify().
     */
    public Expression norify() {
        Expression baseExpressionOne = this.expression1.norify();
        return new Nor(baseExpressionOne, baseExpressionOne);
    }

    @Override
    /**
     * Simplify.
     * @see Expression#simplify().
     */
    public Expression simplify() {
        // means it's only a value
        if (this.expression1.getVariables().isEmpty()) {
            try {
                // ~(1) = 0
                if (this.expression1.evaluate().equals(true)) {
                    return new Val(false);
                } else { // ~(0) = 1
                    return new Val(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}