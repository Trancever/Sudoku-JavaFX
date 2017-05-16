package sample.CustomWidgets;

import game.ApplicationSettings;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.util.ResourceBundle;

public class NumberButton extends Button {

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
                    ApplicationSettings.getInstance().getCurrentLocale());
            this.setText(bundle.getString("clearButton"));
        } else {
        }
    }
}
