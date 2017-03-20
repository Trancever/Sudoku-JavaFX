package sudokupack;

public class SudokuField {

	private Integer field;
	
	public SudokuField() {
		field = new Integer(0);
	}
	
	public int getValue() {
		Integer value = field;
		return value;
	}
	
	public void setValue(Integer value) {
		field = value;
	}
}
