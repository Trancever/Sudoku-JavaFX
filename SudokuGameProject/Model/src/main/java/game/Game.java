package game;

import sudokupack.BackTrackingSudokuSolver;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

import java.util.List;

public class Game {

    private GameLevel gameLevel;
    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;
    private boolean isLoaded;
    private List<List<Boolean>> loadingHelperList;

    public boolean isLoaded() {
        return isLoaded;
    }

    public Game(final GameLevel gameLevel, final SudokuBoard sudokuBoard, final boolean isLoaded, final List<List<Boolean>> helperList) {
        this.gameLevel = gameLevel;
        this.sudokuBoard = sudokuBoard;
        this.sudokuSolver = new BackTrackingSudokuSolver();
        this.isLoaded = isLoaded;
        if (!this.isLoaded) {
            this.sudokuSolver.solve(this.sudokuBoard);
        }
        loadingHelperList = helperList;
    }

    public SudokuBoard getSudokuBoard() {
        return this.sudokuBoard;
    }

    public void cleanFields() {
        sudokuBoard.cleanRandomlyFields(gameLevel.getNumberOfFieldsToDelete());
    }

    public boolean getHelperValue(final int x, final int y) {
        return this.loadingHelperList.get(x).get(y);
    }
}
