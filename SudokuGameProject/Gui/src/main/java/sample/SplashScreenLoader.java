package sample;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SplashScreenLoader class is responsible for creating splash screen on application start
 */
public class SplashScreenLoader extends Preloader {

    Logger logger = LoggerFactory.getLogger(SplashScreenLoader.class);

    private Stage splashScreen;

    /**
     * create and show splash screen
     * @param stage current stage
     * @throws Exception .
     */
    @Override
    public void start(final Stage stage) throws Exception {
        splashScreen = stage;
        splashScreen.initStyle(StageStyle.TRANSPARENT);
        splashScreen.setScene(createScene());
        splashScreen.show();
        logger.debug("SplashScreenLoader started.");
    }

    /**
     * create Scene for splash screen and style it
     * @return created scene
     */
    public Scene createScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('images/sudoku-splash.png')");
        Scene scene = new Scene(root, 400, 130);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    /**
     * Wait for BEFORE_START notification and close splash screen
     * @param notification StateChangeNotification
     */
    @Override
    public void handleStateChangeNotification(final StateChangeNotification notification) {
        if (notification.getType() == StateChangeNotification.Type.BEFORE_START) {
            splashScreen.close();
        }
    }

}
