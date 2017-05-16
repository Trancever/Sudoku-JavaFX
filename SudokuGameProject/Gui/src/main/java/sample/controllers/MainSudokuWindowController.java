package sample.controllers;

import game.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import game.ApplicationSettings;
import javafx.stage.Stage;
import sample.CustomWidgets.FieldPane;
import sample.CustomWidgets.NumberButton;
import sample.WindowManager;
import sudokupack.Dao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuBoardDaoFactory;


public class MainSudokuWindowController {

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
        this.game = ApplicationSettings.getInstance().getGame();
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
                    game.getSudokuBoard().setValue(currentSelectedField.getX(), currentSelectedField.getY(), button.getNumber());
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
                        FieldPane pane = new FieldPane(x * 3 + w, y * 3 + z,
                                Integer.toString(this.game.getSudokuBoard().getValue(x * 3 + w, y * 3 + z)));
                        this.addSudokuFieldEvent(pane);
                        GridPane.setConstraints(pane, z, w);
                        grid.getChildren().add(pane);
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
                    this.game.getSudokuBoard().setValue(pane.getX(), pane.getX(), 0);
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
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getInstance().getFileDao(ApplicationSettings.getInstance().SAVE_FILE_PATH);
        dao.write(this.game.getSudokuBoard());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game saved");
        alert.setContentText("Your game has been successfully saved.");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
