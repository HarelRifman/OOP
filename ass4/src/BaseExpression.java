/**
 * @authour Harel Rifman
 * ID 217398338
 **/

/**
 * The {@code BaseExpression} class serves as a base class for boolean expressions.
 */
public abstract class BaseExpression implements Expression {

    /**
     * @return the result of the exprassion
     * @throws Exception if the exparssion cannot be evaluated.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return this.evaluate(null);
    }
}
