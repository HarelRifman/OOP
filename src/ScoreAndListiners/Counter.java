package ScoreAndListiners;

/**
 * A counter class used for counting numeric values.
 *
 * @author Harel Rifman
 * ID 217398338
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the current count by a specified number.
     *
     * @param number the number to increase the count by
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the current count by a specified number.
     *
     * @param number the number to decrease the count by
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
