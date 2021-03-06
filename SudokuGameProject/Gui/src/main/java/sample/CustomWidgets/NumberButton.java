package sample.CustomWidgets;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import sample.WindowManager;

import java.util.ResourceBundle;

/**
 * NumberButton represents button in Sudoku
 */
public class NumberButton extends Button {

    /**
     * button's number
     */
    public int number;

    public int getNumber() {
        return number;
    }

    public NumberButton(final int number) {
        super();
        this.number = number;
        this.getStyleClass().add("insert-number-button");
        this.setText(Integer.toString(this.getNumber()));
        Font font = new Font("Arial", 20);
        this.setFont(font);
        if (number == 0) {
            ResourceBundle bundle = ResourceBundle.getBundle("MyBundle",
                    WindowManager.getInstance().getCurrentLocale());
            this.setText(bundle.getString("clearButton"));
        }
    }
}
