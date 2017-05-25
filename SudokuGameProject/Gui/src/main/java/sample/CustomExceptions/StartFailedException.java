package sample.CustomExceptions;

import sample.WindowManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class StartFailedException extends IOException {

    ResourceBundle labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());

    private static final long serialVersionUID = 1L;
    public StartFailedException(String message) {
        super(message);
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
