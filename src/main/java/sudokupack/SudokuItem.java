package sudokupack;

import java.util.HashSet;

public class SudokuItem {
	
	private SudokuField[] values;
	
	public SudokuItem(final SudokuField[] values) {
		this.values = values;
	}
	
	public boolean verify() {
		HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            if (!set.add(values[i].getValue())) {
            	return false;
            }
        }
        return true;
	}
}
