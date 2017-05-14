package sample.CustomWidgets;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Trensik on 5/12/2017.
 */
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
            this.setText("clear");
        } else {
        }
    }
}
