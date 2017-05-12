package sample.controllers;

import game.Game;
import game.GameLevel;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.ApplicationSettings;
import sample.MainSudokuWindow;
import sudokupack.BackTrackingSudokuSolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
    public void onEasyButtonClick() {
        this.runGame(GameLevel.EASY);
    }

    @FXML
    public void onMediumButtonClick() {
        this.runGame(GameLevel.MEDIUM);
    }

    @FXML
    public void onHardButtonClick() {
        this.runGame(GameLevel.HARD);
    }

    @FXML
    public void initialize() {
        if (easyButton.getText().equals("Easy")) {
            usRadioButton.setSelected(true);
        } else {
            polishRadioButton.setSelected(true);
        }
    }

    private void runGame(GameLevel level) {
        MainSudokuWindow window = new MainSudokuWindow();
        try {
            ApplicationSettings.getInstance().setGame(new Game(level, new BackTrackingSudokuSolver()));
            window.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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
    }
}
