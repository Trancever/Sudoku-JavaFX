package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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
            sudokuBoard.setValue(row, col, num);
            if (isSafe(row, col, sudokuBoard)) {
                if (solve(sudokuBoard)) {
                    return true;
                }
                sudokuBoard.setValue(row,  col, 0);
            }
            sudokuBoard.setValue(row,  col, 0);
        }
        return false;
    }

    private int[] findUnassignedLocation(final SudokuBoard sudokuBoard) {
        int[] tmp = new int[3];
        int row, col = 0;
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                if (sudokuBoard.getValue(row, col) == 0) {
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

    private boolean isSafe(final int row, final int col, final SudokuBoard sudokuBoard) {
        return sudokuBoard.getRow(row).verify() && sudokuBoard.getColumn(col).verify() && sudokuBoard.getBox(row, col).verify();
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).toString();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        return this == obj;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
    
}