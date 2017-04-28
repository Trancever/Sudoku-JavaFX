package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controllers.MainSudokuWindowController;

/**
 * Created by Trensik on 4/28/2017.
 */
public class MainSudokuWindow {

    private MainSudokuWindowController controller;
    private Stage stage;
    private Scene scene;

    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/MainSudokuWindow.fxml"));
        this.scene = new Scene((Parent) fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.stage = new Stage();
        this.stage.setTitle("Sudoku Game");
        this.stage.setScene(scene);
        this.stage.getIcons().add(new Image("images/sudoku.png"));
        this.stage.setResizable(false);
        this.stage.show();
    }
}
