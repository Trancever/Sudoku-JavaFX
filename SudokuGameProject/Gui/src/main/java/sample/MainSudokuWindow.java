package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.FXMLOpenFailedException;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This class is second window's class.
 */
public class MainSudokuWindow {

    Logger logger = LoggerFactory.getLogger(MainSudokuWindow.class);

    private Stage stage;
    private Scene scene;

    /**
     * start method loads fxml file, create scene and set it on stage
     * @throws FXMLOpenFailedException when problem occurs while loading fxml file.
     */
    public void start() throws FXMLOpenFailedException {
        ResourceBundle bundle = ResourceBundle.getBundle("MyBundle",
                WindowManager.getInstance().getCurrentLocale());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainSudokuWindow.fxml"), bundle);
        } catch (IOException e) {
            logger.error("FXMLOpenFailedException while trying to load MainSudokuWindow fxml.");
            throw new FXMLOpenFailedException("FXMLOpenFailedException");
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
