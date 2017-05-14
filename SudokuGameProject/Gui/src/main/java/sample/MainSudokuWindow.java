package sample;

import game.ApplicationSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controllers.MainSudokuWindowController;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Trensik on 4/28/2017.
 */
public class MainSudokuWindow {

    private Stage stage;
    private Scene scene;

    public void start() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("MyBundle",
                ApplicationSettings.getInstance().getCurrentLocale());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainSudokuWindow.fxml"), bundle);
        this.scene = new Scene(root);
        this.stage = new Stage();
        this.stage.setTitle("Sudoku Game");
        this.stage.setScene(scene);
        this.stage.getIcons().add(new Image("images/sudoku.png"));
        this.stage.setResizable(false);
        this.stage.show();
    }
}
