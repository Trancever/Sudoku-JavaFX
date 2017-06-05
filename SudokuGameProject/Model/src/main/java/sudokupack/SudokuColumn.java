package sudokupack;

import java.util.ArrayList;

/**
 * SudokuColumn is a class representing single sudoku column
 */
public class SudokuColumn extends SudokuItem implements Cloneable {

    /**
     * Constructor of SudokuColumn
     * @param values values is List of SudokuFields which represents row in Sudoku
     */
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
