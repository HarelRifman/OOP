/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * The {@code Or} class represents a logical OR operation on two boolean expressions.
 */
public class Or extends BinaryExpression {

    /**
     * @param str the variable string to replace.
     * @param expression the expression to replace the variable with.
     * @return a new Object from this class type expression with the assigned sub-expressions.
     */
    @Override
    public Expression assign(String str, Expression expression) {
        Expression e1 = getE1().assign(str, expression);
        Expression e2 = getE2().assign(str, expression);
        return new Or(e1, e2);
    }
    /**
     * Constructs an Or expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    public Or(Expression e1, Expression e2) {
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
        return getE1().evaluate(assignment) || getE2().evaluate(assignment);
    }

    /**
     * A convenience method to evaluate the expression with an empty assignment.
     *
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains variables which are not provided in the assignment.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return getE1().evaluate() || getE2().evaluate();
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names used in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.addAll(getE1().getVariables());
        variables.addAll(getE2().getVariables());
        return variables;
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
        return new Nand(new Nand(a, a), new Nand(b, b));
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

        // Step 1: Compute A NOR B
        Expression aNorB = new Nor(e1, e2);

        // Step 2: Compute (A NOR B) NOR (A NOR B)

        return new Nor(aNorB, aNorB);
    }

    /**
     * @return simplified version of the expression
     */
    @Override
    public Expression simplify() {
        try {
            return new Val((new Or(getE1().simplify(), getE2().simplify())).evaluate());
        } catch (Exception ignored) {
        }
        try {
            if (getE1().simplify().evaluate()) {
                return new Val(true);
            }
        } catch (Exception ignored) {
        }
        try {
            if (getE2().simplify().evaluate()) {
                return new Val(true);
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE2().simplify().evaluate()) {
                return getE1().simplify();
            }
        } catch (Exception ignored) {
        }
        try {
            if (!getE1().simplify().evaluate()) {
                return getE2().simplify();
            }
        } catch (Exception ignored) {
        }
        if (getE1().toString().equals(getE2().toString())) {
            return getE2().simplify();
        }
        if (getE1().simplify().toString().equals(getE2().simplify().toString())) {
            return getE2().simplify();
        }

        return new Or(getE1().simplify(), getE2().simplify());
    }

    /**
     * Creates a new instance of the Or expression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of Or expression.
     */
    @Override
    protected Expression createNew(Expression e1, Expression e2) {
        return new Or(e1, e2);
    }

    /**
     * Returns the operator symbol for the Or expression.
     *
     * @return the operator symbol for Or.
     */
    @Override
    protected String getOperator() {
        return "|";
    }
}
