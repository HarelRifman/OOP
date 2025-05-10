/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code Val} class represents a boolean value.
 */
public class Val implements Expression {
    private final Boolean value;

    /**
     * Constructs a Val expression with the specified boolean value.
     *
     * @param value the boolean value.
     */
    public Val(Boolean value) {
        this.value = value;
    }

    /**
     * Evaluates the expression.
     *
     * @param assignment a map containing variable values.
     * @return the boolean value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return this.value;
    }

    /**
     * Evaluates the expression with an empty assignment.
     *
     * @return the boolean value.
     */
    @Override
    public Boolean evaluate() {
        return this.value;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return an empty list as Val contains no variables.
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        if (value) {
            return "T";
        }
        return "F";
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * {@code var} are replaced with the provided expression. Does not modify
     * the current expression.
     *
     * @param var the variable to be replaced.
     * @param expression the expression to replace the variable with.
     * @return the same Val instance as it does not contain variables.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NOR operation.
     *
     * @return the NOR expression tree.
     */
    @Override
    public Expression norify() {
        return this;
    }

    /**
     * @return null
     */
    @Override
    public Expression simplify() {
        return this;
    }

    /**
     * Returns the expression tree resulting from converting all the operations to the logical NAND operation.
     *
     * @return the NAND expression tree.
     */
    @Override
    public Expression nandify() {
        return this;
    }
}
