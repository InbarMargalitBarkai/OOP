import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * Binary expression - represents as And(x,y) = (x & y).
 * In logic, mathematics and linguistics, And is the truth-functional operator of logical conjunction;
 * the and of a set of operands is true if and only if all of its operands are true.
 * The logical connective that represents this operator is typically written as ∧.
 * A ∧ B  is true if and only if is true.
 */
public class And extends BinaryExpression implements Expression {
    // Fields
    private Expression expression1;
    private Expression expression2;

    /**
     * Constructor.
     *
     * @param e1 - first expression.
     * @param e2 - second expression.
     */
    public And(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /**
     * Constructor for variables.
     *
     * @param var1 - the first variable.
     * @param var2 - the second variable.
     */
    public And(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * Constructor for values.
     *
     * @param val1 - the first value.
     * @param val2 - the second value.
     */
    public And(Boolean val1, Boolean val2) {
        this.expression1 = new Val(val1);
        this.expression2 = new Val(val2);
    }

    /**
     * Constructor for expression and value.
     *
     * @param exp - the expression.
     * @param val - the value.
     */
    public And(Expression exp, Boolean val) {
        this.expression2 = exp;
        this.expression1 = new Val(val);
    }

    /**
     * Constructor for value and expression.
     *
     * @param val - the value.
     * @param exp - the expression.
     */
    public And(Boolean val, Expression exp) {
        this.expression1 = new Val(val);
        this.expression2 = exp;
    }

    /**
     * Constructor for expression and variable.
     *
     * @param exp - the expression.
     * @param var - the variable.
     */
    public And(Expression exp, String var) {
        this.expression1 = exp;
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and expression.
     *
     * @param var - the variable.
     * @param exp - the expression.
     */
    public And(String var, Expression exp) {
        this.expression1 = new Var(var);
        this.expression2 = exp;
    }

    /**
     * Constructor for value and variable.
     *
     * @param val - the value.
     * @param var - the variable.
     */
    public And(Boolean val, String var) {
        this.expression1 = new Val(val);
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and value.
     *
     * @param var - the variable.
     * @param val - the value.
     */
    public And(String var, Boolean val) {
        this.expression1 = new Var(var);
        this.expression2 = new Val(val);
    }

    @Override
    /**
     * Evaluate.
     * @see Expression#evaluate(Map).
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            if (this.expression1.evaluate(assignment) && this.expression2.evaluate(assignment)) {
                return true;
            }
            return false;
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
            if (this.expression1.evaluate() && this.expression2.evaluate()) {
                return true;
            }
            return false;
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
        return super.getVariables(this.expression1, this.expression2);
    }

    @Override
    /**
     * To string.
     * @see Expression#toString().
     */
    public String toString() {
        return ("(" + this.expression1.toString() + " & " + this.expression2.toString() + ")");
    }

    @Override
    /**
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        return new And(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    @Override
    /**
     * Nandify - Q = A AND B = ( A NAND B ) NAND ( A NAND B ).
     * @see Expression#nandify().
     */
    public Expression nandify() {
        Expression leftIn = this.expression1.nandify();
        Expression rightIn = this.expression2.nandify();
        Expression leftAndRight = new Nand(leftIn, rightIn);
        return new Nand(leftAndRight, leftAndRight);
    }

    @Override
    /**
     * Norify - Q = A AND B = ( A NOR A ) NOR ( B NOR B ).
     * @see Expression#norify().
     */
    public Expression norify() {
        Expression baseExpressionOne = this.expression1.norify();
        Expression baseExpressionTwo = this.expression2.norify();
        Expression leftIn = new Nor(baseExpressionOne, baseExpressionOne);
        Expression rightIn = new Nor(baseExpressionTwo, baseExpressionTwo);
        return new Nor(leftIn, rightIn);
    }

    @Override
    /**
     * Simplify.
     * @see Expression#simplify().
     */
    public Expression simplify() {
        try {
            // means that both of the expressions are values
            if (this.getVariables().isEmpty()) {
                return new Val(this.expression1.evaluate() && this.expression2.evaluate());
            } else if (this.expression1.getVariables().isEmpty()) { // first expression is only with values
                if (this.expression1.evaluate().equals(false)) { // 0 & x = 0
                    return new Val(this.expression1.evaluate());
                }
                if (this.expression1.evaluate().equals(true)) { // 1 & x = x
                   return this.expression2.simplify();
                } else if (this.expression2.simplify().getVariables().isEmpty()) {
                    return new Val(this.expression1.evaluate() && this.expression2.simplify().evaluate());
                }
            } else if (this.expression2.getVariables().isEmpty()) { // second expression is only with values
                if (this.expression2.evaluate().equals(false)) { // x & 0 = 0
                    return (new Val(this.expression2.evaluate()));
                }
                if (this.expression2.evaluate().equals(true)) { // x & 1 = x
                    return this.expression1.simplify();
                } else if (this.expression1.simplify().getVariables().isEmpty()) {
                    return new Val(this.expression1.simplify().evaluate() && this.expression2.evaluate());
                }
            } else if (this.expression1.simplify().getVariables().isEmpty()) {
                if (this.expression1.simplify().evaluate().equals(false)) {
                    return new Val(this.expression1.simplify().evaluate());
                }
                if (this.expression1.simplify().evaluate().equals(true)) {
                    return this.expression2.simplify();
                } else if (this.expression2.simplify().getVariables().isEmpty()) {
                    return new Val(this.expression1.evaluate() && this.expression2.simplify().evaluate());
                }
            } else if (this.expression2.simplify().getVariables().isEmpty()) {
                if (this.expression2.simplify().evaluate().equals(false)) {
                    return new Val(this.expression2.simplify().evaluate());
                }
                if (this.expression2.simplify().evaluate().equals(true)) {
                    return this.expression1.simplify();
                } else if (this.expression1.simplify().getVariables().isEmpty()) {
                    return new Val(this.expression1.evaluate() && this.expression1.simplify().evaluate());
                }
            } else { // both expressions are variables
                // x & x = x
                if (this.expression1.simplify().toString().equals(this.expression2.simplify().toString())) {
                    // it doesn't matter which one to return because they are the same
                    return this.expression1.simplify();
                } else {
                    return new And(this.expression1.simplify(), this.expression2.simplify());
                }
            }
        } catch (Exception ex) {
            try {
                throw ex;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}