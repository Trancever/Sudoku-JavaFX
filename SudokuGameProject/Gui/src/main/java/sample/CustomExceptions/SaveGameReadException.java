package sample.CustomExceptions;

import sample.WindowManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Igor on 25.05.2017.
 */
public class SaveGameReadException extends Exception {
    ResourceBundle labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());

    private static final long serialVersionUID = 1L;
    public SaveGameReadException(String message) {
        super(message);
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
