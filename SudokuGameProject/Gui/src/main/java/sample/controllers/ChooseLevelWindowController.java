package sample.controllers;

import game.Game;
import game.GameLevel;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.MainSudokuWindow;
import sudokupack.BackTrackingSudokuSolver;

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
    public void onEasyButtonClick() {
        System.out.println("Hehe dziala przycisk easy!");
        MainSudokuWindow window = new MainSudokuWindow();
        try {
            window.start();
            window.getController().setGame(new Game(GameLevel.EASY, new BackTrackingSudokuSolver()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) easyButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void onMediumButtonClick() {
        System.out.println("Hehe dziala przycisk medium!");
    }

    @FXML
    public void onHardButtonClick() {
        System.out.println("Hehe dziala przycisk hard!");
    }

    @FXML
    public void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
