package sudokuPackTest;

import sudokupack.BackTrackingSudokuSolver;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

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
        assertFalse(Arrays.equals(sudokuBoard1.getAll(), sudokuBoard2.getAll()));
    }

    @Test
    public void testRow() {
        Integer[] test = new Integer[9];
        int col = (int) (Math.random() * 9);
        for (int i = 0; i < 9; i++) {
            test[i] = sudokuBoard1.getValue(i, col);
        }
        HashSet<Integer> test2 = new HashSet<Integer>(Arrays.asList(test));
        assertEquals(test.length, test2.size());
    }

    @Test
    public void testCol() {
        Integer[] test = new Integer[9];
        int row = (int) (Math.random() * 9);
        for (int i = 0; i < 9; i++) {
            test[i] = sudokuBoard1.getValue(row, i);
        }
        HashSet<Integer> test2 = new HashSet<Integer>(Arrays.asList(test));
        assertEquals(test.length, test2.size());
    }

    @Test
    public void testBox() {
        Integer[] test = new Integer[9];
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                test[j + i * 3] = sudokuBoard1.getValue(i + row * 3, j + col * 3);
            }
        }
        HashSet<Integer> test2 = new HashSet<Integer>(Arrays.asList(test));
        assertEquals(test.length, test2.size());
    }
    
    @Test
    public void testPrint() {
    	sudokuBoard1.print();
    }

}
