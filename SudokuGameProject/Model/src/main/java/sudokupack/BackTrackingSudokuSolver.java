package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Random;

/**
 * BackTrackingSudokuSolver is an implementation of SudokuSolver interface
 */
public class BackTrackingSudokuSolver implements SudokuSolver {

    private Random random = new Random();

    /**
     * solve try to solve SUdokuBoard
     * @param sudokuBoard SudokuBoard instance which has to be solved
     * @return true if succeded, false if failed
     */
    public boolean solve(final SudokuBoard sudokuBoard) {
        int row, col;

        int[] tmp = findUnassignedLocation(sudokuBoard);

        row = tmp[1];
        col = tmp[2];

        if (tmp[0] == 0) {
            return true;
        }
        int offset = random.nextInt(9) + 1;
        for (int num = 1; num <= 9; num++) {
            int value = 0;
            if ((num + offset) <= 9) {
                value = num + offset;
            } else {
                value = Math.abs(num - offset);
            }
            sudokuBoard.setValue(row, col, value);
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

    /**
     * findUnassignedLocation finds SudokuFields with 0 value
     * @param sudokuBoard board that contain SudokuFields
     * @return int array with 3 values, array[0] => field value, array[1] => field row, array[2] => field column
     */
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

    /**
     * isSafe checks if row, column and box are filled correctly
     * @param row row of the SudokuBoard
     * @param col column of the SudokuBoard
     * @param sudokuBoard SudokuBoard instance
     * @return true if filled corretly, false otherwise
     */
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