package sample.controllers;

import exceptions.SudokuDeserializeException;
import game.Game;
import game.GameLevel;
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
import sample.CustomExceptions.SaveGameReadException;
import sample.MainSudokuWindow;
import sample.WindowManager;
import sudokupack.Dao;
import sudokupack.JDBCSudokuBoardDao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;

import java.io.*;
import java.util.*;

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
    public void onLoadFileGameButtonClicked() throws SaveGameReadException {
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

    private void notifyAboutNotSuccessfullDeserialization() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while loading game.");
        alert.setHeaderText(null);
        alert.setContentText("We have encountered problem while loading your last saved game. Sorry.");
        alert.showAndWait();
    }

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
