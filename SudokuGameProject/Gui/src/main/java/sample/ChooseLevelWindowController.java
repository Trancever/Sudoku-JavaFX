package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;

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
