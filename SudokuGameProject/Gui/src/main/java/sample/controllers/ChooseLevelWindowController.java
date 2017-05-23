package sample.controllers;

import game.Game;
import game.GameLevel;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import sample.MainSudokuWindow;
import sample.WindowManager;
import sudokupack.BackTrackingSudokuSolver;
import sudokupack.Dao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseLevelWindowController {

    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button exitButton;

    @FXML
    private RadioButton polishRadioButton;

    @FXML
    private RadioButton usRadioButton;

    @FXML
    private MenuItem loadItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    public void onEasyButtonClick() {
        this.runGame(GameLevel.EASY, new SudokuBoard(), false);
    }

    @FXML
    public void onMediumButtonClick() {
        this.runGame(GameLevel.MEDIUM, new SudokuBoard(), false);
    }

    @FXML
    public void onHardButtonClick() {
        this.runGame(GameLevel.HARD, new SudokuBoard(), false);
    }

    @FXML
    public void initialize() {
        if (easyButton.getText().equals("Easy")) {
            usRadioButton.setSelected(true);
        } else {
            polishRadioButton.setSelected(true);
        }
    }

    private void runGame(GameLevel level, SudokuBoard sudokuBoard, boolean isLoaded) {
        MainSudokuWindow window = new MainSudokuWindow();
        try {
            WindowManager.getInstance().setGame(new Game(level, sudokuBoard, isLoaded));
            window.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) exitButton.getScene().getWindow();
        WindowManager.getInstance().setPrimaryStage(stage);
        stage.hide();
    }

    @FXML
    public void onExitButtonClick() {
        this.closeApp();
    }

    @FXML
    public void onRadioToggleGroupToggled() throws IOException {

        Scene scene = this.polishRadioButton.getScene();
        Locale locale;
        if (this.polishRadioButton.isSelected()) {
            locale = new Locale("pl", "PL");
        } else {
            locale = new Locale("en", "US");
        }
        Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ChooseLevelWindow.fxml"),
                ResourceBundle.getBundle("MyBundle", locale));
        scene.setRoot(root);
        WindowManager.getInstance().setCurrentLocale(locale);
    }

    @FXML
    public void onCloseButtonClicked() {
        this.closeApp();
    }

    private void closeApp() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLoadGameButtonClicked() {
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(WindowManager.SAVE_FILE_PATH);
        SudokuBoard board = dao.read();
        this.runGame(GameLevel.EASY, board, true);
    }
}
