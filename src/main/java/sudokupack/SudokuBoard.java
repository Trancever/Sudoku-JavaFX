package sudokupack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    //private SudokuField[][] field;
    private ArrayList<ArrayList<SudokuField>> field;


    public SudokuBoard() {
        //field = new ArrayList(9);
        //for (int i = 0; i < 9; i++) {
        //    field.set(i, new ArrayList[9]);
        //}

        //field = new ArrayList<ArrayList<SudokuField>>(
        //        Collections.nCopies(9, new ArrayList<SudokuField>(Collections.nCopies(9, new SudokuField()))));

        field = new ArrayList<ArrayList<SudokuField>>();


        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 9; i++) {
            //field.set(i, new ArrayList<SudokuField>());
            field.add(new ArrayList<SudokuField>());
            for (int j = 0; j < 9; j++) {
                //field.get(i).set(j, new SudokuField());
                field.get(i).add(new SudokuField());
            }
        }
        int[] tmp = new int[3];
        do {
            tmp[0] = generateNumber(1, 9);
            tmp[1] = generateNumber(1, 9);
            tmp[2] = generateNumber(1, 9);
        } while ((tmp[0] == tmp[1]) || (tmp[1] == tmp[2]) || (tmp[0] == tmp[2]));
        for (int i = 0; i < 3; i++) {
            //field[0][i].setValue(tmp[i]);
            field.get(0).get(i).setValue(tmp[i]);
        }
    }

    public ArrayList<ArrayList<SudokuField>> getAll() {
        //SudokuField[][] tmp = field;
        ArrayList<ArrayList<SudokuField>> tmp = field;
        return tmp;
    }

    public int getValue(final int row, final int col) {
        if (col < 0 || col > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of getValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        //Integer value = field[row][col].getValue();
        Integer value = this.field.get(row).get(col).getValue();
        return value;
    }

    public void setValue(final int row, final int col, final int value) throws IndexOutOfBoundsException {
        if (col < 0 || col > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of setValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        //this.field[row][col].setValue(value);
        this.field.get(row).get(col).setValue(value);
    }

    private int generateNumber(final int lowest, final int highest) {
        return lowest + (int) (Math.random() * highest);
    }

    public SudokuRow getRow(final int row) throws IndexOutOfBoundsException {
        if (row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong row. Given = " + row + " expected from 0 to 8.");
        }
        //return new SudokuRow(field[row]);
        return new SudokuRow(this.field.get(row));
    }

    public SudokuColumn getColumn(final int column) throws IndexOutOfBoundsException {
        if (column < 0 || column > 8) {
            throw new IndexOutOfBoundsException("Wrong column. Given = " + column + " expected from 0 to 8.");
        }
        ArrayList<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < 9; i++) {
            //tmp[i] = field[i][column];
            tmp.add(this.field.get(i).get(column));
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
        ArrayList<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //tmp[counter] = field[i + divX * 3][j + divY * 3];
                tmp.add(this.field.get(i + divX * 3).get(j + divY * 3));
            }
        }
        return new SudokuBox(tmp);
    }
}