package sample.CustomWidgets;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


/**
 * Created by Trensik on 5/12/2017.
 */
public class FieldPane extends Pane {

    private int x;
    private int y;
    private Label label;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FieldPane(final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
        this.label = new Label();
        this.getChildren().add(label);
        this.label.setAlignment(Pos.CENTER);
    }

    public void setLabelText(final String text) {
        this.label.setText(text);
    }
}
