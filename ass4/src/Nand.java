/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;

/**
 * The {@code Nand} class represents a logical NAND operation on two boolean expressions.
 */
public class Nand extends BinaryExpression {

    /**
     * Constructs a Nand expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public Nand(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Evaluates the expression using the variable values provided
     * in the assignment, and returns the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a map containing variable values.
     * @return the result of the NAND operation.
     * @throws Exception if the expression contains a variable which is not in the assignment.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getE1().evaluate(assignment) && getE2().evaluate(assignment));
    }
    /**
     * @param str        the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression newLeftExp = getE1().assign(str, expression);
        Expression newRightExp = getE2().assign(str, expression);
        return new Nand(newLeftExp, newRightExp);
    }

    /**
     * Returns a new instance of the Nand expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the Nand expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new Nand(e1, e2);
    }

    /**
     * Returns the operator symbol for the Nand expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getOperator() {
        return "A";
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     * This method transforms the current expression tree so that all logical operations (AND, OR, XOR, etc.)
     * are represented using only the NAND operation.
     *
     * @return the new expression tree with all operations converted to logical NAND operations.
     */
    @Override
    public Expression nandify() {
        return new Nand(getE1().nandify(), getE2().nandify());
    }

    /**
     * Returns the expression tree resulting from converting the current expression
     * (interpreted as an XNOR operation) to use only the logical NOR operation.
     * This method transforms the current expression tree so that the XNOR operation
     * is represented using only the NOR operation.
     *
     * @return the new expression tree with the XNOR operation converted to logical NOR operations.
     */
    @Override
    public Expression norify() {
        Expression e1 = getE1().norify();
        Expression e2 = getE2().norify();
        Expression expression = new Nor(new Nor(e1, e1), new Nor(e2, e2));
        return new Nor(expression, expression);
    }
    /**
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Val(new Nand(getE1().simplify(), getE2().simplify()).evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate()) {
                return new Not(getE2().simplify());
            }
        } catch (Exception e) {
        }
        try {
            if (getE2().simplify().evaluate()) {
                return new Not(getE1().simplify());
            }
        } catch (Exception e) {
        }
        try {
            if (!getE1().simplify().evaluate()) {
                return new Val(true);
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE2().simplify().evaluate()) {
                return new Val(true);
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
                return new Not(getE2().simplify());
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
                return new Not(getE2().simplify());
            }
        } catch (Exception ignored) {
        }
        return new Nand(getE1().simplify(), getE2().simplify());
    }
}
