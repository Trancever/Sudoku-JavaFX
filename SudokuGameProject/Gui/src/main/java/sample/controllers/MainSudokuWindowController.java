package sample.controllers;

import exceptions.SudokuSerializeException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.CustomExceptions.BindingFailedException;
import sample.CustomWidgets.FieldPane;
import sample.CustomWidgets.NumberButton;
import sample.WindowManager;
import sample.helpers.CustomConverter;
import sudokupack.Dao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;

import java.io.File;


public class MainSudokuWindowController {

    static final Logger logger = LoggerFactory.getLogger(MainSudokuWindowController.class);

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
        if (!game.isLoaded()) {
            this.game.cleanFields();
        }
        try {
            this.initializeSudokuGrid();
        } catch (BindingFailedException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        this.initializeButtons();
        logger.debug("MainSudokuWindowController initialized.");
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
    }

    private void addButtonEvent(final NumberButton button) {
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
                NumberButton button = (NumberButton) event.getSource();
                logger.debug(button.toString() + " clicked. Number = " + button.getNumber());
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

    private void initializeSudokuGrid() throws BindingFailedException {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                GridPane grid = new GridPane();
                grid.getStyleClass().add("sudoku-grid-border");
                GridPane.setConstraints(grid, y, x);
                sudokuGrid.getChildren().add(grid);
                for (int w = 0; w < 3; w++) {
                    for (int z = 0; z < 3; z++) {
                        int computedX = x * 3 + w;
                        int computedY = y * 3 + z;
                        FieldPane pane = this.createFieldPane(computedX, computedY);
                        this.addSudokuFieldEvent(pane);
                        GridPane.setConstraints(pane, z, w);
                        grid.getChildren().add(pane);
                        try {
                            Bindings.bindBidirectional(pane.getLabel().textProperty(),
                                    JavaBeanIntegerPropertyBuilder.create().bean(this.game.getSudokuBoard().
                                            getField(computedX, computedY)).name("value").build(), new CustomConverter());
                        } catch (NoSuchMethodException e) {
                            throw new BindingFailedException("BindingFailedException");
                        }
                    }
                }
            }
        }
    }

    private FieldPane createFieldPane(final int x, final int y) {
        return new FieldPane(x, y, Integer.toString(this.game.getSudokuBoard().getValue(x, y)),
                                                        this.game.getSudokuBoard().getField(x, y).getChangeable());
    }

    private void addSudokuFieldEvent(final FieldPane pane) {
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(final MouseEvent event) {
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
                    logger.debug(pane.toString() + " clicked. Position " + pane.getX() + " " + pane.getY());
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
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select location where you want to save your game.");
            fileChooser.setInitialFileName("Sudoku-save");
            fileChooser.getExtensionFilters().add(new FileChooser.
                                    ExtensionFilter("Default Sudoku game save extension", "*.xD"));
            File file = fileChooser.showSaveDialog(this.buttonsGrid.getScene().getWindow());
            if (file == null) {
                return;
            }
            Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(file.getPath());
            dao.write(this.game.getSudokuBoard());
        } catch (SudokuSerializeException e) {
            logger.error(e.getLocalizedMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("We have encountered error while trying to save the game.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game saved");
        alert.setContentText("Your game has been successfully saved.");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
