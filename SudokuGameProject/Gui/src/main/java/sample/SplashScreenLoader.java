package sample;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;


public class SplashScreenLoader extends Preloader {

    private Stage splashScreen;

    @Override
    public void start(Stage stage) throws Exception {
        splashScreen = stage;
        splashScreen.initStyle(StageStyle.TRANSPARENT);
        splashScreen.setScene(createScene());
        splashScreen.show();
    }

    public Scene createScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('images/sudoku-splash.png')");
        Scene scene = new Scene(root, 400, 130);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification notification) {
        if (notification.getType() == StateChangeNotification.Type.BEFORE_START) {
            splashScreen.hide();
        }
    }

}
