/**
 * assignment 5.
 * @author Inbar Margalit Barkai ********* <inbarbarkai6@gmail.com>
 * @version 1.0
 * @since 2021-06-02
 */

/**
 * Counts how many things we have or was.
 */
public class Counter {
    // Fields
    private int counter = 0;

    /**
     * Add number to current count.
     * @param number - the number to add.
     */
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     * @param number - the number to subtract.
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets current count.
     * @return - the count.
     */
    int getValue() {
        return this.counter;
    }
}