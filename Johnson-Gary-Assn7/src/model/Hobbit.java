package model;

/**
 * A class that implements the Simpleton and PersonType interfaces and their abstract methods.
 * Used to create a person of type Hobbit that knows about gardening, can do addition, and picks carrots.
 * Defines and implements a method to access the number of carrots picked.
 * Overrides all of Simpleton's and PersonType's abstract methods.
 * @author Gary Johnson
 * @version 1.0
 */
public class Hobbit implements Simpleton, PersonType {

    /**
     * A private String used to store a person's type
     */
    private String person;

    /**
     * A private String used to store a person's name
     */
    private String name;

    /**
     * A private String used to store a person's math problem
     */
    private String math;

    /**
     * A private String used to store a person's saying
     */
    private String saying;

    /**
     * A private int used to store a Hobbit's picked carrots
     */
    private int carrots;

    /**
     * Regular Constructor that initializes all the member variables
     * @param name String used to initialize the person's name
     * @param math String used to initialize the person's math problem
     * @param saying String used to initialize the person's saying
     * @param carrots int used to initialize the Hobbit's picked carrots
     */
    public Hobbit(String name, String math, String saying, int carrots) {
        person = "Hobbit";
        this.name = name;
        this.math = math;
        this.saying = saying;
        this.carrots = carrots;
    }

    /**
     * Getter method used get a Hobbit's picked carrots
     * @return the Hobbit's carrots picked as set by the Constructor's carrots parameter
     */
    public int getCarrotsPicked() {
        return carrots;
    }

    /**
     * Overridden getter method used to get a Hobbit's name
     * @return the Hobbit's name as set by the Constructor's name parameter
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Overridden getter method used to get an Hobbit's math problem
     * @return the Hobbit's math problem as set by the Constructor's math parameter
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * Overridden getter method used to get an Hobbit's saying
     * @return the Hobbit's saying as set by the Constructor's saying parameter
     */
    @Override
    public String saySomethingSmart() {
        return saying;
    }

    /**
     * Overridden getter method used to get an Hobbit's personType
     * @return the Hobbit's personType as set by the Constructor
     */
    @Override
    public String getPersonType() {
        return person;
    }
}