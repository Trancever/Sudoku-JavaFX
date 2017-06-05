package sudokupack;

import java.util.ArrayList;

/**
 * SudokuRow is a class representing single sudoku row
 */
public class SudokuRow extends SudokuItem implements Cloneable {

    /**
     * Constructor of SudokuRow
     * @param values values is List of SudokuFields which represents row in Sudoku
     */
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
