//208524181

/**
 * @author Shahar shaki
 * this is a counter class that having an increase and decrease methods.
 */
public class Counter {
    //field
    private int counter;

    /**.
     * Constructor
     * @param num int number
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**.
     * counter setter
     * @param counterSet get an in to set the counter
     */
    public void setCounter(int counterSet) {
        this.counter = counterSet;
    }

    /**.
     * method increase the counter by a given number
     * @param number the number to increase
     */
    void increase(int number) {
        this.counter += number;
    }

    /**.
     * method decrease the number from the counter.
     * @param number the number to decrease
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**.
     * getter methode to the counter
     * @return the current counter.
     */
    int getValue() {
        return this.counter;
    }
}