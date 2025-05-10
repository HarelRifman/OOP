/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;
/**
 * The {@code Xor} class represents a logical XOR operation on two boolean expressions.
 */
public class Xor extends BinaryExpression {

    /**
     * @param str the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression e1 = getE1().assign(str, expression);
        Expression e2 = getE2().assign(str, expression);
        return new Xor(e1, e2);
    }
    /**
     * Constructs a Xor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public Xor(Expression e1, Expression e2) {
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
        return logicalXOR(getE1().evaluate(assignment), getE2().evaluate(assignment));
    }

    /**
     * A convenience method to evaluate the expression with an empty assignment.
     *
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains variables which are not provided in the assignment.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return logicalXOR(getE1().evaluate(), getE2().evaluate());
    }

    /**
     * Performs a logical XOR (exclusive OR) operation on two boolean values.
     * The XOR operation returns true if and only if exactly one of the inputs is true.
     *
     * @param x the first boolean value.
     * @param y the second boolean value.
     * @return the result of the XOR operation.
     */
    public static boolean logicalXOR(boolean x, boolean y) {
        return ((x || y) && !(x && y));
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
        Expression a = getE1().nandify();
        Expression b = getE2().nandify();
        Expression left = new Nand(a, new Nand(a, b));
        Expression right = new Nand(b, new Nand(a, b));
        return new Nand(left, right);
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     * This method transforms the current expression tree so that all logical operations (AND, OR, XOR, etc.)
     * are represented using only the NOR operation.
     *
     * @return the new expression tree with all operations converted to logical NOR operations.
     */
    @Override
    public Expression norify() {
        Expression e1 = getE1().norify();
        Expression e2 = getE2().norify();
        return new Nor(new Nor(new Nor(e1, e1), new Nor(e2, e2)), new Nor(e1, e2));
    }

    /**
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Val((new Xor(getE1().simplify(), getE2().simplify())).evaluate());
        } catch (Exception ignored) {
        }
        try {
            return new Val(this.evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate()) {
                return new Not(getE2().simplify());
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().evaluate()) {
                return new Not(getE1().simplify());
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE1().simplify().evaluate()) {
                return getE2().simplify();
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE2().simplify().evaluate()) {
                return getE1().simplify();
            }
        } catch (Exception ignored) {
        }
        if (getE2().toString().equals(getE1().toString())) {
            return new Val(false);
        }
        if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
            return new Val(false);
        }
        return new Xor(getE1().simplify(), getE2().simplify());
    }

    /**
     * Creates a new instance of the Xor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the Xor expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new Xor(e1, e2);
    }

    /**
     * Returns the operator symbol for the Xor expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getOperator() {
        return "^";
    }
}
