/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;
import java.util.Map;

/**
 * The {@code Expression} interface represents a Boolean expression
 * which can be evaluated and manipulated.
 */
public interface Expression {

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
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method to evaluate the expression with an empty assignment.
     *
     * @return the result of the expression evaluation.
     * @throws Exception if the expression contains variables which are not provided in the assignment.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names used in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    @Override
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * {@code var} are replaced with the provided expression. Does not modify
     * the current expression.
     *
     * @param var the variable to be replaced.
     * @param expression the expression to replace the variable with.
     * @return a new expression with the variable replaced by the given expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     * This method transforms the current expression tree so that all logical operations (AND, OR, XOR, etc.)
     * are represented using only the NAND operation.
     *
     * @return the new expression tree with all operations converted to logical NAND operations.
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     * This method transforms the current expression tree so that all logical operations (AND, OR, XOR, etc.)
     * are represented using only the NOR operation.
     *
     * @return the new expression tree with all operations converted to logical NOR operations.
     */
    Expression norify();

    /**
     * @return Returned a simplified version of the current expression.
     */
    Expression simplify();
}
