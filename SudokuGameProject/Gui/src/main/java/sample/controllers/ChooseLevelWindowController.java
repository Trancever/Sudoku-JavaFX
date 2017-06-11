package sample.controllers;

import exceptions.SudokuDeserializeException;
import sample.helpers.Game;
import sample.helpers.GameLevel;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.FXMLOpenFailedException;
import sample.MainSudokuWindow;
import sample.WindowManager;
import sudokupack.Dao;
import sudokupack.JDBCSudokuBoardDao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;

import java.io.*;
import java.util.*;

/**
 * ChooseLevelWindowController is a controller class for ChooseLevelWindow View.
 */
public class ChooseLevelWindowController {

    Logger logger = LoggerFactory.getLogger(ChooseLevelWindowController.class);

    @FXML
    private Button easyButton;

    @FXML
    private Button exitButton;

    @FXML
    private RadioButton polishRadioButton;

    @FXML
    private RadioButton usRadioButton;

    /**
     * onEasyButtonClick method is being invoked on easyButton click. It runs game in easy mode.
     */
    @FXML
    public void onEasyButtonClick() {
        this.runGame(GameLevel.EASY, new SudokuBoard(), false);
    }

    /**
     * onMediumButtonClick method is being invoked on easyButton click. It runs game in medium mode.
     */
    @FXML
    public void onMediumButtonClick() {
        this.runGame(GameLevel.MEDIUM, new SudokuBoard(), false);
    }

    /**
     * onHardButtonClick method is being invoked on easyButton click. It runs game in hard mode.
     */
    @FXML
    public void onHardButtonClick() {
        this.runGame(GameLevel.HARD, new SudokuBoard(), false);
    }

    /**
     * initialize method creates and initialize ui controls.
     */
    @FXML
    public void initialize() {
        if (easyButton.getText().equals("Easy")) {
            usRadioButton.setSelected(true);
        } else {
            polishRadioButton.setSelected(true);
        }
        logger.debug("ChooseLevelWindowController initialized");
    }

    /**
     * runGame create new game, create new MainSudokuWindow
     * @param level level of game (easy, medium, hard)
     * @param sudokuBoard Sudoku model instance
     * @param isLoaded flag that shows if Sudoku is loaded or created new one
     */
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

    /**
     * onExitButtonClick method is being invoked on exitButton click. It close application.
     */
    @FXML
    public void onExitButtonClick() {
        this.closeApp();
    }

    /**
     * onRadioToggleGroupToggled is being invoked after changing language on ui. It's changing application locale and refresh UI.
     * @throws FXMLOpenFailedException throws it when exception occures while loading fxml file.
     */
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

    /**
     * onCloseButtonClicked method is being invoked on closeButton (in toolbar) click. It close application.
     */
    @FXML
    public void onCloseButtonClicked() {
        this.closeApp();
    }

    /**
     * closeApp closes application.
     */
    private void closeApp() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * onLoadFileGameButtonClicked loads saved game from file
     */
    @FXML
    public void onLoadFileGameButtonClicked() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file with saved game.");
            fileChooser.getExtensionFilters().add(new FileChooser.
                    ExtensionFilter("Default Sudoku game extension", "*.xD"));
            File file = fileChooser.showOpenDialog(this.exitButton.getScene().getWindow());
            if (file == null) {
                return;
            }
            Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao();
            SudokuBoard board = dao.read(file.getPath());
            this.runGame(null, board, true);
        } catch (SudokuDeserializeException e) {
            logger.error(e.getLocalizedMessage());
            notifyAboutNotSuccessfullDeserialization();
        }
    }

    /**
     * notifyAboutNotSuccessfullDeserialization shows alert window with message
     */
    private void notifyAboutNotSuccessfullDeserialization() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while loading game.");
        alert.setHeaderText(null);
        alert.setContentText("We have encountered problem while loading your last saved game. Sorry.");
        alert.showAndWait();
    }

    /**
     * onLoadDBGameButtonClicked loads saved game from database.
     */
    @FXML
    public void onLoadDBGameButtonClicked() {
        try {
            Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getJDBCDao();
            JDBCSudokuBoardDao jdbcDao = (JDBCSudokuBoardDao) dao;
            List<String> choices = jdbcDao.getAllBoardsNames();
            ChoiceDialog<String> dialog = new ChoiceDialog<String>("", choices);
            dialog.setTitle("Sudoku loader");
            dialog.setHeaderText(null);
            dialog.setContentText("Choose which save to load:");
            Optional<String> result = dialog.showAndWait();
            SudokuBoard board = dao.read(result.get());
            board.convert1dto2d();
            this.runGame(null, board, true);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            notifyAboutNotSuccessfullDeserialization();
        }
    }
}
