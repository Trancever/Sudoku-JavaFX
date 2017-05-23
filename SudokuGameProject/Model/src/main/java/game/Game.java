package game;

import sudokupack.BackTrackingSudokuSolver;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

public class Game {

    private GameLevel gameLevel;
    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;
    private boolean isLoaded;

    public boolean isLoaded() {
        return isLoaded;
    }

    public Game(final GameLevel gameLevel, final SudokuBoard sudokuBoard, final boolean isLoaded) {
        this.gameLevel = gameLevel;
        this.sudokuBoard = sudokuBoard;
        this.sudokuSolver = new BackTrackingSudokuSolver();
        if (!isLoaded) {
            this.sudokuSolver.solve(this.sudokuBoard);
        }
    }

    public SudokuBoard getSudokuBoard() {
        return this.sudokuBoard;
    }

    public void cleanFields() {
        sudokuBoard.cleanRandomlyFields(gameLevel.getNumberOfFieldsToDelete());
    }
}
