package cs2410.assn6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * An improved drawing tablet program that draws, erases, colors, and moves around ellipses, rectangles, and paths
 * @author Gary Johnson
 * @version 1.0
 */
public class ImprovedDrawingTablet extends Application {
    /**
     * A private ToolPane that holds all the buttons and color pickers that the user can interact with
     */
    private ToolPane tPane;
    /**
     * A private Pane that stores the resizable clip where the shapes are drawn
     * and all the event handlers to draw the shapes
     */
    private Pane drawPane;
    /**
     * A private ellipse that the user can draw, erase, move around, and color
     */
    private Ellipse ellipse;
    /**
     * A private rectangle that the user can draw, erase, move, and color
     */
    private Rectangle rect;
    /**
     * A private path that the user can draw, erase, move, and color
     */
    private Path path;
    /**
     * A private object that lets the user move a shape
     */
    private ShapeDrag drag;
    /**
     * A private double that stores the x location of where the user clicked on the pane
     */
    private double firstX;
    /**
     * A private double that stores the y location of where the user clicked on the pane
     */
    private double firstY;
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
        tPane = new ToolPane();
        tPane.setPrefHeight(50);
        tPane.setAlignment(Pos.CENTER);
        drawPane = new Pane();
        Rectangle clip = new Rectangle();
        clip.heightProperty().bind(drawPane.heightProperty());
        clip.widthProperty().bind(drawPane.widthProperty());
        drawPane.setClip(clip);
        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {  //If the mouse is pressed on the drawPane

            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             * <p>
             *     This handle method is invoked when the mouse is pressed anywhere on the drawPane
             * </p>
             *
             * @param event the mouse being pressed
             */
            @Override
            public void handle(MouseEvent event) {
                firstX = event.getX();  //Saves the X location of where the mouse was clicked
                firstY = event.getY();  //Saves the Y location of where the mouse was clicked
                drag = new ShapeDrag(); //Creates a ShapeDrag object to allow shapes to be dragged
                if (tPane.ellBtnSelected()) {  //If the user can draw
                    ellipse = new Ellipse(event.getX(), event.getY(), 0, 0); //Creates a new ellipse that starts where the user clicked
                    drawPane.getChildren().add(ellipse);
                    ellipse.setFill(tPane.getFillPickerValue());
                    ellipse.setStroke(tPane.getStrokePickerValue());
                    ellipse.setStrokeWidth(tPane.getStrokeSizeValue());
                    ellipse.setOnMousePressed(new EventHandler<MouseEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when the mouse is pressed on any ellipse
                         * </p>
                         *
                         * @param event the mouse being pressed
                         */
                        @Override
                        public void handle(MouseEvent event) {
                            if (tPane.editBtnSelected()) {
                                tPane.setFillPickerValue((Color)((Ellipse) event.getSource()).getFill());
                                tPane.setStrokePickerValue((Color)((Ellipse) event.getSource()).getStroke());
                                tPane.setStrokeSizeValue((int) ((Ellipse) event.getSource()).getStrokeWidth());
                            }
                        }
                    });
                } else if (tPane.rectBtnSelected()) {
                    rect = new Rectangle(event.getX(), event.getY(), 0, 0);
                    drawPane.getChildren().add(rect);
                    rect.setFill(tPane.getFillPickerValue());
                    rect.setStroke(tPane.getStrokePickerValue());
                    rect.setStrokeWidth(tPane.getStrokeSizeValue());
                    rect.setOnMousePressed(new EventHandler<MouseEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when the mouse is pressed on any rectangle
                         * </p>
                         *
                         * @param event the mouse being pressed
                         */
                        @Override
                        public void handle(MouseEvent event) {
                            if (tPane.editBtnSelected()) {
                                tPane.setFillPickerValue((Color)((Rectangle) event.getSource()).getFill());
                                tPane.setStrokePickerValue((Color)((Rectangle) event.getSource()).getStroke());
                                tPane.setStrokeSizeValue((int) ((Rectangle) event.getSource()).getStrokeWidth());
                            }
                        }
                    });
                } else if (tPane.freeBtnSelected()) {
                    path = new Path();
                    drawPane.getChildren().add(path);
                    path.setStroke(tPane.getStrokePickerValue());
                    path.setStrokeWidth(tPane.getStrokeSizeValue());
                    path.getElements().add(new MoveTo(event.getX(), event.getY()));
                    path.setOnMousePressed(new EventHandler<MouseEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when the mouse is pressed on any path
                         * </p>
                         *
                         * @param event the mouse being pressed
                         */
                        @Override
                        public void handle(MouseEvent event) {
                            if (tPane.editBtnSelected()) {
                                tPane.setStrokePickerValue((Color) ((Path) event.getSource()).getStroke());
                                tPane.setStrokeSizeValue((int) ((Path) event.getSource()).getStrokeWidth());
                            }
                        }
                    });
                } else if (tPane.eraseBtnSelected()) {  //If the user can erase
                    //System.out.println("Erasing!");
                    drawPane.getChildren().remove(event.getTarget());  //Erase the shape by removing it from the drawPane
                } else if (tPane.editBtnSelected()) {
                    //System.out.println("Event X:" + event.getX());
                    //System.out.println("Event Y:" + event.getY());
                    drag.setShapeHandler((Shape) event.getTarget(), tPane);
                    Shape shape = (Shape) event.getTarget();
                    //System.out.println(shape);
                    tPane.setFillPickerAction(new EventHandler<ActionEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when a color is picked from the fill picker
                         *     while the edit button is pressed and a shape is selected. It allows the selected shape's
                         *     fill color to be changed to whatever is selected in the fill picker.
                         * </p>
                         *
                         * @param event a fill color being selected in edit mode when a shape is selected
                         */
                        @Override
                        public void handle(ActionEvent event) {
                            //if (shape == ellipse || shape == rect) {
                            shape.setFill(tPane.getFillPickerValue());
                            //}
                        }
                    });
                    tPane.setStrokePickerAction(new EventHandler<ActionEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when a color is picked from the stroke picker
                         *     while the edit button is pressed and a shape is selected. It allows the selected shape's
                         *     stroke color to be changed to whatever is selected in the stroke picker.
                         * </p>
                         *
                         * @param event a stroke color being selected in edit mode when a shape is selected
                         */
                        @Override
                        public void handle(ActionEvent event) {
                            shape.setStroke(tPane.getStrokePickerValue());
                        }
                    });
                    tPane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
                        /**
                         * Invoked when a specific event of the type for which this handler is
                         * registered happens.
                         * <p>
                         *     This handle method is invoked when a stroke size is picked from the stroke size combo box
                         *     while the edit button is pressed and a shape is selected. It allows the selected shape's
                         *     stroke size to be changed to whatever is selected in the stroke size combo box.
                         * </p>
                         *
                         * @param event a stroke size being selected in edit mode when a shape is selected
                         */
                        @Override
                        public void handle(ActionEvent event) {
                            shape.setStrokeWidth(tPane.getStrokeSizeValue());
                        }
                    });
                }
            }
        });

        drawPane.setOnDragDetected(new EventHandler<MouseEvent>() { //If the mouse is starting to be dragged after it has been clicked
            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             * <p>
             *     This handle method is invoked when a drag is detected
             * </p>
             *
             * @param event the mouse being dragged
             */
            @Override
            public void handle(MouseEvent event) {
                drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {  //If the mouse is dragged

                    /**
                     * Invoked when a specific event of the type for which this handler is
                     * registered happens.
                     * <p>
                     *     This handle method is invoked when the mouse is dragged anywhere inside the drawPane.
                     *     It allows the specified shape to be resized to wherever the cursor is being dragged.
                     * </p>
                     *
                     * @param event the mouse being dragged
                     */
                    @Override
                    public void handle(MouseEvent event) {
                        if (tPane.ellBtnSelected()) {
                            ellipse.setCenterX((firstX + event.getX()) / 2);
                            ellipse.setCenterY((firstY + event.getY()) / 2);
                            ellipse.setRadiusX(Math.abs(firstX - event.getX()) / 2);
                            ellipse.setRadiusY(Math.abs(firstY - event.getY()) / 2);

                        } else if (tPane.rectBtnSelected()) {
                            rect.setY(Math.min(firstY, event.getY()));
                            rect.setX(Math.min(firstX, event.getX()));
                            rect.setHeight(Math.abs(firstY - event.getY()));
                            rect.setWidth(Math.abs(firstX - event.getX()));
                        } else if (tPane.freeBtnSelected()) {
                            path.getElements().add(new LineTo(event.getX(), event.getY()));
                        }
                    }
                });
            }
        });

        drawPane.setOnMouseReleased(new EventHandler<MouseEvent>() {  //When the mouse is released after it was pressed

            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             * <p>
             *     This handle method is invoked when the mouse is released
             * </p>
             *
             * @param event the mouse being released
             */
            @Override
            public void handle(MouseEvent event) {
                if (tPane.ellBtnSelected()) {
                    //System.out.println("Ellipse!");
                    ellipse = null;
                } else if (tPane.rectBtnSelected()) {
                    //System.out.println("Rectangle!");
                    rect = null;
                } else if (tPane.freeBtnSelected()) {
                    //System.out.println("Free Hand!");
                    path = null;
                }
            }
        });

        BorderPane rootPane = new BorderPane(drawPane, tPane, null, null, null);// Stores drawPane and tPane, so they can both be added to the scene
        Scene drawTablet = new Scene(rootPane, 550, 550);// Stores rootPane and is added to the Stage
        primaryStage.setScene(drawTablet);
        primaryStage.setTitle("Improved Drawing Tablet");
        primaryStage.show();
    }
}

