/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;
import java.util.ArrayList;
/**
 * The {@code BinaryExpression} class serves as a base class for binary boolean expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression e1;
    private final Expression e2;

    /**
     * @return the first expression
     */
    public Expression getE1() {
        return e1;
    }
    /**
     * @return the second expression
     */
    public Expression getE2() {
        return e2;
    }

    /**
     * Constructs a BinaryExpression with the specified left and right sub-expressions.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     */
    protected BinaryExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names used in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.addAll(e1.getVariables());
        variables.addAll(e2.getVariables());
        return variables;
    }
    /**
     * Abstract method to be implemented by subclasses to create a new instance of the binary expression.
     *
     * @param e1 the left sub-expression.
     * @param e2 the right sub-expression.
     * @return a new instance of the binary expression.
     */
    protected abstract Expression createNew(Expression e1, Expression e2);

    /**
     * Abstract method to be implemented by subclasses to return the operator symbol.
     *
     * @return the operator symbol.
     */
    protected abstract String getOperator();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        return "(" + e1.toString() + " " + getOperator() + " " + e2.toString() + ")";
    }
}
