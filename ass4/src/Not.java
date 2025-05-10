/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.Map;

/**
 * The {@code Not} class represents a logical NOT operation on a boolean expression.
 */
public class Not extends UnaryExpression {

    /**
     * Constructs a Not expression with the specified sub-expression.
     *
     * @param exp the sub-expression.
     */
    public Not(Expression exp) {
        super(exp);
    }

    /**
     * Evaluates the expression.
     *
     * @param assignment a map containing variable values.
     * @return the result of the NOT operation.
     * @throws Exception if the expression contains a variable which is not in the assignment.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getExp().evaluate(assignment));
    }

    /**
     * Creates a new instance of the Not expression with the specified sub-expression.
     *
     * @param exp the sub-expression.
     * @return a new instance of the Not expression.
     */
    @Override
    public Expression assign(String var, Expression exp) {
        return new Not(this.getExp().assign(var, exp));
    }

    /**
     * Returns the operator symbol for the Not expression.
     *
     * @return the operator symbol.
     */
    @Override
    protected String getSign() {
        return "~";
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     *
     * @return the NANDified expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(getExp().nandify(), getExp().nandify());
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     *
     * @return the NORified expression.
     */
    @Override
    public Expression norify() {
        return new Nor(getExp().norify(), getExp().norify());
    }

    /**
     * @return simplified version
     */
    @Override
    public Expression simplify() {
       try {
           return new Val(new Not(getExp().simplify()).evaluate());
       } catch (Exception ignored) {
       }
        try {
            return new Val(getExp().simplify().evaluate());
        } catch (Exception ignored) {
        }
        return new Not(getExp().simplify());
    }
}
