//ID:316387950
/*
  @author Yotam Ben Dov
 * @version 4.0
 * ass6
 */

/**
 * creates a counter object.
 */
public class Counter {
    // Fields.
    private int counter;

    /**
     * add number to current count.
     *
     * @param number number to append.
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number number to reduce.
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return returns current counter.
     */
    int getValue() {
        return counter;
    }
}
