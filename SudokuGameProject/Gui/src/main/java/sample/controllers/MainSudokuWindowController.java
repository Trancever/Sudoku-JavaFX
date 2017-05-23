package sample.controllers;

import com.google.common.io.Files;
import com.sun.org.apache.xpath.internal.operations.Bool;
import game.Game;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomWidgets.FieldPane;
import sample.CustomWidgets.NumberButton;
import sample.WindowManager;
import sample.helpers.CustomConverter;
import sudokupack.Dao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;

import java.io.*;


public class MainSudokuWindowController {

    final static Logger logger = LoggerFactory.getLogger(MainSudokuWindowController.class);

    private Game game;

    private FieldPane currentSelectedField;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private GridPane buttonsGrid;

    @FXML
    private Button resetGameButton;

    @FXML
    private Button saveStateButton;

    @FXML
    private Button newGameButton;

    @FXML
    public void initialize() {
        this.currentSelectedField = null;
        this.game = WindowManager.getInstance().getGame();
        this.game.cleanFields();
        this.initializeSudokuGrid();
        this.initializeButtons();
    }

    private void initializeButtons() {
        for (int x = 0; x < 10; x++) {
            NumberButton button = new NumberButton(x);
            if (x == 0) {
                buttonsGrid.add(button, 0, 9);
            } else {
                buttonsGrid.add(button, 0, x - 1);
            }
            this.addButtonEvent(button);
        }
        logger.info("Siemanko.jpeg");
    }

    private void addButtonEvent(final NumberButton button) {
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
                    if (game.getSudokuBoard().isSolved()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("You won");
                        alert.setHeaderText(null);
                        alert.setContentText("Sudoku solved, congratulation");
                        alert.showAndWait();
                    }
                }
            }
        });
    }

    private void initializeSudokuGrid() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                GridPane grid = new GridPane();
                grid.getStyleClass().add("sudoku-grid-border");
                GridPane.setConstraints(grid, y, x);
                sudokuGrid.getChildren().add(grid);
                for (int w = 0; w < 3; w++) {
                    for (int z = 0; z < 3; z++) {
                        int computedX = x * 3 + w;
                        int computedY =  y * 3 + z;
                        FieldPane pane = new FieldPane(computedX, computedY,
                                Integer.toString(this.game.getSudokuBoard().getValue(computedX, computedY)));
                        this.addSudokuFieldEvent(pane);
                        GridPane.setConstraints(pane, z, w);
                        grid.getChildren().add(pane);
                        try {
                            Bindings.bindBidirectional(pane.getLabel().textProperty(),
                                    JavaBeanIntegerPropertyBuilder.create().bean(this.game.getSudokuBoard().
                                            getField(computedX, computedY)).name("value").build(), new CustomConverter());
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Exception while binding label text property with model SudokuField value.");
                        }
                    }
                }
            }
        }
    }

    private void addSudokuFieldEvent(final FieldPane pane) {
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FieldPane pane = (FieldPane) event.getSource();
                if (pane.isChangeable()) {
                    for (Node node : sudokuGrid.getChildren()) {
                        GridPane innerGrid = (GridPane) node;
                        for (Node field : innerGrid.getChildren()) {
                            if (field != pane) {
                                field.getStyleClass().remove("sudokuFieldSelected");
                            }
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
            }
        });
    }

    @FXML
    public void onResetGameButtonClicked() {
        for (Node node : sudokuGrid.getChildren()) {
            GridPane innerGrid = (GridPane) node;
            for (Node field : innerGrid.getChildren()) {
                FieldPane pane = (FieldPane) field;
                if (pane.isChangeable()) {
                    pane.setLabelText("0");
                }
            }
        }
    }

    @FXML
    public void onNewGameButtonClicked() {
        Stage stage = (Stage) this.sudokuGrid.getScene().getWindow();
        stage.close();
        WindowManager.getInstance().getPrimaryStage().show();
    }

    @FXML
    public void onSaveStateButtonClicked() {
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(WindowManager.getInstance().SAVE_FILE_PATH);
        dao.write(this.game.getSudokuBoard());
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(WindowManager.SAVE_HELPER_FILE_PATH));
            out.write(generateFieldsProperties());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game saved");
        alert.setContentText("Your game has been successfully saved.");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private String generateFieldsProperties() {
        StringBuilder str = new StringBuilder();
        for (Node node : sudokuGrid.getChildren()) {
            GridPane innerGrid = (GridPane) node;
            for (Node field : innerGrid.getChildren()) {
                FieldPane pane = (FieldPane) field;
                str.append(Integer.toString(pane.getX()) + ",");
                str.append(Integer.toString(pane.getY()) + ",");
                str.append(Boolean.toString(pane.isChangeable()) + "\n");
            }
        }
        return str.toString();
    }
}
