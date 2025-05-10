/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * The {@code Var} class represents a boolean variable.
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructs a Var expression with the specified variable name.
     *
     * @param name the variable name.
     */
    public Var(String name) {
        this.name = name;
    }
    /**
     * Evaluates the expression.
     *
     * @param assignment a map containing variable values.
     * @return the boolean value of the variable.
     * @throws Exception if the variable is not in the assignment.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (!assignment.containsKey(name)) {
            throw new Exception("Variable " + name + " not found in assignment.");
        }
        return assignment.get(name);
    }

    /**
     * Evaluates the expression with an empty assignment.
     *
     * @return the boolean value of the variable.
     */
    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Evaluation with no map.");
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names used in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(name);
        return variables;
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * {@code var} are replaced with the provided expression. Does not modify
     * the current expression.
     *
     * @param var the variable to be replaced.
     * @param expression the expression to replace the variable with.
     * @return a new expression with the variable replaced by the given expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
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

