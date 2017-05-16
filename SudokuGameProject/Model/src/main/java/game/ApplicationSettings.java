package game;

import java.util.Locale;

/**
 * Created by Trensik on 5/10/2017.
 */
public class ApplicationSettings {

    private Locale currentLocale;

    private Game game;

    public static final String SAVE_FILE_PATH = "d:/saved.xD";

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

    private static ApplicationSettings applicationSettings = new ApplicationSettings();

    private ApplicationSettings() { }

    public static ApplicationSettings getInstance() {
        return applicationSettings;
    }
}
