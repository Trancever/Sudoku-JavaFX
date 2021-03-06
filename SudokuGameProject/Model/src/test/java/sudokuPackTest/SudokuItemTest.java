package sudokuPackTest;

import org.junit.Assert;
import sudokupack.*;

import org.junit.Test;

import java.util.ArrayList;

public class SudokuItemTest {

    @Test
    public void sudokuRowVerifyWithUniqueValuesTest() {
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField());
            fields.get(i - 1).setValue(i);
        }
        SudokuItem sudokuItem = new SudokuRow(fields);
        Assert.assertTrue(sudokuItem.verify());
    }

    @Test
    public void sudokuRowVerifyWithNotUniqueValuesTest() {
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField());
            fields.get(i - 1).setValue(i%2);
        }
        SudokuItem sudokuItem = new SudokuRow(fields);
        Assert.assertFalse(sudokuItem.verify());
    }
}
