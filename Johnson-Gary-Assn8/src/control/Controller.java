package control;

import view.Cell;
import view.GameBar;
import view.Minesweeperish;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * A public Controller Class that creates all the cells in the grid,
 * handles all the data including the rows and columns of the grid,
 * the number of bombs in the grid, the total number of cells in the grid,
 * the total number of cleared cells, and the number of cells that need to be cleared in order to win.
 * @author Gary Johnson
 */
public class Controller {

    /**
     * A private ArrayList that is filled with all the cells in the game.
     * Used to populate a 2D array that fills the game grid
     */
    private ArrayList<Cell> cellList;

    /**
     * A private int that stores the rows of the game grid.
     */
    private int m_CellsPerRow;

    /**
     * A private int that stores the columns of the game grid.
     */
    private int m_CellsPerCol;

    /**
     * A private int that keeps track of how many bombs the game has.
     */
    private int m_Bombs;

    /**
     * A private int that keeps track of all the cells that have been cleared.
     */
    private int m_ClearedCells;

    /**
     * A private int that keeps track of all the cells that are in the game grid that don't have bombs.
     */
    private int m_TotalEmptyCells;

    /**
     * A private boolean that keeps track of if the user has won or lost.
     */
    private boolean m_Win;

    /**
     * ***BONUS***
     * A private String that stores the difficulty to determine the amount of bombs a game has.
     */
    private String m_Difficulty;

    /**
     * Default Constructor that initializes every member variable of Controller to zero.
     */
    public Controller() {
        m_CellsPerRow = 0;
        m_CellsPerCol = 0;
        m_Bombs = 0;
        m_ClearedCells = 0;
        m_TotalEmptyCells = 0;
    }

    /**
     * A public method that sets the number of cells each row has.
     * @param cells the number of cells per row.
     */
    public void setCellsPerRow(int cells) { m_CellsPerRow = cells; }

    /**
     * A public method that sets the number of cells each column has.
     * @param cells the number of cells per column.
     */
    public void setCellsPerColumn(int cells) { m_CellsPerCol = cells; }

    /**
     * A public method that sets the percentage of the cells that have bombs.
     * @param percentage the amount of the cells that have bombs.
     */
    public void setBombs(double percentage) {
        percentage /= 100;
        double bombs =  percentage * getTotalCells();
        m_Bombs = (int) bombs;
    }

    /**
     * A public method that determines whether or not the user wins to decide what the game over message should do.
     * @param value true if the user wins or false if the user loses.
     */
    public void setWin(Boolean value) {
        m_Win = value;
    }

    /**
     * ***BONUS***
     * A public method that sets the mode of the game to either a small grid, medium grid, or large grid.
     * @param mode the mode that determines the number of rows and columns of the game.
     */
    public void setMode(String mode) {
        if (Objects.equals(mode, "Small")) {
            m_CellsPerRow = 10;
            m_CellsPerCol = 10;
            setDifficulty(getDifficulty());
//            setCellsPerRow(10);
//            setCellsPerColumn(10);
        } else if (Objects.equals(mode, "Medium")) {
            m_CellsPerRow = 25;
            m_CellsPerCol = 25;
            setDifficulty(getDifficulty());
//            setCellsPerRow(25);
//            setCellsPerColumn(25);
        } else if (Objects.equals(mode, "Large")){
            m_CellsPerRow = 50;
            m_CellsPerCol = 25;
            setDifficulty(getDifficulty());
//            setCellsPerRow(50);
//            setCellsPerColumn(25);
        }
    }

    /**
     * ***BONUS***
     * A public method that sets the amount of cells in the game that have bombs.
     * @param difficulty the value that determines the percentage of the game grid is bombs.
     *                   "Easy" is 10% of the grid.
     *                   "Medium" is 25% of the grid.
     *                   "Hard" is 40% of the grid.
     */
    public void setDifficulty(String difficulty) {
        if (Objects.equals(difficulty, "Easy")) {
            setBombs(10);
        } else if (Objects.equals(difficulty, "Medium")) {
            setBombs(25);
        } else if (Objects.equals(difficulty, "Hard")) {
            setBombs(40);
        }
        m_Difficulty = difficulty;
    }

    /**
     * A public method that returns the cell from the cellList at the specified index.
     * @param i the index to specify which cell to get from the cellList.
     * @return the cell from the cellList at the specified index.
     */
    public Cell getCell(int i) { return cellList.get(i); }

