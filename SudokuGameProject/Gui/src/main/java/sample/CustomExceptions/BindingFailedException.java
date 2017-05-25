package sample.CustomExceptions;

import sample.WindowManager;

import java.util.ResourceBundle;

/**
 * Created by Igor on 25.05.2017.
 */
public class BindingFailedException extends NoSuchMethodException {

    ResourceBundle labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());

    private static final long serialVersionUID = 1L;
    public BindingFailedException(String message) {
        super(message);
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
