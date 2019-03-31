package model;

/**
 * An interface that inherits from PersonType and provides three abstract methods for accessing a person's name, it's math problem, and it' saying
 * @author Gary Johnson
 * @version 1.0
 */
public interface Simpleton extends PersonType {

    /**
     * An abstract method used to a person's name.
     * @return the person's assigned name
     */
    String getName();

    /**
     * An abstract method used to get a person's math problem
     * @return the person's assigned math problem
     */
    String doMath();

    /**
     * An abstract method used to get a person's saying
     * @return the person's assigned saying
     */
    String saySomethingSmart();
}
