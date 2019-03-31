package model;

/**
 * An abstract class the implements the Simpleton and PersonType interfaces to access their abstract methods.
 * It provides a single abstract method for accessing a person's income.
 * This class can access a person's name, math, saying and IQ.
 * @author Gary Johnson
 * @version 1.0
 */
abstract class Smarty implements Simpleton, PersonType {

    /**
     * A private int used to store a person's IQ
     */
    private int iq;

    /**
     * Regular Constructor that initializes a person's IQ
     * @param i An integer that is used to initialize a person's IQ.
     */
    Smarty(int i) {
        iq = i;
    }

    /**
     * An abstract method used to get a person's income.
     * @return a person's calculated income
     */
    public abstract double getIncome();

    /**
     * Method used to get a person's IQ that was initialized in the Constructor
     * @return the iq member variable set in the Constructor
     */
    public int getIQ() {
        return iq;
    }
}
