package sample;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SplashScreenLoader extends Preloader {

    Logger logger = LoggerFactory.getLogger(SplashScreenLoader.class);

    private Stage splashScreen;

    @Override
    public void start(final Stage stage) throws Exception {
        splashScreen = stage;
        splashScreen.initStyle(StageStyle.TRANSPARENT);
        splashScreen.setScene(createScene());
        splashScreen.show();
        logger.debug("SplashScreenLoader started.");
    }

    public Scene createScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('images/sudoku-splash.png')");
        Scene scene = new Scene(root, 400, 130);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    @Override
    public void handleStateChangeNotification(final StateChangeNotification notification) {
        if (notification.getType() == StateChangeNotification.Type.BEFORE_START) {
            splashScreen.hide();
        }
    }

}
