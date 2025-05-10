/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;

/**
 * The {@code Nor} class represents a logical NOR operation on two boolean expressions.
 */
public class Nor extends BinaryExpression {

    /**
     * @param str the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression e1 = getE1().assign(str, expression);
        Expression e2 = getE2().assign(str, expression);
        return new Nor(e1, e2);
    }
    /**
     * Constructs a Nor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public Nor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Evaluates the expression using the variable values provided
     * in the assignment, and returns the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a map containing variable values.
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains a variable which is not in the assignment.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getE1().evaluate(assignment) || getE2().evaluate(assignment));
    }

    /**
     * A convenience method to evaluate the expression with an empty assignment.
     *
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains variables which are not provided in the assignment.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return !(getE1().evaluate() || getE2().evaluate());
    }
    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     *
     * @return the NANDified expression.
     */
    @Override
    public Expression nandify() {
        Expression a = getE1().nandify();
        Expression b = getE2().nandify();
        Expression side = new Nand(new Nand(a, a), new Nand(b, b));
        return new Nand(side, side);
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     *
     * @return the NORified expression.
     */
    @Override
    public Expression norify() {
        return new Nor(getE1().norify(), getE2().norify());
    }

    /**
     * Creates a new instance of the Nor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the Nor expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new Nor(e1, e2);
    }

    /**
     * Returns the operator symbol for the Nor expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getOperator() {
        return "V";
    }

    /**
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Val((new Nor(getE1().simplify(), getE2().simplify())).evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate().equals(true)) {
                return new Val(false);
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().evaluate().equals(true)) {
                return new Val(false);
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate().equals(false)) {
                return new Not(getE2().simplify());
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().evaluate().equals(false)) {
                return new Not(getE1().simplify());
            }
        } catch (Exception ignored) {
        }
        if (getE2().toString().equals(getE1().toString())) {
            return new Not(getE2().simplify());
        }
        if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
            return new Not(getE1().simplify());
        }
        return new Nor(getE1().simplify(), getE2().simplify());
    }
}
