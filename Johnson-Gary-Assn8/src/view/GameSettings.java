package view;

import control.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

/**
 * A package-private helper class that sets up a MenuBar that can edit the game board mid-play to change the number of cells and bombs the game has
 * @author Gary Johnson
 */
class GameSettings extends MenuBar {

//    private int m_Rows;
//
//    private int m_Columns;

    /**
     *
     */
    private RadioMenuItem m_SmallBoardBtn;

    /**
     *
     */
    private RadioMenuItem m_MediumBoardBtn;

    /**
     *
     */
    private RadioMenuItem m_LargeBoardBtn;

    /**
     *
     */
    private RadioMenuItem m_EasyDiffBtn;

    /**
     *
     */
    private RadioMenuItem m_MediumDiffBtn;

    /**
     *
     */
    private RadioMenuItem m_HardDiffBtn;

    /**
     * A package-private Regular Constructor that sets up the menu bar used in the game bar.
     * @param controller the controller for the game that this constructor uses to reset the the rows, columns, and bombs.
     */
    GameSettings(Controller controller) {
        Menu modeMenu = new Menu("Mode");
        Menu difficultyMenu = new Menu("Difficulty");
        MenuItem helpBtn = new MenuItem("How to Play");
        MenuItem highScore = new MenuItem("High Score");
        MenuItem noScore = new MenuItem("Clear Record");
        m_SmallBoardBtn = new RadioMenuItem("Small: 10 x 10");
        m_MediumBoardBtn = new RadioMenuItem("Medium: 25 x 25");
        m_LargeBoardBtn = new RadioMenuItem("Large: 50 x 25");
        m_EasyDiffBtn = new RadioMenuItem("Easy: 10%");
        m_MediumDiffBtn = new RadioMenuItem("Medium: 25%");
        m_HardDiffBtn = new RadioMenuItem("Hard: 40%");
        //SeparatorMenuItem itemSeparator = new SeparatorMenuItem();

        modeMenu.getItems().addAll(m_SmallBoardBtn, m_MediumBoardBtn, m_LargeBoardBtn);
        difficultyMenu.getItems().addAll(m_EasyDiffBtn, m_MediumDiffBtn, m_HardDiffBtn);

        ToggleGroup modeGroup = new ToggleGroup();
        modeGroup.getToggles().addAll(m_SmallBoardBtn, m_MediumBoardBtn, m_LargeBoardBtn);
        m_MediumBoardBtn.setSelected(true);
        controller.setMode("Medium");
        ToggleGroup diffGroup = new ToggleGroup();
        diffGroup.getToggles().addAll(m_EasyDiffBtn, m_MediumDiffBtn, m_HardDiffBtn);
        m_MediumDiffBtn.setSelected(true);
        controller.setDifficulty("Medium");

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(highScore, noScore);
        highScore.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "High Score" button being pressed.
             */
            @Override
            public void handle(ActionEvent event) {

            }
        });
        noScore.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Clear Record" button being pressed.
             */
            @Override
            public void handle(ActionEvent event) {

            }
        });
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(modeMenu, difficultyMenu);
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(helpBtn);
        helpBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "How to Play" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                Alert rulesDialog = new Alert(Alert.AlertType.INFORMATION);
                rulesDialog.setTitle("How to Play");
                rulesDialog.setHeaderText(null);
                rulesDialog.setContentText("1. Uncover a mine, and the game ends.\n2. Uncover an empty square, and you keep playing.\n3. Uncover a number, and it tells you how many mines lay\n    hidden in the eight surrounding squares - information you\n    use to deduce which nearby squares are safe to click.");
                rulesDialog.show();
            }
        });

        this.getMenus().addAll(fileMenu, editMenu, helpMenu);
    }

//    public void setModeRows(int rows) {
//        m_Rows = rows;
//    }
//
//    public void setModeColumns(int columns) {
//        m_Columns = columns;
//    }
//
//    public int getModeRows() {
//        return m_Rows;
//    }
//
//    public int getModeColumns() {
//        return m_Columns;
//    }

    /**
     * A package-private method that sets the actions of the edit menu's buttons to change the difficulty and mode of the game.
     * @param gameBar the gameBar for the game that this method uses to enable the start button when the mode or difficulty is changed.
     * @param controller the controller for the game that this method uses to reset the the rows, columns, and bombs.
     */
    void setMenuButtonActions(GameBar gameBar, Controller controller) {
        m_SmallBoardBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Small" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Small Board");
                controller.setMode("Small");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
                //controller.reset(gameBar);
                //stage.sizeToScene();
//                setModeRows(10);
//                setModeColumns(10);
//                controller.setCellsPerRow(10);
//                controller.setCellsPerColumn(10);
//                controller.reset(gameBar);
            }
        });
        m_MediumBoardBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Medium" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Medium Board");
                controller.setMode("Medium");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
//                setModeRows(25);
//                setModeColumns(25);
//                controller.setCellsPerRow(25);
//                controller.setCellsPerColumn(25);
                //controller.reset(gameBar);
                //stage.sizeToScene();
            }
        });
        m_LargeBoardBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Large" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Large Board");
                controller.setMode("Large");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
//                setModeRows(50);
//                setModeColumns(25);
//                controller.setCellsPerRow(50);
//                controller.setCellsPerColumn(25);
                //controller.reset(gameBar);
                //stage.sizeToScene();
            }
        });
        m_EasyDiffBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Easy" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Easy");
                controller.setDifficulty("Easy");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
                //controller.reset(gameBar);

            }
        });
        m_MediumDiffBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Medium" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Medium");
                controller.setDifficulty("Medium");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
                //controller.reset(gameBar);
            }
        });
        m_HardDiffBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event the "Hard" button being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hard");
                controller.setDifficulty("Hard");
                Minesweeperish.setGameOver(true);
                gameBar.startGame(false);
                //controller.reset(gameBar);
            }
        });

    }
}