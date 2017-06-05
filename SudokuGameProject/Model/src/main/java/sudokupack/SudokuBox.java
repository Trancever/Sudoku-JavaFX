package sudokupack;

import java.util.ArrayList;

/**
 * SudokuBox is a class representing single Sudoku Box
 */
public class SudokuBox extends SudokuItem implements Cloneable {

    /**
     * Constructor of SudokuColumn
     * @param values values is List of SudokuFields which represents row in Sudoku
     */
    public SudokuBox(final ArrayList<SudokuField> values) {
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
