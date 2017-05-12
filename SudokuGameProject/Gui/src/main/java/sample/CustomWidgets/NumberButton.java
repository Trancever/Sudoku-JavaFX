package sample.CustomWidgets;

import javafx.scene.control.Button;

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
    }
}
