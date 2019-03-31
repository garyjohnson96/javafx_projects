package view;

import control.Controller;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.Objects;

/**
 * A public class that inherits Button to have all of the functionality of Button.
 * Styled as blue squares that change colors based on how they are interacted with.
 * A Cell can either have a bomb or not have a bomb and me marked as such
 * A Cell with no bomb can be cleared, and a cleared Cell displays the number of bombs surrounding it.
 * @author Gary Johnson
 */
public class Cell extends Button {

    /**
     * A private boolean that keeps track of whether or not a cell has a bomb.
     */
    private boolean m_Bomb;

    /**
     * A private int that stores the number of bombs a cell has around it.
     */
    private int m_NeighborBombs;

    /**
     * A private int that keeps track of if the cell that's clicked on is the first cell.
     */
    private int m_FirstCell;

    /**
     * A private boolean that keeps track of whether or not a cell has been cleared.
     */
    private boolean m_Cleared;

    /**
     * A private int that stores a cell's x coordinate in the game grid.
     */
    private int m_X;

    /**
     * A private int that stores a cell's y coordinate in the game grid.
     */
    private int m_Y;

    /**
     * Default Constructor that initializes every member of Cell.
     * It makes each cell start out as not having a bomb, not cleared, and having no neighbor bombs.
     * It also sets the size and initial color of each cell.
     */
    public Cell() {
        m_Bomb = false;
        m_Cleared = false;
        m_FirstCell = 0;
        m_NeighborBombs = 0;
        m_X = 0;
        m_Y = 0;
        super.setMinWidth(25);
        super.setMinHeight(25);
        super.setMaxWidth(25);
        super.setMaxHeight(25);
        if (!isCleared()) {
            super.setStyle("-fx-background-color: cornflowerblue; -fx-border-color: black; -fx-border-width: 1px");
        }
    }

    /**
     * A public method that sets a Cell's m_Bomb value to true.
     */
    public void giveBomb() {
        m_Bomb = true;
    }

    /**
     * A public boolean method that determines if a cell has a bomb.
     * @return true if the cell has a bomb or false if it does not.
     */
    public boolean hasBomb() { return m_Bomb; }

    /**
     * A package-private method that increments the number of bombs a cell has neighboring it.
     */
    void addNeighborBomb() {
        m_NeighborBombs++;
        Cell.super.setText(String.valueOf(m_NeighborBombs));
    }

    /**
     * A package-private method that sets the number of bombs a cell has neighboring it.
     * @param bombs the number of bombs a cell has around it.
     */
    void setNeighborBombs(int bombs) {
        m_NeighborBombs = bombs;
    }

    /**
     * A package-private method that returns the number of bombs a cell has neighboring it.
     * @return the number of bombs a cell has surrounding it.
     */
    int getNeighborBombs() {
        return m_NeighborBombs;
    }

    /**
     * A package-private method that sets the x-coordinate of a cell.
     * @param x the x-coordinate given to a cell.
     */
    void setX(int x) {
        m_X = x;
    }

    /**
     * A package-private method that sets the y-coordinate of a cell.
     * @param y the y-coordinate given to a cell.
     */
    void setY(int y) {
        m_Y = y;
    }

    /**
     * A package-private boolean method that determines if a cell is cleared.
     * @return true if a cell has been cleared or false if it has not.
     */
    boolean isCleared() {
        return m_Cleared;
    }

    /**
     * A package-private method that clears a cell and recolors it to show it has been cleared.
     * @param controller used to increment the number of cleared cells.
     */
    void clearCell(Controller controller) {
        m_Cleared = true;
        Cell.super.setStyle("-fx-background-color: skyblue; -fx-border-color: black;");
        Cell.super.setText(String.valueOf(m_NeighborBombs));
        controller.addClearedCell();
    }

    /**
     * A public method that sets the cell actions that any non-cleared cell can perform:
     *   Entering a cell highlights it to indicate where the cursor is,
     *   Exiting a cell returns the cell to its original color.
     *   Clicking on a cell highlights the cell even lighter to give it the illusion of being pressed,
     *   Releasing the mouse's primary button either clears the cell or
     *             ends the game depending on if the cell is empty or has a bomb, respectively.
     *   Releasing the mouse's secondary button marks the cell as either a bomb ("x"), a possible bomb ("?"), or no bomb ("").
     * @param gameBar used to start the game when the first cell is clicked
     *                and changes the number of bombs displayed on the Game Bar based on what the cell is marked as.
     * @param controller used to set whether the user wins depending on if they successfully cleared every cell or clicked on a bomb
     *                   and to display the game over message when the game ends.
     */
    public void setCellActions(GameBar gameBar, Controller controller) {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            /**
             * @param event the mouse entering the cell
             */
            @Override
            public void handle(MouseEvent event) {
                if (!Minesweeperish.isGameOver()) { // || !Cell.super.isDisabled()) {
                    if (!isCleared()) {
                        Cell.super.setStyle("-fx-background-color: deepskyblue; -fx-border-color: black; -fx-border-width: 1px");
                    }
                }
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            /**
             * @param event the mouse exiting the cell
             */
            @Override
            public void handle(MouseEvent event) {
                if (!Minesweeperish.isGameOver()) { // || !Cell.super.isDisabled()) {
                    if (!isCleared()) {
                        Cell.super.setStyle("-fx-background-color: cornflowerblue; -fx-border-color: black; -fx-border-width: 1px");
                    }
                }
            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            /**
             * @param event the mouse pressing on the cell
             */
            @Override
            public void handle(MouseEvent event) {
                if (!Minesweeperish.isGameOver()) { // || !Cell.super.isDisabled()) {
                    if (!isCleared()) {
                        Cell.super.setStyle("-fx-background-color: aqua; -fx-border-color: black; -fx-border-width: 1px");
                    }
                }
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            /**
             * @param event the mouse releasing its click from the cell
             */
            @Override
            public void handle(MouseEvent event) {
                 if (!Minesweeperish.isGameOver()) {
                     if (!isCleared()) {
                         if (m_FirstCell == 0) {
                             gameBar.startGame(true);
                             m_FirstCell++;
                         }
                         if (event.getButton() == MouseButton.PRIMARY) {
                             if (Objects.equals(Cell.super.getText(), "")) {
                                 if (hasBomb()) {  // lose
                                     m_FirstCell = 0;
                                     controller.setWin(false);
                                     controller.gameOver(gameBar);
                                 } else {  // win
                                     Minesweeperish.clearCells(controller, m_X, m_Y);
                                     if (controller.allCleared()) {
                                         controller.setWin(true);
                                         controller.gameOver(gameBar);
                                     }
                                 }
                             }
                         } else if (event.getButton() == MouseButton.SECONDARY) {
                             Cell.super.setStyle("-fx-background-color: deepskyblue; -fx-border-color: black; -fx-border-width: 1px");
                             if (Objects.equals(Cell.super.getText(), "")) {
                                 Cell.super.setText("x");
                                 gameBar.subtractBombNum();
                             } else if (Objects.equals(Cell.super.getText(), "x")) {
                                 Cell.super.setText("?");
                                 gameBar.addBombNum(controller);
                             } else if (Objects.equals(Cell.super.getText(), "?")) {
                                 Cell.super.setText("");
                             }
                         }
                     }
                 }
            }

        });
    }
}