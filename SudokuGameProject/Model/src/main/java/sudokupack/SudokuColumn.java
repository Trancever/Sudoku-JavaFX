package sudokupack;

import java.util.ArrayList;

public class SudokuColumn extends SudokuItem implements Cloneable {

    public SudokuColumn(final ArrayList<SudokuField> values) {
        super(values);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayList<SudokuField> fields = new ArrayList<SudokuField>();
        for (SudokuField field : values) {
            fields.add((SudokuField) field.clone());
        }
        return new SudokuColumn(fields);
    }
}
