package sudokupack;

/**
 * SudokuSolver is an interface which can solve the SudokuBoard
 */
public interface SudokuSolver {

    /**
     * solve gets SudokuBoard and solve it
     * @param sudokuBoard SudokuBoard instance which has to be solved
     * @return true if solved corretly, false if not solved correctly
     */
    public boolean solve(SudokuBoard sudokuBoard);
    
}
