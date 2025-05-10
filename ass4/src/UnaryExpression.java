/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;

/**
 * The {@code UnaryExpression} class serves as a base class for unary boolean expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression exp;

    /**
     * @return exp attribute.
     */
    public Expression getExp() {
        return exp;
    }

    /**
     * Constructs a UnaryExpression with the specified sub-expression.
     *
     * @param exp the sub-expression.
     */
    public UnaryExpression(Expression exp) {
        this.exp = exp;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names used in the expression.
     */
    @Override
    public List<String> getVariables() {
        return exp.getVariables();
    }
    /**
     * Abstract method to be implemented by subclasses to return the operator symbol.
     *
     * @return the operator symbol.
     */
    protected abstract String getSign();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        return getSign() + "(" + exp.toString() + ")";
    }
}
