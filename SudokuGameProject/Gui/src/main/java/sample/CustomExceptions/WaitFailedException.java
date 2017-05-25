package sample.CustomExceptions;

import sample.WindowManager;

import java.util.ResourceBundle;

/**
 * Created by Igor on 25.05.2017.
 */
public class WaitFailedException extends InterruptedException {

    ResourceBundle labels;

    public WaitFailedException(final String message) {
        super(message);
        labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
