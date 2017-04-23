package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        //Wait 3 seconds for splash screen.
        TimeUnit.MILLISECONDS.sleep(2500);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Sudoku Game");
        primaryStage.getIcons().add(new Image("images/sudoku.png"));
        primaryStage.setScene(new Scene(root, 450, 660));
        primaryStage.getScene().getStylesheets().add("css/main.css");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(final String[] args) {
        LauncherImpl.launchApplication(Main.class, SplashScreenLoader.class, args);
    }
}
