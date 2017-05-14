package sample.CustomWidgets;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


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
        this.label.layoutXProperty().bind(this.widthProperty().subtract(this.label.widthProperty()).divide(2));
        this.label.layoutYProperty().bind(this.heightProperty().subtract(this.label.heightProperty()).divide(2));
    }

    public void setLabelText(final String text) {
        this.label.setText(text);
    }

    public void setLabelFont(final Font font) {
        this.label.setFont(font);
    }
}
