package sample.CustomExceptions;

import sample.WindowManager;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Igor on 25.05.2017.
 */
public class SaveGameWriteException extends IOException {
    ResourceBundle labels;

    public SaveGameWriteException(final String message) {
        super(message);
        labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
