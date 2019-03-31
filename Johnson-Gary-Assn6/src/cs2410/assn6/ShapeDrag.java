package cs2410.assn6;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 * Used for dragging around and dropping shapes in a Pane
 * @author Gary Johnson
 * @version 1.0
 */
class ShapeDrag {
    /**
     * A private double used for storing the X coordinate of where the mouse is clicked
     */
    private double startX;
    /**
     * A private double used for storing the Y coordinate of where teh mouse was clicked
     */
    private double startY;

    /**
     * Sets an event handler for any shape to allow them to be dragged around as long as the tablet is in edit mode.
     * @param shape Any shape that needs to be able to be moved around
     * @param toolPane A toolPane to provide access to which button is currently being pressed
     */
    void setShapeHandler(Shape shape, ToolPane toolPane) {
        shape.setOnMousePressed(new EventHandler<MouseEvent>() {
            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             * <p>
             *     This handle method is invoked when the mouse is pressed on a shape in the drawPane.
             *     It checks if the edit button is being pressed before anything else happens to ensure that
             *     the event only happens in edit mode.
             *     If the edit button is being pressed, it copies the x and y locations of where the mouse is
             *     upon being pressed. Otherwise, it does nothing.
             * </p>
             *
             * @param event the mouse being pressed
             */
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.editBtnSelected()) {
                    startX = event.getX();
                    startY = event.getY();
                }
            }
        });

        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             * <p>
             *     This handle method is invoked when the mouse is dragged while it is also pressed
             *     on a shape. If the edit button is being pressed, the shape is translated, so it can move around drawPane.
             *     Otherwise, it does nothing.
             * </p>
             *
             * @param event the mouse being dragged
             */
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.editBtnSelected()) {
                    if (startX == 0) {
                        startX = event.getX();
                    }
                    if (startY == 0) {
                        startY = event.getY();
                    }
                    shape.setTranslateX(shape.getTranslateX() + event.getX() - startX);
                    shape.setTranslateY(shape.getTranslateY() + event.getY() - startY);
                }
            }
        });
    }
}