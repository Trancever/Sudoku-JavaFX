package sudokupack;

public class BackTrackingSudokuSolver implements SudokuSolver {

    public boolean solve(final SudokuBoard sudokuBoard) {
        int row, col;

        int[] tmp = findUnassignedLocation(sudokuBoard);

        row = tmp[1];
        col = tmp[2];

        if (tmp[0] == 0) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num, sudokuBoard)) {
                sudokuBoard.setValue(row,  col, num);
                if (solve(sudokuBoard)) {
                    return true;
                }
                sudokuBoard.setValue(row,  col, 0);
            }
        }
        return false;
    }

    private int[] findUnassignedLocation(final SudokuBoard sudokuBoard) {
        int[] tmp = new int[3];
        int row, col = 0;
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                if (sudokuBoard.getValue(row,  col) == 0) {
                    tmp[0] = 1;
                    tmp[1] = row;
                    tmp[2] = col;
                    return tmp;
                }
            }
        }
        tmp[0] = 0;
        tmp[1] = row;
        tmp[2] = col;
        return tmp;
    }

    private boolean usedInRow(final int row, final int num, final SudokuBoard sudokuBoard) {
        for (int col = 0; col < 9; col++) {
            if (sudokuBoard.getValue(row,  col) == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(final int col, final int num, final SudokuBoard sudokuBoard) {
        for (int row = 0; row < 9; row++) {
            if (sudokuBoard.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(final int boxStartRow, final int boxStartCol, final int num, final SudokuBoard sudokuBoard) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (sudokuBoard.getValue(row + boxStartRow, col + boxStartCol) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSafe(final int row, final int col, final int num, final SudokuBoard sudokuBoard) {
        return !usedInRow(row, num, sudokuBoard) && !usedInCol(col, num, sudokuBoard) && !usedInBox(row - row % 3, col - col % 3, num, sudokuBoard);
    }
    
}
