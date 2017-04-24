package sudokupack;

import java.util.ArrayList;

public class SudokuRow extends SudokuItem implements Cloneable {

    public SudokuRow(final ArrayList<SudokuField> values) {
        super(values);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (SudokuField field : values) {
            fields.add((SudokuField) field.clone());
        }
        return new SudokuRow(fields);
    }
}
