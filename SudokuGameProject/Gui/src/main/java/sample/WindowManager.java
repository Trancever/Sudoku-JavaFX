package sample;

import game.Game;
import javafx.stage.Stage;

import java.util.Locale;

public class WindowManager {

    private Stage primaryStage;

    private Locale currentLocale;

    private Game game;

    private boolean isLoaded;

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(final boolean loaded) {
        isLoaded = loaded;
    }

    public static final String SAVE_FILE_PATH = "saved.xD";
    public static final String SAVE_HELPER_FILE_PATH = "fields.xD";

    public Game getGame() {
        return game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public void setCurrentLocale(final Locale locale) {
        this.currentLocale = locale;
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public void setPrimaryStage(final Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private static WindowManager manager = new WindowManager();

    private WindowManager() {
    }

    public static WindowManager getInstance() {
        return manager;
    }
}
