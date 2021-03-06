package sudokuPackTest;

import org.junit.Assert;
import sudokupack.BackTrackingSudokuSolver;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardTest {

    private SudokuBoard sudokuBoard1;
    private SudokuBoard sudokuBoard2;
    private SudokuSolver sudokuSolver;

    @Before
    public void init() {
        sudokuBoard1 = new SudokuBoard();
        sudokuBoard2 = new SudokuBoard();
        sudokuSolver = new BackTrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard1);
        sudokuSolver.solve(sudokuBoard2);
    }

    @Test
    public void testRepeat() {
        assertFalse(sudokuBoard1.getAll().equals(sudokuBoard2.getAll()));
    }

    @Test
    public void testValuesUniquenessInRow() {
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(sudokuBoard1.getRow(i).verify());
        }
    }

    @Test
    public void testValuesUniquenessInCol() {
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(sudokuBoard1.getColumn(i).verify());
        }
    }

    @Test
    public void testValuesUniquenessInBox() {
        for (int i = 0; i < 9; i++) {
            Assert.assertTrue(sudokuBoard1.getBox(i, i).verify());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getRowWithWrongParameterExpectedException() {
        sudokuBoard1.getRow(-1);
        Assert.fail();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getColumnWithWrongParameterExpectedException() {
        sudokuBoard1.getColumn(9);
        Assert.fail();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getBoxWithWrongParametersExpectedException() {
        sudokuBoard1.getBox(-1, 12);
        Assert.fail();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getValueWithWrongParametersExpectedException() {
        sudokuBoard1.getValue(9, 10);
        Assert.fail();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setValueWithWrongParametersExpectedException() {
        sudokuBoard1.setValue(-1, -3, 5);
        Assert.fail();
    }
}
