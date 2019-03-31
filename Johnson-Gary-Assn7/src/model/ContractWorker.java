package model;

/**
 * A class that inherits from Smarty to implement all of its abstract methods.
 * Used to create a person of type ContractWorker that knows about space, can do division, and income based on pay per completed contract.
 * Defines and implements a method to access the number of hours worked.
 * Overrides all of Smarty's abstract methods.
 * @author Gary Johnson
 * @version 1.0
 */
public class ContractWorker extends Smarty {

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
     * A private int used to store a ContractWorker's completed contracts
     */
    private int m_Contracts;

    /**
     * A private double used to store a ContractWorker's pay per contract
     */
    private double m_Pay;

    /**
     * Regular Constructor that initializes all the member variables
     * @param name String used to initialize the person's name
     * @param math String used to initialize the person's math problem
     * @param saying String used to initialize the person's saying
     * @param iq int used to initialize the person's IQ by passing it to HourlyWorker's super class which acts as the constructor for Smarty
     * @param contracts int used to initialize the ContractWorker's contracts completed
     * @param pay double used to initialize the ContractWorker's pay per contract
     */
    public ContractWorker(String name, String math, String saying, int iq, int contracts, double pay) {
        super(iq);

        m_Person = "Contract Worker";
        this.m_Name = name;
        this.m_Math = math;
        this.m_Saying = saying;
        this.m_Contracts = contracts;
        this.m_Pay = pay;
    }

    /**
     * Getter method used to get a ContractWorker's contracts completed
     * @return the ContractWorker's contracts completed as set by the Constructor's contracts parameter
     */
    public int getContractsCompleted() {
        return m_Contracts;
    }

    /**
     * Overridden getter method used to get an ContractWorker's IQ
     * @return the IQ value that was sent to the Smarty constructor and initialized from the Constructor's IQ parameter
     */
    @Override
    public int getIQ() {
        return super.getIQ();
    }

    /**
     * Overridden getter method used to get an ContractWorker's personType
     * @return the ContractWorker's personType as set by the Constructor
     */
    @Override
    public String getPersonType() {
        return m_Person;
    }

    /**
     * Overridden getter method used to get an ContractWorker's name
     * @return the ContractWorker's name as set by the Constructor's name parameter
     */
    @Override
    public String getName() {
        return m_Name;
    }

    /**
     * Overridden getter method used to get an ContractWorker's math problem
     * @return the ContractWorker's math problem as set by the Constructor's math parameter
     */
    @Override
    public String doMath() {
        return m_Math;
    }

    /**
     * Overridden getter method used to get an ContractWorker's saying
     * @return the ContractWorker's saying as set by the Constructor's saying parameter
     */
    @Override
    public String saySomethingSmart() {
        return m_Saying;
    }

    /**
     * Overridden getter method used to get an ContractWorker's income
     * Income is calculated by multiplying the ContractWorker's wages by the ContractWorker's hours
     * @return the ContractWorker's calculated income
     */
    @Override
    public double getIncome() {
        return m_Pay * m_Contracts;
    }
}
