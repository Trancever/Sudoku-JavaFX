package sudokupack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SudokuItem {

    private ArrayList<SudokuField> values;

    public SudokuItem(final ArrayList<SudokuField> values) {
        this.values = values;
    }

    public boolean verify() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getValue() != 0) {
                if (!set.add(values.get(i).getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
}
