package sample.CustomExceptions;

import sample.WindowManager;

import java.util.ResourceBundle;

/**
 * Created by Igor on 25.05.2017.
 */
public class WaitFailedException extends InterruptedException {
    ResourceBundle labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());

    public WaitFailedException(String message) {
        super(message);
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
