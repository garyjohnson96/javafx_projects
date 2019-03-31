package cs2410.assn4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * View Class used to interact with a Stage object and display images by using buttons to manipulate an Image Viewer.
 * @author Gary Johnson
 * @version 1.0
 */
public class View extends Application implements EventHandler<ActionEvent> {
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

    /**
     * A Controller object used to access the Controller class to use the Image data
     */
    private Controller controller;

    /**
     * A private integer used to keep track of the current index of the ArrayList
     *
     */
    private int currIndex;

    /**
     * A private ImageView used to view the current image of the ArrayList
     */
    private ImageView imgView;

    /**
     * A private Button used to access the addImage method in the Controller
     */
    private Button btnAdd;

    /**
     * A private Button used to access the delImage method in the Controller
     */
    private Button btnDel;

    /**
     * A private Button used to access the showNextImage method in the Controller
     */
    private Button btnNext;

    /**
     * A private Button used to access the showPrevImage method in the Controller
     */
    private Button btnPrev;

    /**
     * A private Pane used to hold the buttons and the image view
     */
    private Pane pane;

    /**
     * A private Scene used to hold the Pane and set the size of the window
     */
    private Scene scene;

    /**
     * Method: Create a GUI and interact with the data in the Controller
     * @param primaryStage //A Stage object that shows the scene
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Control
        controller = new Controller("data/images.data");

        currIndex = 0; //Initialized to zero because zero is the first index of an ArrayList

        //Image View
        imgView = new ImageView();
        checkIfEmpty();  //Checks if the ArrayList is empty to decide what to display and if the buttons should be disabled
        //Sets the size of the image
        imgView.setFitWidth(350);
        imgView.setFitHeight(350);
        imgView.setTranslateY(20); //Sets the Y location of the image
        //Sets the appearance of the image
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);

        //Buttons
        btnAdd = new Button("Add");
        btnAdd.setLayoutY(400);
        btnAdd.setLayoutX(207);
        btnAdd.setOnAction(this);  //Calls the handle method when this button is clicked

        btnDel = new Button("Del");
        btnDel.setLayoutX(250);
        btnDel.setLayoutY(400);
        btnDel.setOnAction(this);  //Calls the handle method when this button is clicked

        btnNext = new Button("Next");
        btnNext.setLayoutX(288);
        btnNext.setLayoutY(400);
        btnNext.setOnAction(this); //Calls the handle method when this button is clicked

        btnPrev = new Button("Prev");
        btnPrev.setLayoutY(400);
        btnPrev.setLayoutX(162);
        btnPrev.setOnAction(this); //Calls the handle method when this button is clicked

        //ArrayList is empty if checkIfEmpty() == true
        if (checkIfEmpty()){  //Disables the del, next, and prev buttons if the ArrayList is empty
            btnDel.setDisable(true);
            btnPrev.setDisable(true);
            btnNext.setDisable(true);
        }

        //Pane
        pane = new Pane();
        //Adds the Image View and all the buttons to the pane
        pane.getChildren().add(imgView);
        pane.getChildren().add(btnAdd);
        pane.getChildren().add(btnDel);
        pane.getChildren().add(btnNext);
        pane.getChildren().add(btnPrev);

        //Scene
        scene = new Scene(pane, 500, 500);

        //Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Super Lame Image Viewer");
        primaryStage.setResizable(false);  //Makes it so the GUI cannot be resized
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {  //Executes methods when the 'X' button on the Stage is clicked

            /**
             * Method: Save the ArrayList to the original file and quit.
             * @param event
             */
            @Override
            public void handle(WindowEvent event) {
                controller.saveList(); //Saves the ArrayList to the original file
                controller.quit();  //Exits the program
            }
        });
    }

    /**
     * Method: Check which button was clicked and execute the appropriate action.
     * @param event //Event that holds the button that was pressed
     */
    @Override
    public void handle(ActionEvent event){
        int lastIndex = controller.images.size() - 1;  //Index limiter for cycling through the images
                                                       //Keeps the index from going out of bounds past the last index
        if (event.getSource() == btnAdd){  //If "Add" was clicked
            String URL;  //Holds the user's response
            TextInputDialog input = new TextInputDialog();  //Input Dialog for the user to enter a response
            input.setTitle("Add Image");
            input.setHeaderText(null);
            input.setContentText("Enter an image URL");
            URL = input.showAndWait().get();  //Gets the response from the user and saves it to URL
            imgView.setImage(controller.addImage(URL, currIndex));  //Creates a new image with the URL provided by the user, adds it to the ArrayList, and sets the Image View to that image
            imgView.setTranslateX(110); //Sets the X location of the image
            //Enables all the buttons because the ArrayList is no longer empty in case it was before.
            btnNext.setDisable(false);
            btnPrev.setDisable(false);
            btnDel.setDisable(false);
        }
        else if (event.getSource() == btnDel){  //If "Del" was clicked
            imgView.setImage(controller.deleteImage(currIndex));  //Deletes the current image and displays the next image
            currIndex--;  //An image was removed from the ArrayList, so the index needs to shrink to remain within the bounds of the smaller ArrayList
            if (currIndex < 0){  //Resets the Index to the end of the ArrayList if it goes below zero
                currIndex = 0;
            }
            checkIfEmpty();  //Checks if that delete emptied the ArrayList
            //If the ArrayList is empty, then disabled is set to true, and the buttons become disabled.
            //Otherwise, disabled remains false, and the buttons remain enabled.
            btnDel.setDisable(controller.disabled);
            btnPrev.setDisable(controller.disabled);
            btnNext.setDisable(controller.disabled);
        }
        else if (event.getSource() == btnNext){  //If "Next" was clicked
            currIndex++;  //Moves the index to the right to access the next image
            if (currIndex > lastIndex) {  //Resets the index to zero if it goes over index limit of the ArrayList
                currIndex = 0;
            }
            imgView.setImage(controller.showNextImage(currIndex));  //Displays the image to the right of the current image
        }
        else if (event.getSource() == btnPrev){  //If "Prev" was clicked
            currIndex--;  //Moves the index to the left to access the previous image
            if (currIndex < 0) {  //Resets the index to the final index if it goes below zero
                currIndex = lastIndex;
            }
            imgView.setImage(controller.showPrevImage(currIndex));  //Displays the image to the left of the current image
        }
    }

    /**
     * Method: Check if the ArrayList is empty and display the appropriate image.
     *         Display the image from the first URL if it is not.
     *         Or, display a "No Image" message if it is.
     * @return true if the ArrayList is empty or false if it isn't.
     */
    public boolean checkIfEmpty() {
        if (controller.images.isEmpty() == false) {  //The ArrayList is not empty
            Image firstImage = new Image(controller.images.get(0)); //Gets the URL at the beginning of the ArrayList
            imgView.setImage(firstImage); //Displays the URL at the beginning of the ArrayList
            imgView.setTranslateX(110); //Sets the X location of the image
            return false;
        }
        else { //The ArrayList is empty
            Image firstImage = new Image("http://www.waahindia.com/discount/client/images/noimage.png");  //"No Image" Message
            imgView.setImage(firstImage); //Displays the "No Image" message
            imgView.setTranslateX(75); //Sets the X location of the image
            return true;
        }
    }
}