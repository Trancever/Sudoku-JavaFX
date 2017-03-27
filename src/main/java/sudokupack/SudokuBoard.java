package sudokupack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private ArrayList<ArrayList<SudokuField>> field;

    public SudokuBoard() {
		field = new ArrayList<ArrayList<SudokuField>>(
				Collections.nCopies(9, new ArrayList<SudokuField>(Collections.nCopies(9, new SudokuField(0)))));
        initBoard();
    }

    private void initBoard() {
        Integer[] tmp = new Integer[3];
        do {
            tmp[0] = generateNumber(1, 9);
            tmp[1] = generateNumber(1, 9);
            tmp[2] = generateNumber(1, 9);
        } while ((tmp[0] == tmp[1]) || (tmp[1] == tmp[2]) || (tmp[0] == tmp[2]));
        for (int i = 0; i < 3; i++) {
            this.setValue(0, i, tmp[i]);
        }
    }

    public ArrayList<ArrayList<SudokuField>> getAll() {
    	ArrayList<ArrayList<SudokuField>> tmp = field;
        return tmp;
    }
    
    public Integer getValue(final int row, final int col) {
        if (col < 0 || col > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of getValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        Integer value = field.get(row).get(col).getValue();
        return value;
    }
    
    public void setValue(final int row, final int col, final int value) throws IndexOutOfBoundsException {
        if (col < 0 || col > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of setValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        this.field.get(row).get(col).setValue(value);
    }

    private int generateNumber(final int lowest, final int highest) {
        return lowest + (int) (Math.random() * highest);
    }
    
    public SudokuRow getRow(final int row) throws IndexOutOfBoundsException {
        if (row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong row. Given = " + row + " expected from 0 to 8.");
        }
        return new SudokuRow(field.get(row));
    }
    
    public SudokuColumn getColumn(final int column) throws IndexOutOfBoundsException {
        if (column < 0 || column > 8) {
            throw new IndexOutOfBoundsException("Wrong column. Given = " + column + " expected from 0 to 8.");
        }
        List<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < 9; i++) {
            tmp.add(field.get(i).get(column));
        }
        return new SudokuColumn(tmp);
    }
    
    public SudokuBox getBox(final int row, final int column) throws IndexOutOfBoundsException {
        if (column < 0 || column > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong Box. Given row = " + row + " expected from 0 to 8. "
                    + "Given column = " + column + " expected from 0 to 8.");
        }
        int divX = row / 3;
        int divY = column / 3;
        List<SudokuField> tmp = new ArrayList<SudokuField>();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp.add(field.get(i + divX * 3).get(j + divY * 3));
                counter++;
            }
        }
        return new SudokuBox(tmp);
    }
    
    public void print() {
    	for (List<SudokuField> fi : field) {
    		for (SudokuField value : fi) {
    			System.out.print(value.getValue());
    			System.out.print(" ");
    		}
    		System.out.println();
    	}
    }
 }
