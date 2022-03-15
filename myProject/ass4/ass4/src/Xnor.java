import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-05-06
 */

/**
 * Binary expression - represents as Xnor(x,y) = (x # y).
 * Logical equality is a logical operator that corresponds to equality in Boolean algebra and to the logical
 * biconditional in propositional calculus. It gives the functional value true if both functional arguments have
 * the same logical value, and false if they are different.
 */
public class Xnor extends BinaryExpression implements Expression {
    // Fields
    private Expression expression1;
    private Expression expression2;

    /**
     * Constructor.
     * @param e1 - first expression.
     * @param e2 - second expression.
     */
    public Xnor(Expression e1, Expression e2) {
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /**
     * Constructor for variables.
     * @param var1 - the first variable.
     * @param var2 - the second variable.
     */
    public Xnor(String var1, String var2) {
        this.expression1 = new Var(var1);
        this.expression2 = new Var(var2);
    }

    /**
     * Constructor for values.
     * @param val1 - the first value.
     * @param val2 - the second value.
     */
    public Xnor(Boolean val1, Boolean val2) {
        this.expression1 = new Val(val1);
        this.expression2 = new Val(val2);
    }

    /**
     * Constructor for expression and value.
     * @param exp - the expression.
     * @param val - the value.
     */
    public Xnor(Expression exp, Boolean val) {
        this.expression2 = exp;
        this.expression1 = new Val(val);
    }

    /**
     * Constructor for value and expression.
     * @param val - the value.
     * @param exp - the expression.
     */
    public Xnor(Boolean val, Expression exp) {
        this.expression1 = new Val(val);
        this.expression2 = exp;
    }

    /**
     * Constructor for expression and variable.
     * @param exp - the expression.
     * @param var - the variable.
     */
    public Xnor(Expression exp, String var) {
        this.expression1 = exp;
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and expression.
     * @param var - the variable.
     * @param exp - the expression.
     */
    public Xnor(String var, Expression exp) {
        this.expression1 = new Var(var);
        this.expression2 = exp;
    }

    /**
     * Constructor for value and variable.
     * @param val - the value.
     * @param var - the variable.
     */
    public Xnor(Boolean val, String var) {
        this.expression1 = new Val(val);
        this.expression2 = new Var(var);
    }

    /**
     * Constructor for variable and value.
     * @param var - the variable.
     * @param val - the value.
     */
    public Xnor(String var, Boolean val) {
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
            if ((this.expression1.evaluate(assignment) && this.expression2.evaluate(assignment))
                    || ((!(this.expression1.evaluate(assignment))) && (!(this.expression2.evaluate(assignment))))) {
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
            if ((this.expression1.evaluate() && this.expression2.evaluate())
                    || ((!(this.expression1.evaluate())) && (!(this.expression2.evaluate())))) {
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
        return super.getVariables(expression1, expression2);
    }

    @Override
    /**
     * Assign.
     * @see Expression#assign(String, Expression).
     */
    public Expression assign(String var, Expression expression) {
        return new Xnor(this.expression1.assign(var, expression), this.expression2.assign(var, expression));
    }

    @Override
    /**
     * To string.
     * @see Expression#toString().
     */
    public String toString() {
        return ("(" + this.expression1.toString() + " # " + this.expression2.toString() + ")");
    }

    @Override
    /**
     * Nandify - Q = A XNOR B = [ ( A NAND A ) NAND ( B NAND B ) ] NAND ( A NAND B ).
     * @see Expression#nandify().
     */
    public Expression nandify() {
        Expression baseExpressionOne = this.expression1.nandify();
        Expression baseExpressionTwo = this.expression2.nandify();
        Expression leftLeftIn = new Nand(baseExpressionOne, baseExpressionOne);
        Expression leftRightIn = new Nand(baseExpressionTwo, baseExpressionTwo);
        Expression left = new Nand(leftLeftIn, leftRightIn);
        Expression right = new Nand(baseExpressionOne, baseExpressionTwo);
        return new Nand(left, right);
    }

    @Override
    /**
     * Norify - Q = A XNOR B = [ A NOR ( A NOR B ) ] NOR [ B NOR ( A NOR B ) ].
     * @see Expression#norify().
     */
    public Expression norify() {
        Expression baseExpressionOne = this.expression1.norify();
        Expression baseExpressionTwo = this.expression2.norify();
        Expression leftAndRightRightIn = new Nor(baseExpressionOne, baseExpressionTwo);
        Expression left = new Nor(baseExpressionOne, leftAndRightRightIn);
        Expression right = new Nor(baseExpressionTwo, leftAndRightRightIn);
        return new Nor(left, right);
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
                // 0 # 0 = 1         1 # 1 = 1
                if (this.expression1.evaluate().equals(this.expression2.evaluate())) {
                    return new Val(true);
                }
            } else { // both expressions are variables
                // x # x = 1
                if (this.expression1.simplify().toString().equals(this.expression2.simplify().toString())) {
                    // it doesn't matter which one to return because they are the same
                    return new Val(true);
                } else {
                    return new Xnor(this.expression1.simplify(), this.expression2.simplify());
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