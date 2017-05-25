package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.StartFailedException;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainSudokuWindow {

    Logger logger = LoggerFactory.getLogger(MainSudokuWindow.class);

    private Stage stage;
    private Scene scene;

    public void start() throws StartFailedException {
        ResourceBundle bundle = ResourceBundle.getBundle("MyBundle",
                WindowManager.getInstance().getCurrentLocale());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainSudokuWindow.fxml"), bundle);
        } catch (IOException e) {
            throw new StartFailedException("startFailedException");
        }
        this.scene = new Scene(root);
        this.stage = new Stage();
        this.stage.setTitle("Sudoku Game");
        this.stage.setScene(scene);
        this.stage.getIcons().add(new Image("images/sudoku.png"));
        this.stage.setResizable(false);
        this.stage.show();
        logger.debug("MainSudokuWindow started");
    }
}
