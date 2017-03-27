package sudokuPackTest;

import org.junit.Assert;
import sudokupack.*;

import org.junit.Test;

public class SudokuItemTest {

    @Test
    public void sudokuRowVerifyWithUniqueValuesTest() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i <= fields.length; i++) {
            fields[i-1] = new SudokuField();
            fields[i-1].setValue(i);
        }
        SudokuItem sudokuItem = new SudokuItem(fields);
        Assert.assertTrue(sudokuItem.verify());
    }

    @Test
    public void sudokuRowVerifyWithNotUniqueValuesTest() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 1; i <= fields.length; i++) {
            fields[i-1] = new SudokuField();
            fields[i-1].setValue(i%2);
        }
        SudokuItem sudokuItem = new SudokuItem(fields);
        Assert.assertFalse(sudokuItem.verify());
    }
}
