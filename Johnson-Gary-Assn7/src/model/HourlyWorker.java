package model;

/**
 * A class that inherits from Smarty to implement all of its abstract methods.
 * Used to create a person of type HourlyWorker that knows about animals, can do multiplication, and income based on hourly wages.
 * Defines and implements a method to access the number of hours worked.
 * Overrides all of Smarty's abstract methods.
 * @author Gary Johnson
 * @version 1.0
 */
public class HourlyWorker extends Smarty {

    /**
     * A private String used to store a person's type
     */
    private String m_Person;

    /**
     * A private String used to store a person's name
     */
    private String m_Name;

    /**
     * A private String used to store a person's math problem
     */
    private String m_Math;

    /**
     * A private String used to store a person's saying
     */
    private String m_Saying;

    /**
     * A private double used to store an Hourly Worker's wages per hour
     */
    private double m_Wages;

    /**
     * A private int used to store an Hourly Worker's hours worked
     */
    private int m_Hours;

    /**
     * Regular Constructor that initializes all the member variables
     * @param name String used to initialize the person's name
     * @param math String used to initialize the person's math problem
     * @param saying String used to initialize the person's saying
     * @param iq int used to initialize the person's IQ by passing it to HourlyWorker's super class which acts as the constructor for Smarty
     * @param hours int used to initialize the HourlyWorker's hours worked
     * @param wages double used to initialize the HourlyWorker's wages per hour
     */
    public HourlyWorker(String name, String math, String saying, int iq, int hours, double wages) {
        super(iq);

        m_Person = "Hourly Worker";
        this.m_Name = name;
        this.m_Math = math;
        this.m_Saying = saying;
        this.m_Hours = hours;
        this.m_Wages = wages;
    }

    /**
     * Getter method used to get an HourlyWorker's hours worked
     * @return the HourlyWorker's hours worked as set by the Constructor's hours parameter
     */
    public int getHoursWorked() {
        return m_Hours;
    }

    /**
     * Overridden getter method used to get an HourlyWorker's IQ
     * @return the IQ value that was sent to the Smarty constructor and initialized from the Constructor's IQ parameter
     */
    @Override
    public int getIQ() {
        return super.getIQ();
    }


    /**
     * Overridden getter method used to get an HourlyWorker's name
     * @return the HourlyWorker's name as set by the Constructor's name parameter
     */
    @Override
    public String getName() {
        return m_Name;
    }

    /**
     * Overridden getter method used to get an HourlyWorker's math problem
     * @return the HourlyWorker's math problem as set by the Constructor's math parameter
     */
    @Override
    public String doMath() {
        return m_Math;
    }

    /**
     * Overridden getter method used to get an HourlyWorker's saying
     * @return the HourlyWorker's saying as set by the Constructor's saying parameter
     */
    @Override
    public String saySomethingSmart() {
        return m_Saying;
    }

    /**
     * Overridden getter method used to get an HourlyWorker's personType
     * @return the HourlyWorker's personType as set by the Constructor
     */
    @Override
    public String getPersonType() {
        return m_Person;
    }

    /**
     * Overridden getter method used to get an HourlyWorker's income
     * Income is calculated by multiplying the HourlyWorker's wages by the HourlyWorker's hours
     * @return the HourlyWorker's calculated income
     */
    @Override
    public double getIncome() {
        return m_Wages * m_Hours;
    }
}