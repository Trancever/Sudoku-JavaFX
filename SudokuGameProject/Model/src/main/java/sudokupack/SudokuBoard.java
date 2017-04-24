package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.lang.reflect.Array;
import java.util.*;
import java.io.Serializable;


public class SudokuBoard implements Serializable, Cloneable {

    private ArrayList<ArrayList<SudokuField>> field;


    public SudokuBoard() {
        field = new ArrayList<ArrayList<SudokuField>>();
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 9; i++) {
            field.add(new ArrayList<SudokuField>());
            for (int j = 0; j < 9; j++) {
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
            field.get(0).get(i).setValue(tmp[i]);
        }
    }

    public ArrayList<ArrayList<SudokuField>> getAll() {
        ArrayList<ArrayList<SudokuField>> tmp = field;
        return tmp;
    }

    public int getValue(final int row, final int col) {
        if (col < 0 || col > 8 || row <  0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of getValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        int value = this.field.get(row).get(col).getValue();
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
        ArrayList<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < 9; i++) {
            tmp.add(new SudokuField());
            tmp.get(i).setValue(field.get(row).get(i).getValue());
        }
        return new SudokuRow(tmp);
    }

    public SudokuColumn getColumn(final int column) throws IndexOutOfBoundsException {
        if (column < 0 || column > 8) {
            throw new IndexOutOfBoundsException("Wrong column. Given = " + column + " expected from 0 to 8.");
        }
        ArrayList<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < 9; i++) {
            tmp.add(new SudokuField());
            tmp.get(i).setValue(field.get(i).get(column).getValue());
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
                tmp.add(this.field.get(i + divX * 3).get(j + divY * 3));
            }
        }
        return new SudokuBox(tmp);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayList<ArrayList<SudokuField>> newList = new ArrayList<ArrayList<SudokuField>>();
        for (int i = 0; i < 9; i++) {
            newList.add(new ArrayList<SudokuField>());
            for (int j = 0; j < 9; j++) {
                newList.get(i).add((SudokuField) field.get(i).get(j).clone());
            }
        }
        SudokuBoard board = new SudokuBoard();
        board.field = newList;
        return board;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("field", field).toString();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        final ArrayList<ArrayList<SudokuField>> list = other.getAll();
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if (field.get(i).get(j).getValue() != list.get(i).get(j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.field);
    }
    
}