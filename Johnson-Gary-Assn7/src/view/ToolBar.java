package view;

import control.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.util.Objects;

/**
 * A Helper class that inherits HBox, so all of its contents will be laid out horizontally with the ability to be formatted.
 * It has a ComboBox that change the display and three Buttons for adding new people.
 * @author Gary Johnson
 * @version 1.0
 */
class ToolBar extends HBox {

    /**
     * A package-private ComboBox that hold the contents of the drop-down box
     */
    ComboBox<String> selection;

    /**
     * A package-private Button used to add a new hourly worker
     */
    Button hourlyBtn;

    /**
     * A package-private Button used to add a new contract worker
     */
    Button contractBtn;

    /**
     * A package-private Button used to add a new hobbit
     */
    Button hobbitBtn;

    /**
     * Default Constructor for class ToolBar that initializes the selection member variable, the Button variables,
     * adds the selection and Buttons to the ToolBar, and formats the Toolbar.
     */
    ToolBar() {
        selection = new ComboBox<>(FXCollections.observableArrayList("Math", "Income", "Hours", "IQ", "Say", "Carrots", "Contract"));

        hourlyBtn = new Button("Hourly");
        contractBtn = new Button("Contract");
        hobbitBtn = new Button("Hobbit");

        this.getChildren().addAll(selection, hourlyBtn, contractBtn, hobbitBtn);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(50);
    }

    /**
     * Method used to change the pane being displayed.
     * @param stackPane StackPane used to display the current selection
     * @param controller Controller used to access the print methods for each selection to be displayed
     */
    void select(StackPane stackPane, Controller controller) {
        FlowPane flowPane = new FlowPane();
        Text text = new Text();
        text.setText(selection.getValue());

        if (Objects.equals(text.getText(), "Math")) {
            controller.printMath(flowPane);
        } else if (Objects.equals(text.getText(), "Income")) {
            controller.printIncome(flowPane);
        } else if (Objects.equals(text.getText(), "Say")) {
            controller.printSayings(flowPane);
        } else if (Objects.equals(text.getText(), "IQ")) {
            controller.printIQ(flowPane);
        } else if (Objects.equals(text.getText(), "Hours")) {
            controller.printHours(flowPane);
        } else if (Objects.equals(text.getText(), "Contract")) {
            controller.printContracts(flowPane);
        } else if (Objects.equals(text.getText(), "Carrots")) {
            controller.printCarrots(flowPane);
        }

        flowPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(flowPane);
    }
}