    /**
     * A public method that returns the number of cells in each row.
     * @return the cells per row.
     */
    public int getCellsPerRow() {
        return m_CellsPerRow;
    }

    /**
     * A public method that returns the number of cells in each column.
     * @return the cells per column.
     */
    public int getCellsPerColumn() {
        return m_CellsPerCol;
    }

    /**
     * A private method that returns the total number of cells in a game grid.
     * @return the number of cells per row multiplied by the number of cells per column.
     */
    private int getTotalCells() {
        return m_CellsPerRow * m_CellsPerCol;
    }

    /**
     * A public method that returns the number of bombs the game has.
     * @return the number of bombs the game has.
     */
    public int getBombs() {
        return m_Bombs;
    }

    /**
     * ***BONUS***
     * A private method that returns the difficulty of the game.
     * @return the difficulty to determine the percentage of bombs the game has.
     */
    private String getDifficulty() {
        return m_Difficulty;
    }

    /**
     * A public boolean method that determines whether or not the user has won the game.
     * @return true if the user has won or false if the user has lost.
     */
    public boolean hasWon() {
        return m_Win;
    }

    /**
     * A public method that creates a new cellList and fills it with cells and sets the actions for every cell.
     * It also uses the number of bombs that the game has to give a bomb to that many cells.
     * It then shuffles cellList to randomize the cells with bombs.
     * @param gameBar used to pass into the setCellActions method to set a cell's actions.
     */
    public void fillList(GameBar gameBar) {
        m_TotalEmptyCells = getTotalCells() - getBombs();
        cellList = new ArrayList<>();
        for (int i = 0; i < getTotalCells(); i++) {
            Cell cell = new Cell();
            if (i < m_Bombs) {
                cell.giveBomb();
            }
            cell.setCellActions(gameBar, this);
            cellList.add(cell);
        }
        Collections.shuffle(cellList);
    }

    /**
     * <p>
     *     A public method that changes the color of cells depending on how the user performed.
     *     If the user wins, every cell that has a bomb is marked as a bomb and colored green.
     *     If the user lost, the cells are changed to red if they have a bomb,
     *     green if they have a bomb and were correctly marked as a bomb,
     *     or yellow if they were marked as a bomb but don't have a bomb.
     * </p>
     * <p>
     *     Sets game over to true and start to false.
     *     Displays a game over pop-up message.
     * </p>
     * @param gameBar the game's game bar that sets start to false and change the name of the "Start" button to "Restart".
     */
    public void gameOver(GameBar gameBar) {
        gameBar.startGame(false);
        gameBar.setStartBtnText("Restart");
        cellList.stream().filter(Cell::hasBomb).forEach(c -> {
            if (m_Win) {
                c.setText("x");
                c.setStyle("-fx-background-color: green; -fx-border-color: black; -fx-border-width: 1px");
            } else {
                if (Objects.equals(c.getText(), "")) {
                    c.setText("x");
                    c.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-width: 1px");
                } else if (Objects.equals(c.getText(), "x") || Objects.equals(c.getText(), "?")) {
                    c.setText("x");
                    c.setStyle("-fx-background-color: green; -fx-border-color: black; -fx-border-width: 1px");
                }
            }
        });
        cellList.stream().filter(c -> !c.hasBomb()).forEach(c -> {
                if (!m_Win) {
                    if (Objects.equals(c.getText(), "x") || Objects.equals(c.getText(), "?")) {
                        c.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 1px");
                    }
                }
        });

        Minesweeperish.setGameOver(true);
        Minesweeperish.showGameOverMessage();
    }

    /**
     * A public method that increments the number of cells that have been cleared.
     */
    public void addClearedCell() {
        m_ClearedCells++;
    }

    /**
     * A public boolean method that checks if all the cells with no bombs have been cleared.
     * @return a comparison between the total number of cells with no bombs and the number of cells that have been cleared.
     */
    public boolean allCleared() {
        return m_ClearedCells == m_TotalEmptyCells;
    }

    /**
     * A public method that resets the game board when the "Start" or "Restart" button is pushed after a game ends.
     * @param gameBar used to reset the total number of bombs to be displayed.
     */
    public void reset(GameBar gameBar) {
        m_ClearedCells = 0;
        fillList(gameBar);
        gameBar.setBombNum(getBombs());
        Minesweeperish.emptyGrid();
        Minesweeperish.fillGrid();
        Minesweeperish.resetTime();
    }
}