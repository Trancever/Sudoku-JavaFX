package sample.CustomExceptions;

import sample.WindowManager;

import java.util.ResourceBundle;

/**
 * BindingFailedException 
 */
public class BindingFailedException extends NoSuchMethodException {

    ResourceBundle labels;

    public BindingFailedException(final String message) {
        super(message);
        labels = ResourceBundle.getBundle("MyBundle", WindowManager.getInstance().getCurrentLocale());
    }

    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
