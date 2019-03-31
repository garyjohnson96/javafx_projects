package view;

import control.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * Class used to run the application, display the information about all the people,
 * and add new people.
 * @author Gary Johnson
 * @version 1.0
 */
public class Display extends Application{

    /**
     * A private ToolBar that holds all the buttons and a ComboBox to interact with
     */
    private ToolBar toolBar;

    /**
     * A private Controller used to print people's information and add new people to the list
     */
    private Controller m_Controller;

    /**
     * A private AnchorPane used to enter information about a new HourlyWorker and anchor the save and cancel buttons
     */
    private AnchorPane addHourlyWorkerPane;

    /**
     * A private AnchorPane used to enter information about a new ContractWorker and anchor the save and cancel buttons
     */
    private AnchorPane addContractWorkerPane;

    /**
     * A private AnchorPane used to enter information about a new Hobbit and anchor the save and cancel buttons
     */
    private AnchorPane addHobbitPane;

    /**
     * A private StackPane used to display information about the people in the list
     */
    private StackPane displayPane;

    /**
     * A private Button that adds a new person to the list
     */
    private Button saveBtn;

    /**
     * A private Button that clears all the text fields
     */
    private Button cancelBtn;

    /**
     * A private TextField used to enter a new person's name
     */
    private TextField nameField;

    /**
     * A private TextField used to enter a new person's math problem
     */
    private TextField mathField;

    /**
     * A private TextField used to enter a new person's saying
     */
    private TextField sayField;

    /**
     * A private TextField used to enter a new person's IQ
     */
    private TextField iqField;

    /**
     * A private TextField used to enter a new person's hours worked
     */
    private TextField hoursField;

    /**
     * A private TextField used to enter a new person's wage per hour
     */
    private TextField wageField;

    /**
     * A private TextField used to enter a new person's contract completed
     */
    private TextField contractsField;

    /**
     * A private TextField used to enter a new person's pay per contract
     */
    private TextField payField;

    /**
     * A private TextField used to enter a new person's carrots picked
     */
    private TextField carrotsField;

    /**
     * A private String used to store a new person's name
     */
    private String m_Name;

    /**
     * A private String used to store a new person's math problem
     */
    private String m_Math;

    /**
     * A private String used to store a new person's saying
     */
    private String m_Saying;

    /**
     * A private String used to store a new person's IQ
     */
    private int m_IQ;

    /**
     * A private String used to store a new person's hours worked
     */
    private int m_Hours;

    /**
     * A private String used to store a new person's wage per hour
     */
    private double m_Wage;

    /**
     * A private String used to store a new person's contracts completed
     */
    private int m_Contracts;

    /**
     * A private String used to store a new person's pay per contract
     */
    private double m_Pay;

    /**
     * A private String used to store a new person's carrots picked
     */
    private int m_Carrots;

    /**
     * A private boolean used to check if the HourlyBtn was clicked
     */
    private boolean m_Hourly;

    /**
     * A private boolean used to check if the ContractBtn was clicked
     */
    private boolean m_Contract;

    /**
     * A private boolean used to check if the HobbitBtn was clicked
     */
    private boolean m_Hobbit;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        m_Controller = new Controller();

        m_Name = "";
        m_Math = "";
        m_Saying = "";
        m_IQ = 0;
        m_Hours = 0;
        m_Wage = 0.0;
        m_Contracts = 0;
        m_Pay = 0.0;
        m_Carrots = 0;

        m_Hourly = false;
        m_Contract = false;
        m_Hobbit = false;

        saveBtn = new Button("Save");
        saveBtn.setFont(Font.font(15));
        cancelBtn = new Button("Cancel");
        cancelBtn.setFont(Font.font(15));

        BorderPane rootPane = new BorderPane();

