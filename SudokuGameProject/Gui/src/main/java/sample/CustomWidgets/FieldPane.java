package sample.CustomWidgets;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * FieldPane represents field in Sudoku
 */
public class FieldPane extends Pane {

    /**
     * x is row position
     */
    private int x;

    /**
     * y is column position
     */
    private int y;

    /**
     * label taht is displayed on the Pane
     */
    private Label label;

    /**
     * flag that holds information if field is changeable
     */
    private boolean isChangeable;

    public Label getLabel() {
        return this.label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isChangeable() {
        return isChangeable;
    }

    public FieldPane(final int x, final int y, final String text, final boolean isChangeable) {
        super();
        this.x = x;
        this.y = y;
        this.label = new Label();
        this.getChildren().add(label);
        this.label.layoutXProperty().bind(this.widthProperty().subtract(this.label.widthProperty()).divide(2));
        this.label.layoutYProperty().bind(this.heightProperty().subtract(this.label.heightProperty()).divide(2));
        this.label.setFont(new Font("Arial", 48));
        this.isChangeable = isChangeable;
        this.setLabelText(text);
        this.setStyle();

    }

    /**
     * set given text on Pane's Label
     * @param text text that will be set in Pane
     */
    public void setLabelText(final String text) {
        String content = text;
        if (content.equals("0")) {
            content = "";
        }
        this.label.setText(content);
    }

    /**
     * setStyle changes FieldPane styles, depends on isChangeable flag
     */
    private void setStyle() {
        this.getStyleClass().add("sudokuField");
        this.setStyle("-fx-pref-width: 10em; -fx-pref-height: 10em;");
        if (!isChangeable) {
            this.getStyleClass().add("sudokuField-not-changeable");
        }
    }
}
