package sudokupack;

public class SudokuField {

private Integer field;

    public SudokuField() {
        field = new Integer(0);
    }
    public SudokuField(final int value) {
    	field = value;
    }

    public int getValue() {
        int value = field.intValue();
        return value;
    }

    public void setValue(final int value) {
        field = new Integer(value);
    }
}