        toolBar = new ToolBar();
        toolBar.selection.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event selection being made
             */
            @Override
            public void handle(ActionEvent event) {
                displayPane = new StackPane();
                toolBar.select(displayPane, m_Controller);
                rootPane.setCenter(displayPane);
            }
        });
        toolBar.hourlyBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event hourlyBtn being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                m_Hourly = true;
                m_Contract = false;
                m_Hobbit = false;
                addHourlyWorkerPane = new AnchorPane();
                setDisplay(addHourlyWorkerPane);
                rootPane.setCenter(addHourlyWorkerPane);
            }
        });
        toolBar.contractBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event contractBtn being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                m_Hourly = false;
                m_Contract = true;
                m_Hobbit = false;
                addContractWorkerPane = new AnchorPane();
                setDisplay(addContractWorkerPane);
                rootPane.setCenter(addContractWorkerPane);
            }
        });
        toolBar.hobbitBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event hobbitBtn being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                m_Hourly = false;
                m_Contract = false;
                m_Hobbit = true;
                addHobbitPane = new AnchorPane();
                setDisplay(addHobbitPane);
                rootPane.setCenter(addHobbitPane);
            }
        });

        rootPane.setTop(toolBar);

        Scene scene = new Scene(rootPane, 410, 325);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Polyabstraheritance");
        primaryStage.show();
    }

    /**
     * @param pane
     */
    private void setLabels(AnchorPane pane) {
        Label name = new Label("Name: ");
        name.setFont(Font.font(15));
        name.setLayoutX(20);
        name.setLayoutY(5);

        Label math = new Label("Math: ");
        math.setFont(Font.font(15));
        math.setLayoutX(20);
        math.setLayoutY(45);

        Label saying = new Label("Saying: ");
        saying.setFont(Font.font(15));
        saying.setLayoutX(20);
        saying.setLayoutY(85);

        pane.getChildren().addAll(name, math, saying);

        if (m_Hourly || m_Contract) {
            Label iq = new Label("IQ: ");
            iq.setFont(Font.font(15));
            iq.setLayoutX(20);
            iq.setLayoutY(125);

            pane.getChildren().add(iq);
        }

        if (m_Hourly) {
            Label hours = new Label("Hours: ");
            hours.setFont(Font.font(15));
            hours.setLayoutX(20);
            hours.setLayoutY(165);

            Label wage = new Label("Wage: ");
            wage.setFont(Font.font(15));
            wage.setLayoutX(20);
            wage.setLayoutY(205);

            pane.getChildren().addAll(hours, wage);
        } else if (m_Contract) {
            Label contracts = new Label("Contracts: ");
            contracts.setFont(Font.font(15));
            contracts.setLayoutX(20);
            contracts.setLayoutY(165);

            Label pay = new Label("Pay Per Contract: ");
            pay.setFont(Font.font(15));
            pay.setLayoutX(20);
            pay.setLayoutY(205);

            pane.getChildren().addAll(contracts, pay);
        } else if (m_Hobbit) {
            Label carrots = new Label("Carrots: ");
            carrots.setFont(Font.font(15));
            carrots.setLayoutX(20);
            carrots.setLayoutY(125);

            pane.getChildren().add(carrots);
        }
    }

    /**
     * @param pane
     */
    private void setTextFields(AnchorPane pane) {
        if (m_Hourly || m_Hobbit) {
            nameField = new TextField();
            nameField.setLayoutX(100);
            nameField.setLayoutY(2);
            nameField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Name = nameField.getText();
                    System.out.println(m_Name);
                }
            });
            mathField = new TextField();
            mathField.setLayoutX(100);
            mathField.setLayoutY(42);
            mathField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Math = mathField.getText();
                    System.out.println(m_Math);
                }
            });
            sayField = new TextField();
            sayField.setLayoutX(100);
            sayField.setLayoutY(82);
            sayField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Saying = sayField.getText();
                    System.out.println(m_Saying);
                }
            });
            pane.getChildren().addAll(nameField, mathField, sayField);
        }
        if (m_Hourly) {
            iqField = new TextField();
            iqField.setLayoutX(100);
            iqField.setLayoutY(122);
            iqField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_IQ = Integer.parseInt(iqField.getText());
                    System.out.println(m_IQ);
                }
            });
            hoursField = new TextField();
            hoursField.setLayoutX(100);
            hoursField.setLayoutY(162);
            hoursField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Hours = Integer.parseInt(hoursField.getText());
                    System.out.println(m_Hours);
                }
            });
            wageField = new TextField();
            wageField.setLayoutX(100);
            wageField.setLayoutY(202);
            wageField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Wage = Double.parseDouble(wageField.getText());
                    System.out.println(m_Wage);
                }
            });
            pane.getChildren().addAll(iqField, hoursField, wageField);

        } else if (m_Contract) {
            nameField = new TextField();
            nameField.setLayoutX(150);
            nameField.setLayoutY(2);
            nameField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Name = nameField.getText();
                    System.out.println(m_Name);
                }
            });
            mathField = new TextField();
            mathField.setLayoutX(150);
            mathField.setLayoutY(42);
            mathField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Math = mathField.getText();
                    System.out.println(m_Math);
                }
            });
            sayField = new TextField();
            sayField.setLayoutX(150);
            sayField.setLayoutY(82);
            sayField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Saying = sayField.getText();
                    System.out.println(m_Saying);
                }
            });
            iqField = new TextField();
            iqField.setLayoutX(150);
            iqField.setLayoutY(122);
            iqField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_IQ =  Integer.parseInt(iqField.getText());
                    System.out.println(m_IQ);
                }
            });
            contractsField = new TextField();
            contractsField.setLayoutX(150);
            contractsField.setLayoutY(162);
            contractsField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Contracts = Integer.parseInt(contractsField.getText());
                    System.out.println(m_Contracts);
                }
            });
            payField = new TextField();
            payField.setLayoutX(150);
            payField.setLayoutY(202);
            payField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Pay = Double.parseDouble(payField.getText());
                    System.out.println(m_Pay);
                }
            });
            pane.getChildren().addAll(nameField, mathField, sayField, iqField, contractsField, payField);

        } else if (m_Hobbit) {
            carrotsField = new TextField();
            carrotsField.setLayoutX(100);
            carrotsField.setLayoutY(122);
            carrotsField.setOnAction(new EventHandler<ActionEvent>() {
                /**
                 * @param event
                 */
                @Override
                public void handle(ActionEvent event) {
                    m_Carrots = Integer.parseInt(carrotsField.getText());
                }
            });
            pane.getChildren().add(carrotsField);
        }
    }

    /**
     *
     */
    private void clearTextFields() {
        nameField.clear();
        mathField.clear();
        sayField.clear();
        if (m_Hourly || m_Contract) {
            iqField.clear();
        }
        if (m_Hourly) {
            hoursField.clear();
            wageField.clear();
        } else if (m_Contract) {
            contractsField.clear();
            payField.clear();
        } else if (m_Hobbit) {
            carrotsField.clear();
        }
    }

    /**
     * @param pane
     */
    private void setAnchorButtons(AnchorPane pane) {
        FlowPane buttonPane = new FlowPane(cancelBtn, saveBtn);
        buttonPane.setPadding(new Insets(0, 10, 10, 0));
        buttonPane.setHgap(10);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                addPerson();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("You added " + m_Name);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    clearTextFields();
                }
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                clearTextFields();
            }
        });
        pane.getChildren().add(buttonPane);

        AnchorPane.setBottomAnchor(buttonPane, 0.0);
        AnchorPane.setRightAnchor(buttonPane, 0.0);
    }

    /**
     * @param
     */
    private void addPerson() {
        if (m_Hourly) {
            m_Controller.createNewHourlyWorker(m_Name, m_Math, m_Saying, m_IQ, m_Hours, m_Wage);
        } else if (m_Contract) {
            m_Controller.createNewContractWorker(m_Name, m_Math, m_Saying, m_IQ, m_Contracts, m_Pay);
        } else if (m_Hobbit) {
            m_Controller.createNewHobbit(m_Name, m_Math, m_Saying, m_Carrots);
        }
    }

    /**
     * @param pane
     */
    private void setDisplay(AnchorPane pane) {
        setLabels(pane);
        setTextFields(pane);
        setAnchorButtons(pane);
    }
}