package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        //Wait 2 seconds for splash screen.
        TimeUnit.SECONDS.sleep(2);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Locale locale = new Locale("pl", "PL");
        ResourceBundle bundle = ResourceBundle.getBundle("MyBundle", locale);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/chooseLevelWindow.fxml"), bundle);
        primaryStage.setTitle("Sudoku Game");
        primaryStage.getIcons().add(new Image("images/sudoku.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(final String[] args) {
        LauncherImpl.launchApplication(Main.class, SplashScreenLoader.class, args);
    }
}
