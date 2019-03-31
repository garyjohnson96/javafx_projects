package view;

import control.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A public class that extends Application to run as a JavaFX Application.
 * This class displays all the information compiled from the other classes.
 * The display replicates Minesweeper and acts similarly to that game.
 * @author Gary Johnson
 */
public class Minesweeperish extends Application {

    /**
     * A private static 2-dimensional Cell Array that is populated by controller's cellList.
     */
    private static Cell m_GameCell[][];

    /**
     * A private static Controller used to fill the CellList with Cells, populate m_GameCell[][] using the cellList, and to initialize the m_GameBar.
     */
    private static Controller m_Controller;

    /**
     * A private static GameBar used to set a GameBar display at the top of the game window.
     */
    private static GameBar m_GameBar;

    /**
     * A private static GridPane that serves as the grid for the cells that the m_GameCell[][] fills out.
     */
    private static GridPane m_GameGrid;

    /**
     * A private static Alert that displays a message when tha game is over.
     */
    private static Alert m_Alert;

    /**
     * A private static Timer that increments a counter every second.
     */
    private static Timer m_Timer;

    /**
     * A private static int counter that gets incremented by m_Timer.
     */
    private static int m_Second;

    private static int m_Minute;

    /**
     * A private static boolean that keeps track of if the game is over or not.
     */
    private static boolean m_GameOver;

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
        m_GameOver = false;
        m_Timer = new Timer();
        m_Second = 0;
        m_Minute = 0;
        m_Alert = new Alert(Alert.AlertType.INFORMATION);
        m_Alert.setTitle("Game Over!");
        m_Alert.setHeaderText(null);

        m_Controller = new Controller();
        m_Controller.setCellsPerRow(20);
        m_Controller.setCellsPerColumn(20);
        m_Controller.setBombs(25.0);
        m_GameBar = new GameBar(m_Controller);
        m_Controller.fillList(m_GameBar);
        m_GameBar.setStartBtnText("Start");
        m_GameBar.setBombNum(m_Controller.getBombs());
        m_GameBar.setTime(m_Minute, m_Second);
        m_GameCell = new Cell[m_Controller.getCellsPerRow()][m_Controller.getCellsPerColumn()];
        m_GameGrid = new GridPane();
        m_GameGrid.setAlignment(Pos.CENTER);

        fillGrid();

        m_Timer.scheduleAtFixedRate(new TimerTask() {
            /**
             * Increments the m_Time counter by one every second and sets the time displayed in m_GameBar to the newly incremented m_Time.
             */
            @Override
            public void run() {
                if (m_GameBar.started()) {
                    m_Second++;
                    if (m_Second == 60) {
                        m_Minute++;
                        m_Second = 0;
                    }
                    m_GameBar.setTime(m_Minute, m_Second);
                }
            }
        }, 0, 1000);

        BorderPane gameBoard = new BorderPane(m_GameGrid, m_GameBar, null, null, null);
        gameBoard.getChildren().add(m_GameBar.m_MenuPane);

        Scene game = new Scene(gameBoard);

        primaryStage.setScene(game);
        primaryStage.setTitle("Minesweeperish");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            /**
             * @param event the window being closed
             */
            @Override
            public void handle(WindowEvent event) {
                m_Timer.cancel();
            }
        });
        primaryStage.show();
    }

    /**
     * A public static method that resets the m_Time counter to 0 and passes the new m_Time value into M_GameBar's setTime().
     */
    public static void resetTime() {
        m_Second = 0;
        m_Minute = 0;
        m_GameBar.setTime(m_Minute, m_Second);
    }

    /**
     * A public static method that determines whether or not the game should be over.
     * @param value the value of whether or not the game is over.
     */
    public static void setGameOver(boolean value) {
        m_GameOver = value;
    }

    /**
     * A package-private static method that determines whether or not the game has ended.
     * @return true if the game is over or false if it is not
     */
    static boolean isGameOver() {
        return m_GameOver;
    }

    /**
     * A public static method that fills the m_GameGrid and sets each Cell's x- and y-coordinates.
     */
    public static void fillGrid() {
        int cellCount = 0;
        for (int x = 0; x < m_Controller.getCellsPerRow(); x++) {
            for (int y = 0; y < m_Controller.getCellsPerColumn(); y++) {
                m_Controller.getCell(cellCount).setX(x);
                m_Controller.getCell(cellCount).setY(y);
                m_GameCell[x][y] = m_Controller.getCell(cellCount);
                GridPane.setConstraints(m_GameCell[x][y], x, y);
                m_GameGrid.getChildren().add(m_GameCell[x][y]);
                cellCount++;
            }
        }
    }

    /**
     * A public static method that empties the M_GameGrid, so it can be refilled without having any data collisions.
     */
    public static void emptyGrid() {
        for (int x = 0; x < m_Controller.getCellsPerRow(); x++) {
            for (int y = 0; y < m_Controller.getCellsPerColumn(); y++) {
                m_GameGrid.getChildren().remove(m_GameCell[x][y]);
            }
        }
    }

    /**
     * A package-private static recursive method that clears all empty cells around the cell that was clicked on.
     * @param controller used to get the rows and columns of the grid for bounds-checking around the cell that was clicked on.
     * @param x the x-coordinate of the cell that was clicked on.
     * @param y the y-coordinate of the cell that was clicked on.
     */
    static void clearCells(Controller controller, int x, int y) {
        if (x < 0 || y < 0 || x > controller.getCellsPerRow() || y > controller.getCellsPerColumn()) {
            return;
        }
        if (m_GameCell[x][y].isCleared()) {
            return;
        }
        if (m_GameCell[x][y].hasBomb()) {
            return;
        }
        m_GameCell[x][y].setNeighborBombs(checkNeighbors(x, y));
        if (m_GameCell[x][y].getNeighborBombs() > 0) {
            return;
        }
        for (int i = (x - 1); i <= (x + 1); i++) {
            for (int j = (y - 1); j <= (y + 1); j++) {
                try {
                    clearCells(controller, i, j);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }

    /**
     * A private method for checking if each neighbor of a cell has a bomb
     * @param row the row of the cell being checked
     * @param col the column of the cell being checked
     */
    private static int checkNeighbors(int row, int col) {
        try {
            if (m_GameCell[row + 1][col].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row - 1][col].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row][col + 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row][col - 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row + 1][col + 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row - 1][col - 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row + 1][col - 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        try {
            if (m_GameCell[row - 1][col + 1].hasBomb()) {
                m_GameCell[row][col].addNeighborBomb();
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        m_GameCell[row][col].clearCell(m_Controller);
        return m_GameCell[row][col].getNeighborBombs();
    }

    /**
     * A public method used to display a message when the game ends indicating that the game is over.
     * The message says if the player won or lost and the time it took them to do so.
     */
    public static void showGameOverMessage() {
        if (m_Controller.hasWon()) {
            m_Alert.setContentText("You win!\n\nIt took you " + m_Minute + " minute(s) " + m_Second + " second(s).");
        } else {
            m_Alert.setContentText("You lose!\n\nIt took you " + m_Minute + " minute(s) " + m_Second + " second(s).");
        }
        m_Alert.showAndWait();
    }
}