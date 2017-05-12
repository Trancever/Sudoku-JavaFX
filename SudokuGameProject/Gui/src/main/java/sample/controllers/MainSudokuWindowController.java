package sample.controllers;

import game.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.ApplicationSettings;
import sample.CustomWidgets.FieldPane;
import sample.CustomWidgets.NumberButton;


public class MainSudokuWindowController {

    private Game game;

    private FieldPane currentSelectedField;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private GridPane buttonsGrid;

    @FXML
    public void initialize() {
        initializeSudokuGrid();
        initializeButtons();
    }

    private void initializeButtons() {
        for (int x = 0; x < 10; x++) {
            NumberButton button = new NumberButton(x);
            button.getStyleClass().add("insert-number-button");
            button.setText(Integer.toString(button.getNumber()));
            if (x == 0) {
                button.setText("reset");
                buttonsGrid.add(button, 0, 9);
            } else {
                buttonsGrid.add(button, 0, x-1);
            }
            button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    NumberButton button = (NumberButton) event.getSource();
                    System.out.println(button.toString() + " clicked. Number = " + button.getNumber());
                    if (currentSelectedField != null) {
                        if (button.getNumber() == 0) {
                            currentSelectedField.setLabelText("");
                        } else {
                            currentSelectedField.setLabelText(button.getText());
                        }

                    }
                }
            });
        }
    }

    private void initializeSudokuGrid() {
        this.currentSelectedField = null;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                this.game = ApplicationSettings.getInstance().getGame();
                FieldPane pane = new FieldPane(x, y);
                pane.setLabelText(Integer.toString(this.game.getSudokuBoard().getValue(x, y)));
                pane.getStyleClass().add("sudokuField");
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        FieldPane pane = (FieldPane) event.getSource();
                        for (Node field : sudokuGrid.getChildren()) {
                            if (field != pane) {
                                field.getStyleClass().remove("sudokuFieldSelected");
                            }
                        }

                        System.out.println(pane.toString() + " clicked. Position " + pane.getX() + " " + pane.getY());
                        if (currentSelectedField != null && currentSelectedField.equals(pane)) {
                            pane.getStyleClass().remove("sudokuFieldSelected");
                            currentSelectedField = null;
                        } else {
                            pane.getStyleClass().add("sudokuFieldSelected");
                            currentSelectedField = pane;
                        }
                    }
                });
                sudokuGrid.add(pane, y, x);
            }
        }
    }
}
