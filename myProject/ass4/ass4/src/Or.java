import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * Binary expression - represents as Or(x,y) = (x | y).
 * In logic, disjunction is a logical connective typically notated ∨ whose meaning either refines
 * or corresponds to that of natural language expressions such as "or".
 * In classical logic, it is given a truth functional semantics on which ϕ ∨ ψ is true
 * unless both ϕ and ψ are false.
 * Because this semantics allows a disjunctive formula to be true when both of its disjuncts are true,
 * it is an inclusive interpretation of disjunction, in contrast with exclusive disjunction.
 */
public class Or extends BinaryExpression implements Expression {
    // Fields
    private Expression expression1;
    private Expression expression2;

    /**
     * Constructor.
     *
     * @param e1 - first expression.
     * @param e2 - second expression.
     */
    public Or(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /**
     * Constructor for variables.
     *
     * @param var1 - the first variable.
     * @param var2 - the second variable.
     */
    public Or(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * Constructor for values.
     *
     * @param val1 - the first value.
     * @param val2 - the second value.
     */
    public Or(Boolean val1, Boolean val2) {
        this.expression1 = new Val(val1);
        this.expression2 = new Val(val2);
    }

    /**
     * Constructor for expression and value.
     *
     * @param exp - the expression.
     * @param val - the value.
     */
    public Or(Expression exp, Boolean val) {
        this.expression2 = exp;
        this.expression1 = new Val(val);
    }

    /**
     * Constructor for value and expression.
     *
     * @param val - the value.
     * @param exp - the expression.
     */
    public Or(Boolean val, Expression exp) {
        this.expression1 = new Val(val);
        this.expression2 = exp;
    }

    /**
     * Constructor for expression and variable.
     *
     * @param exp - the expression.
     * @param var - the variable.
     */
    public Or(Expression exp, String var) {
        this.expression1 = exp;
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and expression.
     *
     * @param var - the variable.
     * @param exp - the expression.
     */
    public Or(String var, Expression exp) {
        this.expression1 = new Var(var);
        this.expression2 = exp;
    }

    /**
     * Constructor for value and variable.
     *
     * @param val - the value.
     * @param var - the variable.
     */
    public Or(Boolean val, String var) {
        this.expression1 = new Val(val);
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and value.
     *
     * @param var - the variable.
     * @param val - the value.
     */
    public Or(String var, Boolean val) {
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
            if (this.expression1.evaluate(assignment) || this.expression2.evaluate(assignment)) {
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
            if (this.expression1.evaluate() || this.expression2.evaluate()) {
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
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        return new Or(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    @Override
    /**
     * To string.
     * @see Expression#toString().
     */
    public String toString() {
        return ("(" + this.expression1.toString() + " | " + this.expression2.toString() + ")");
    }

    @Override
    /**
     * Nandify - Q = A OR B = ( A NAND A ) NAND ( B NAND B ).
     * @see Expression#nandify().
     */
    public Expression nandify() {
        Expression baseExpressionOne = this.expression1.nandify();
        Expression baseExpressionTwo = this.expression2.nandify();
        Expression left = new Nand(baseExpressionOne, baseExpressionOne);
        Expression right = new Nand(baseExpressionTwo, baseExpressionTwo);
        return new Nand(left, right);
    }

    @Override
    /**
     * Norify - Q = A OR B = ( A NOR B ) NOR ( A NOR B ).
     * @see Expression#norify().
     */
    public Expression norify() {
        Expression baseExpressionOne = this.expression1.norify();
        Expression baseExpressionTwo = this.expression2.norify();
        Expression leftAndRight = new Nor(baseExpressionOne, baseExpressionTwo);
        return new Nor(leftAndRight, leftAndRight);
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
                // 0 | 0 = 0
                if (this.expression1.evaluate().equals(false) && this.expression2.evaluate().equals(false)) {
                    return new Val(false);
                } else { // 1 | 1 = 1      0 | 1 = 1     1 | 0 = 1
                    return new Val(true);
                }
            } else if (this.expression1.getVariables().isEmpty()) { // first expression is only with values
                if (this.expression1.evaluate().equals(false)) { // 0 | x = x
                    return this.expression2.simplify();
                }
                if (this.expression1.evaluate().equals(true)) {  // 1 | x = 1
                    return new Val(true);
                } else if (this.expression2.simplify().getVariables().isEmpty()) {
                    return new Or(this.expression1.evaluate(), this.expression2.simplify().evaluate());
                }
            } else if (this.expression2.getVariables().isEmpty()) { // second expression is only with values
                if (this.expression2.evaluate().equals(false)) { // x | 0 = x
                    return this.expression1.simplify();
                }
                if (this.expression2.evaluate().equals(true)) { // x | 1 = 1
                    return new Val(true);
                } else if (this.expression1.simplify().getVariables().isEmpty()) {
                    return new Or(this.expression1.simplify().evaluate(), this.expression2.evaluate());
                }
            } else if (this.expression1.simplify().getVariables().isEmpty()) {
                if (this.expression1.simplify().evaluate().equals(false)) { // 0 | x = x
                    return this.expression2.simplify();
                }
                if (this.expression1.simplify().evaluate().equals(true)) { //  1 | x = 1
                    return new Val(true);
                } else if (this.expression2.simplify().getVariables().isEmpty()) {
                    return new Or(this.expression1.evaluate(), this.expression2.simplify().evaluate());
                }
            } else if (this.expression2.simplify().getVariables().isEmpty()) {
                if (this.expression2.simplify().evaluate().equals(false)) { // x | 0 = x
                    return this.expression1.simplify();
                }
                if (this.expression2.simplify().evaluate().equals(true)) { // x | 1 = 1
                    return new Val(true);
                } else if (this.expression1.simplify().getVariables().isEmpty()) {
                    return new Or(this.expression1.evaluate(), this.expression1.simplify().evaluate());
                }
            } else { // both expressions are variables
                // x | x = x
                if (this.expression1.simplify().toString().equals(this.expression2.simplify().toString())) {
                    // it doesn't matter which one to return because they are the same
                    return this.expression1.simplify();
                } else {
                    return new Or(this.expression1.simplify(), this.expression2.simplify());
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