package sample.controllers;

import exceptions.SudokuDeserializeException;
import game.Game;
import game.GameLevel;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.FXMLOpenFailedException;
import sample.CustomExceptions.SaveGameReadException;
import sample.MainSudokuWindow;
import sample.WindowManager;
import sudokupack.Dao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseLevelWindowController {

    Logger logger = LoggerFactory.getLogger(ChooseLevelWindowController.class);

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
        logger.debug("ChooseLevelWindowController initialized");
    }

    private void runGame(final GameLevel level, final SudokuBoard sudokuBoard, final boolean isLoaded) {
        MainSudokuWindow window = new MainSudokuWindow();
        try {
            WindowManager.getInstance().setGame(new Game(level, sudokuBoard, isLoaded));
            window.start();
        } catch (FXMLOpenFailedException e) {
            logger.error(e.getLocalizedMessage());
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
    public void onRadioToggleGroupToggled() throws FXMLOpenFailedException {

        Scene scene = this.polishRadioButton.getScene();
        Locale locale;
        if (this.polishRadioButton.isSelected()) {
            locale = new Locale("pl", "PL");
        } else {
            locale = new Locale("en", "US");
        }
        Parent root = null;
        try {
            root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ChooseLevelWindow.fxml"),
                    ResourceBundle.getBundle("MyBundle", locale));
        } catch (IOException e) {
            throw new FXMLOpenFailedException("FXMLOpenFailedException");
        }
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
    public void onLoadGameButtonClicked() throws SaveGameReadException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file with saved game.");
            fileChooser.getExtensionFilters().add(new FileChooser.
                                        ExtensionFilter("Default Sudoku game extension", "*.xD"));
            File file = fileChooser.showOpenDialog(this.exitButton.getScene().getWindow());
            if (file ==  null) {
                return;
            }
            Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(file.getPath());
            SudokuBoard board = dao.read();
            this.runGame(null, board, true);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while loading game.");
            alert.setHeaderText(null);
            alert.setContentText("We have encountered problem while loading your last saved game. Sorry.");
            alert.showAndWait();
        }
    }
}
