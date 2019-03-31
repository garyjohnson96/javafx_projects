package view;

import control.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A public class that inherits HBox and acts as the bar on top of the game grid.
 * It displays the total number of bombs in the game grid that decrements when a cell is marked as a bomb
 *   and increments when it is no longer marked as a bomb.
 * It also displays a timer to show how many seconds the game has been running.
 * It also displays a "Start" button that resets the game after it ends or mode/difficulty is changed.
 * @author Gary Johnson
 */
public class GameBar extends HBox {

    /**
     * ***BONUS***
     * A package-private AnchorPane used to anchor the menuBar to the top-left corner of the GameBar.
     */
    AnchorPane m_MenuPane;

    /**
     * A private Label that labels the number of remaining bombs.
     */
    private Label m_BombLabel;

    /**
     * A private Text that labels the amount of seconds the game has been running.
     */
    private Text m_TimeLabel;

    /**
     * A private Button used to start a new game after a game has ended.
     */
    private Button m_StartBtn;

    /**
     * A private boolean that determines whether or not the game has been started.
     */
    private boolean m_Start;

    /**
     * A private int that stores the number of bombs that are scattered about the game grid
     */
    private int m_BombNum;

    /**
     * A private int that stores the number of seconds that have passed since the game started.
     */
    private int m_Seconds;

    /**
     * Regular Constructor that initializes start to false, seconds to 0, creates the bomb and seconds labels and the "Start" button,
     * sets the "Start" button's action to reset the game when it's clicked,
     * adds the labels and button to the GameBar,
     * and sets the alignment, spacing, and padding of the GameBar.
     * @param controller the game's controller used to reset the game when the "Start" button's clicked.
     */
    GameBar(Controller controller) {
        m_Start = false;
        m_Seconds = 0;
        m_BombLabel = new Label();
        m_TimeLabel = new Text();
        m_StartBtn = new Button();
        m_StartBtn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event m_StartBtn being pressed
             */
            @Override
            public void handle(ActionEvent event) {
                if (Minesweeperish.isGameOver()) {
                    controller.reset(GameBar.this);
                    Minesweeperish.setGameOver(false);
                }
            }
        });

        GameSettings settings = new GameSettings(controller);
        settings.setMenuButtonActions(this, controller);

        m_MenuPane = new AnchorPane(settings);
        AnchorPane.setTopAnchor(settings, 0.0);
        AnchorPane.setLeftAnchor(settings, 0.0);

        this.getChildren().addAll(m_MenuPane, m_BombLabel, m_StartBtn, m_TimeLabel);
        //this.getChildren().addAll(m_BombLabel, m_StartBtn, m_TimeLabel);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(75);
        this.setPadding(new Insets(5, 5, 5, 5));
    }

    /**
     * A public method that determines whether or not the game should be started.
     * @param value the value of whether or not the game should be started. True if it should start or false if it should end.
     */
    public void startGame(boolean value) {
        m_Start = value;
        m_StartBtn.setDisable(value);
    }

    /**
     * A package-private boolean method that determines whether or not the game has been started.
     * @return true if the game has been started of false if it has not.
     */
    boolean started() {
        return m_Start;
    }

    /**
     * A public method that sets the number of bombs that the GameBar displays.
     * @param bombs the number of bombs to be displayed.
     */
    public void setBombNum(int bombs) {
        m_BombNum = bombs;
        m_BombLabel.setText("Bombs Left\n      " + m_BombNum);
    }

    /**
     * A public method that sets the Button's text.
     * @param btnName the text assigned to the button.
     */
    public void setStartBtnText(String btnName) {
        m_StartBtn.setText(btnName);
    }

    /**
     * A package-private method that sets the seconds that the GameBar displays.
     * @param seconds the number of seconds to be displayed.
     */
    void setTime(int minutes, int seconds) {
        //m_Seconds = seconds;
        if (seconds < 10) {
            m_TimeLabel.setText("Time\n " + minutes + ":0" + seconds);
        }
        else {
            m_TimeLabel.setText("Time\n " + minutes + ":" + seconds);
        }
    }

    /**
     * A package-private method that increments the number of bombs that the GameBar displays.
     * @param controller the game's controller used to make sure the number of bombs being displayed
     *                   never exceeds the actual number of bombs in the game.
     */
    void addBombNum(Controller controller) {
        if (m_BombNum < controller.getBombs()) {
            m_BombNum++;
            setBombNum(m_BombNum);
        }
    }

    /**
     * A package-private method that decrements the number of bombs that the GameBar displays.
     */
    void subtractBombNum() {
        if (m_BombNum > 0) {
            m_BombNum--;
            setBombNum(m_BombNum);
        }
    }
}