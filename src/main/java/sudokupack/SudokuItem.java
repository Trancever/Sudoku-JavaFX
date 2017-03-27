package sudokupack;

import java.util.HashSet;
import java.util.List;

public class SudokuItem {

    private List<SudokuField> values;

    public SudokuItem(final List<SudokuField> values) {
        this.values = values;
    }

    public boolean verify() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (SudokuField field : values) {
            if (field.getValue() != 0) {
                if (!set.add(field.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
}
