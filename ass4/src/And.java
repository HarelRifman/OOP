/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;
/**
 * The {@code And} class represents a logical AND operation on two boolean expressions.
 */
public class And extends BinaryExpression {


    /**
     * @param str the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression newLeftExp = getE1().assign(str, expression);
        Expression newRightExp = getE2().assign(str, expression);
        return new And(newLeftExp, newRightExp);
    }
    /**
     * Constructs an And expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public And(Expression e1, Expression e2) {
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
        return getE1().evaluate(assignment) && getE2().evaluate(assignment);
    }
    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     *
     * @return the expression tree resulting from converting all the operations to Nand.
     */
    @Override
    public Expression nandify() {
        // Recursively norify the sub-expressions
        Expression a = getE1().nandify();
        Expression b = getE2().nandify();
        return new Nand(new Nand(a, b), new Nand(a, b));
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     *
     * @return the expression tree resulting from converting all the operations to NOR.
     */
    @Override
    public Expression norify() {
        // Recursively norify the sub-expressions
        Expression e1 = getE1().norify();
        Expression e2 = getE2().norify();

        return new Nor(new Nor(e1, e1), new Nor(e2, e2));
    }
    /**
     * @return the simplified version
     */
    @Override
    public Expression simplify() {
        try {
            return new Val((new And(getE1().simplify(), getE2().simplify())).evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate()) {
                return getE2().simplify();
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().evaluate()) {
                return getE1().simplify();
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE1().simplify().evaluate()) {
                return new Val(false);
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE2().simplify().evaluate()) {
                return new Val(false);
            }
        } catch (Exception ignored) {
        }
        if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
            return getE2();
        }
        return new And(getE1().simplify(), getE2().simplify());
    }

    /**
     * Creates a new instance of the And expression with the specified sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the And expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new And(e1, e2);
    }

    /**
     * Returns the operator symbol for the And expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getOperator() {
        return "&";
    }
}
