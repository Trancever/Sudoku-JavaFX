package sample.CustomExceptions;

import sample.WindowManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLOpenFailedException extends IOException {

    ResourceBundle labels;

    public FXMLOpenFailedException(final String message) {
        super(message);
        labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
