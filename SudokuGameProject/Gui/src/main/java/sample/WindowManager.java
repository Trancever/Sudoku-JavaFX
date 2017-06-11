package sample;

import sample.helpers.Game;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * WindowManager is a singeleton class which holds windows settings and app settings
 */
public class WindowManager {

    /**
     * current stage
     */
    private Stage primaryStage;

    /**
     * current app locale
     */
    private Locale currentLocale;

    /**
     * current Game
     */
    private Game game;

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
