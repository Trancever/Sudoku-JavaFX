package game;

import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

public class Game {

    private GameLevel gameLevel;
    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;
    private int currentTime;

    public Game(final GameLevel gameLevel, final SudokuSolver sudokuSolver) {
        this.gameLevel = gameLevel;
        //Tu pewnie też trzeba będzie jakoś ogarnąć wczytywanie z pliku zapisanej gry ale to zostawimy na pozniej
        this.sudokuBoard = new SudokuBoard();
        this.sudokuSolver = sudokuSolver;
        this.sudokuSolver.solve(this.sudokuBoard);
    }

    public SudokuBoard getSudokuBoard() {
        return this.sudokuBoard;
    }

    public void cleanFields() {
        sudokuBoard.cleanRandomlyFields(gameLevel.getNumberOfFieldsToDelete());
    }
}
