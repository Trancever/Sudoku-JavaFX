package sudokuPackTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import sudokupack.*;

import org.junit.Test;

import java.util.ArrayList;

public class SudokuItemTest {

    @Test
    public void sudokuRowVerifyWithUniqueValuesTest() {
<<<<<<< HEAD
        List<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField(i));
=======
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField());
            fields.get(i - 1).setValue(i);
>>>>>>> Change Arrays to ArrayLists in all methods
        }
        SudokuItem sudokuItem = new SudokuItem(fields);
        Assert.assertTrue(sudokuItem.verify());
    }

    @Test
    public void sudokuRowVerifyWithNotUniqueValuesTest() {
<<<<<<< HEAD
    	List<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField(i%2));
=======
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (int i = 1; i <= 9; i++) {
            fields.add(new SudokuField());
            fields.get(i - 1).setValue(i%2);
>>>>>>> Change Arrays to ArrayLists in all methods
        }
        SudokuItem sudokuItem = new SudokuItem(fields);
        Assert.assertFalse(sudokuItem.verify());
    }
}
