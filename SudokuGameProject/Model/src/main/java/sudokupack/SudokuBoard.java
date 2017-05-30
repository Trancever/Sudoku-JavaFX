package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.Serializable;

@Entity
public class SudokuBoard implements Serializable, Cloneable {

    @Transient
    private static final int ROWS = 9;

    @Transient
    private static final int COLUMNS = 9;

    @Id
    private String id;

    @Transient
    private ArrayList<ArrayList<SudokuField>> field;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<SudokuField> linearBoard;

    public SudokuBoard() {
        field = new ArrayList<ArrayList<SudokuField>>();
        linearBoard = new ArrayList<SudokuField>();
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < ROWS; i++) {
            field.add(new ArrayList<SudokuField>());
            for (int j = 0; j < COLUMNS; j++) {
                SudokuField sudokuField = new SudokuField();
                sudokuField.setSudokuBoard(this);
                field.get(i).add(sudokuField);
            }
        }
        int[] tmp = new int[3];
        Random random = new Random();
        do {
            tmp[0] = random.nextInt(ROWS);
            tmp[1] = random.nextInt(ROWS);
            tmp[2] = random.nextInt(ROWS);
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
        if (col < 0 || col > 8 || row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of getValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        int value = this.field.get(row).get(col).getValue();
        return value;
    }

    public SudokuField getField(final int row, final int col) {
        return this.field.get(row).get(col);
    }

    public void setValue(final int row, final int col, final int value) throws IndexOutOfBoundsException {
        if (col < 0 || col > 8 || row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong parameters of setValue. Given row = " + row
                    + " expected from 0 to 8. " + "Given column = " + col + " expected from 0 to 8.");
        }
        this.field.get(row).get(col).setValue(value);
    }

    public SudokuRow getRow(final int row) throws IndexOutOfBoundsException {
        if (row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("Wrong row. Given = " + row + " expected from 0 to 8.");
        }
        ArrayList<SudokuField> tmp = new ArrayList<SudokuField>();
        for (int i = 0; i < COLUMNS; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setSudokuBoard(this);
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
        for (int i = 0; i < ROWS; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setSudokuBoard(this);
            tmp.add(new SudokuField());
            tmp.get(i).setValue(field.get(i).get(column).getValue());
        }
        return new SudokuColumn(tmp);
    }

    public SudokuBox getBox(final int row, final int column) throws IndexOutOfBoundsException {
        if (column < 0 || column > 8 || row < 0 || row > 8) {
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

    public void cleanRandomlyFields(final int amount) {
        Random random = new Random();
        int tmp = amount;
        while (tmp > 0) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLUMNS);
            if (this.getValue(row, col) != 0) {
                this.setValue(row, col, 0);
                this.getField(row, col).setChangeable(true);
                tmp--;
            }
        }
    }

    public boolean isSolved() {
        boolean solved = true;
        int i = 0;
        int j = 0;
        for (; i < ROWS; i++, j++) {
            if (!this.getRow(i).isSolved() || !this.getColumn(j).isSolved()) {
                return false;
            }
        }
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (!this.getBox(i, j).isSolved()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void convert2dto1d() {
        for(int i = 0; i < ROWS; i ++) {
            for(int j = 0; j < COLUMNS; j++) {
                this.linearBoard.add(this.field.get(i).get(j));
            }
        }
    }

    public void convert1dto2d() {
        for(int i = 0; i < linearBoard.size(); i ++) {
            int x = i % 9;
            int y = i / 9;
            this.setValue(y, x, this.linearBoard.get(i).getValue());
            this.field.get(y).get(x).setChangeable(this.linearBoard.get(i).getChangeable());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayList<ArrayList<SudokuField>> newList = new ArrayList<ArrayList<SudokuField>>();
        for (int i = 0; i < ROWS; i++) {
            newList.add(new ArrayList<SudokuField>());
            for (int j = 0; j < COLUMNS; j++) {
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
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
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

    public String print() {
        StringBuilder builder = new StringBuilder();
        for (List<SudokuField> x : field) {
            for (SudokuField y : x) {
                builder.append(Integer.toString(y.getValue()) + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}