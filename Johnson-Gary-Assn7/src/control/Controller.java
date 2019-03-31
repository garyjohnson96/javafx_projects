package control;

import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.ContractWorker;
import model.Hobbit;
import model.HourlyWorker;
import model.Simpleton;
import java.util.ArrayList;

/**
 * Helper class that can create new people based on Display input, add them to the list, and print out text based on the selection made in the ToolBar
 * @author Gary Johnson
 * @version 1.0
 */
public class Controller {

    /**
     * A private ArrayList filled with Simpleton objects
     */
    private ArrayList<Simpleton> list;

    /**
     * A private Text used to print out whatever is returned by a person's getter methods.
     */
    private Text text;

    /**
     * Default Constructor that initializes text and the list.
     * Adds six default people to the list.
     */
    public Controller() {
        text = new Text();
        list = new ArrayList<>();
        Simpleton simpleton1 = new HourlyWorker("Becky", "10 x 10 = 100", "Turtles can live longer than 100 years.", 67, 10, 10.0);
        Simpleton simpleton2 = new HourlyWorker("Greg", "12 x 5 = 60", "Bears hibernate during the winter.", 70, 15, 12);
        Simpleton simpleton3 = new ContractWorker("Reggie", "10 / 2 = 5", "Jupiter is big.", 84, 100, 200.0);
        Simpleton simpleton4 = new ContractWorker("Valerie", "48 / 6 = 8", "Mercury is the closest planet to the Sun.", 102, 15, 120);
        Simpleton simpleton5 = new Hobbit("Bjorn", "1 + 5 = 6", "Flowers grow in soil.", 50);
        Simpleton simpleton6 = new Hobbit("Greta", "14 + 15 = 29", "Plants need water to grow.", 115);
        list.add(simpleton1);
        list.add(simpleton2);
        list.add(simpleton3);
        list.add(simpleton4);
        list.add(simpleton5);
        list.add(simpleton6);
    }

    /**
     * Method used to create a new HourlyWorker and add them to the list.
     * @param name used to initialize the person's name
     * @param math used to initialize the person's math problem
     * @param saying used to initialize the person's saying
     * @param iq used to initialize the person's IQ by passing it to HourlyWorker's super class which acts as the constructor for Smarty
     * @param hours used to initialize the HourlyWorker's hours worked
     * @param wages used to initialize the HourlyWorker's wages per hour
     */
    public void createNewHourlyWorker(String name, String math, String saying, int iq, int hours, double wages) {
        Simpleton simpleton = new HourlyWorker(name, math, saying, iq, hours, wages);
        list.add(simpleton);
    }

    /**
     * Method used to create a new ContractWorker and add them to the list.
     * @param name used to initialize the person's name
     * @param math used to initialize the person's math problem
     * @param saying used to initialize the person's saying
     * @param iq used to initialize the person's IQ by passing it to HourlyWorker's super class which acts as the constructor for Smarty
     * @param contracts used to initialize the ContractWorker's contracts completed
     * @param pay used to initialize the ContractWorker's pay per contract
     */
    public void createNewContractWorker(String name, String math, String saying, int iq, int contracts, double pay) {
        Simpleton simpleton = new ContractWorker(name, math, saying, iq, contracts, pay);
        list.add(simpleton);
    }

    /**
     * Method used to create a new Hobbit and add them to the list.
     * @param name used to initialize the person's name
     * @param math used to initialize the person's math problem
     * @param saying used to initialize the person's saying
     * @param carrots used to initialize the Hobbit's picked carrots
     */
    public void createNewHobbit(String name, String math, String saying, int carrots){
        Simpleton simpleton = new Hobbit(name, math, saying, carrots);
        list.add(simpleton);
    }

    /**
     * A method used to print a person's name, personType, and the math they can do for each person that can do math
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printMath(FlowPane flowPane) {
        text.setText("");
        for (Simpleton s : list) {
            text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ", I can do this math: " + s.doMath() + ". \n");
        }
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and saying for each person that has something smart to say
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printSayings(FlowPane flowPane) {
        text.setText("");
        for (Simpleton s : list) {
            text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": \"" + s.saySomethingSmart() + "\" \n");
        }
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and income for each person that has income
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printIncome(FlowPane flowPane) {
        text.setText("");
        for (Simpleton s : list) {
            if (s instanceof HourlyWorker) {
                text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": $" + ((HourlyWorker) s).getIncome() + " is my income. \n");
            } else if (s instanceof ContractWorker) {
                text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": $" + ((ContractWorker) s).getIncome() + " is my income. \n");
            }
        }
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and the hours they have worked for each person that works by the hour
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printHours(FlowPane flowPane) {
        text.setText("");
        list.stream().filter(s -> s instanceof HourlyWorker).forEach(s -> text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": " + ((HourlyWorker) s).getHoursWorked() + " hours worked. \n"));
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and IQ for each person that has an IQ.
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printIQ(FlowPane flowPane) {
        text.setText("");
        for (Simpleton s : list) {
            if (s instanceof HourlyWorker) {
                text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": " + ((HourlyWorker) s).getIQ() + " is my IQ. \n");
            } else if (s instanceof ContractWorker) {
                text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": " + ((ContractWorker) s).getIQ() + " is my IQ. \n");
            }
        }
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and the contracts they ave completed for each person that has contracts
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printContracts(FlowPane flowPane) {
        text.setText("");
        list.stream().filter(s -> s instanceof ContractWorker).forEach(s -> text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": " + ((ContractWorker) s).getContractsCompleted() + " contracts completed. \n"));
        flowPane.getChildren().add(text);
    }

    /**
     * A method used to print a person's name, personType, and the carrots they have picked for each person that can pick carrots
     * @param flowPane FlowPane with which is used to add the text that has each person's information
     */
    public void printCarrots(FlowPane flowPane) {
        text.setText("");
        list.stream().filter(s -> s instanceof Hobbit).forEach(s -> text.setText(text.getText() + s.getName() + ", " + s.getPersonType() + ": " + ((Hobbit) s).getCarrotsPicked() + " carrots picked. \n"));
        flowPane.getChildren().add(text);
    }
}
