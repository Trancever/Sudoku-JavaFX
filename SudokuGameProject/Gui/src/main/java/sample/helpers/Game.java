package sample.helpers;

import sudokupack.BackTrackingSudokuSolver;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

/**
 * Game represents single Game in Sudoku Game
 */
public class Game {

    /**
     * gameLevel is level of the game (easy, medium, hard)
     */
    private GameLevel gameLevel;

    /**
     * sudokuBoard is an instance of our Sudoku model
     */
    private SudokuBoard sudokuBoard;

    /**
     * sudokuSolver is used to solve Sudoku when the game starts
     */
    private SudokuSolver sudokuSolver;

    /**
     * isLoaded is a flag that holds info if game is loaded or created new one
     */
    private boolean isLoaded;

    public boolean isLoaded() {
        return isLoaded;
    }

    public Game(final GameLevel gameLevel, final SudokuBoard sudokuBoard, final boolean isLoaded) {
        this.gameLevel = gameLevel;
        this.sudokuBoard = sudokuBoard;
        this.sudokuSolver = new BackTrackingSudokuSolver();
        this.isLoaded = isLoaded;
        if (!this.isLoaded) {
            this.sudokuSolver.solve(this.sudokuBoard);
        }
    }

    public SudokuBoard getSudokuBoard() {
        return this.sudokuBoard;
    }

    /**
     * cleanFields cleans random fields in SudokuBoard model
     */
    public void cleanFields() {
        sudokuBoard.cleanRandomlyFields(gameLevel.getNumberOfFieldsToDelete());
    }
}
