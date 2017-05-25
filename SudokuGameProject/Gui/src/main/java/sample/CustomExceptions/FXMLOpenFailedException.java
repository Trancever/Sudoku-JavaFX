package sample.CustomExceptions;

import sample.WindowManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLOpenFailedException extends IOException {

    ResourceBundle labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());

    private static final long serialVersionUID = 1L;
    public FXMLOpenFailedException(String message) {
        super(message);
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
