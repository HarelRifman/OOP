/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;
/**
 * The {@code Xnor} class represents a logical XNOR operation on two boolean expressions.
 */
public class Xnor extends BinaryExpression {

    /**
     * @param str the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression e1 = getE1().assign(str, expression);
        Expression e2 = getE2().assign(str, expression);
        return new Xnor(e1, e2);
    }
    /**
     * Constructs a Xnor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public Xnor(Expression e1, Expression e2) {
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
        return getE1().evaluate(assignment).equals(getE2().evaluate(assignment));
    }

    /**
     * A convenience method to evaluate the expression with an empty assignment.
     *
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains variables which are not provided in the assignment.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return getE1().evaluate().equals(getE2().evaluate());
    }
    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     *
     * @return the NOR expression tree.
     */
    @Override
    public Expression norify() {
        // Step 1: Create NOR of E1 and E2
        Expression e1 = getE1().norify();
        Expression e2 = getE2().norify();
        return new Nor(new Nor(e1, new Nor(e1, e2)), new Nor(e2, new Nor(e1, e2)));
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     *
     * @return the NAND expression tree.
     */
    @Override
    public Expression nandify() {
        Expression a = getE1().nandify();
        Expression b = getE2().nandify();
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a, b));
    }

    /**
     * Creates a new instance of the Xnor expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the Xnor expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new Xnor(e1, e2);
    }

    /**
     * Returns the operator symbol for the Xnor expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getOperator() {
        return "#";
    }

    /**
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Val((new Xnor(getE1().simplify(), getE2().simplify())).evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().toString().equals(getE1().simplify().toString())) {
                return new Val(true);
            }
        } catch (Exception ignored) {
        }
        return new Xnor(getE1().simplify(), getE2().simplify());
    }
}
