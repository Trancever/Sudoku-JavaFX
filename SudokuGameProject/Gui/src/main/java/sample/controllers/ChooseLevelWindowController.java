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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.FXMLOpenFailedException;
import sample.CustomExceptions.SaveGameOpenFailedException;
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
        this.runGame(GameLevel.EASY, new SudokuBoard(), false, null);
    }

    @FXML
    public void onMediumButtonClick() {
        this.runGame(GameLevel.MEDIUM, new SudokuBoard(), false, null);
    }

    @FXML
    public void onHardButtonClick() {
        this.runGame(GameLevel.HARD, new SudokuBoard(), false, null);
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

    private void runGame(final GameLevel level, final SudokuBoard sudokuBoard, final boolean isLoaded, final List<List<Boolean>> helperList) {
        MainSudokuWindow window = new MainSudokuWindow();
        try {
            WindowManager.getInstance().setGame(new Game(level, sudokuBoard, isLoaded, helperList));
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
    public void onLoadGameButtonClicked() throws SaveGameOpenFailedException {
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(WindowManager.SAVE_FILE_PATH);
        SudokuBoard board = dao.read();
        List<List<Boolean>> tmp = new ArrayList<List<Boolean>>();
        for (int i  = 0; i < 9; i++) {
            tmp.add(new ArrayList<Boolean>());
            for (int j = 0; j < 9; j++) {
                tmp.get(i).add(true);
            }
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(WindowManager.SAVE_HELPER_FILE_PATH));
            String line;
            while ((line = in.readLine()) != null) {
                if (line != "") {
                    String[] str = line.split(",");
                    tmp.get(Integer.parseInt(str[0])).set(Integer.parseInt(str[1]), Boolean.parseBoolean(str[2]));
                }
            }
        } catch (Exception e) {
            throw new SaveGameOpenFailedException("SaveGameOpenFailedException");
            //TODO: okienko informujace ze sie nie udalo wczytac gry
        }
        this.runGame(GameLevel.EASY, board, true, tmp);
    }
}
