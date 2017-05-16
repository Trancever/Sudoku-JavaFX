package sample;

import javafx.stage.Stage;

public class WindowManager {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private static WindowManager manager = new WindowManager();

    private WindowManager() {}

    public static WindowManager getInstance() {
        return manager;
    }
}
