package sample;

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

    private MainSudokuWindowController controller;
    private Stage stage;
    private Scene scene;

    public void start() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getClassLoader().getResource("fxml/MainSudokuWindow.fxml")));
        this.scene = new Scene((Parent)loader.load());
        this.controller = loader.getController();
        this.stage = new Stage();
        this.stage.setTitle("Sudoku Game");
        this.stage.setScene(scene);
        this.stage.getIcons().add(new Image("images/sudoku.png"));
        this.stage.setResizable(false);
        this.stage.show();
    }

    public MainSudokuWindowController getController() {
        return this.controller;
    }
}
