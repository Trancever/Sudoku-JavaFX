package sudokupack;

public class SudokuBoard {

    private SudokuField[][] field;

    public SudokuBoard() {
    	field = new SudokuField[9][9];
        initBoard();
    }

    private void initBoard() {
        Integer[] tmp = new Integer[3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new SudokuField();
            }
        }
        do {
            tmp[0] = generateNumber(1, 9);
            tmp[1] = generateNumber(1, 9);
            tmp[2] = generateNumber(1, 9);
        } while ((tmp[0] == tmp[1]) || (tmp[1] == tmp[2]) || (tmp[0] == tmp[2]));
        for (int i = 0; i < 3; i++) {
            field[0][i].setValue(tmp[i]);
        }
    }

    public SudokuField[][] getAll() {
        SudokuField[][] tmp = field;
        return tmp;
    }
    
    public Integer getValue(final int row, final int col) {
        Integer value = field[row][col].getValue();
        return value;
    }
    
    public void setValue(final int row, final int col, final int value) {
        this.field[row][col].setValue(value);
    }

    private int generateNumber(final int lowest, final int highest) {
        return lowest + (int) (Math.random() * highest);
    }
    
    public SudokuRow getRow(final int row) throws IndexOutOfBoundsException {
    	if (row < 1 || row > 9) {
    		throw new IndexOutOfBoundsException("Wrong row. Given = " + row + " expected from 1 to 9.");
    	}
    	return new SudokuRow(field[row]);
    }
    
    public SudokuColumn getColumn(final int column) throws IndexOutOfBoundsException {
    	if (column < 1 || column > 9) {
    		throw new IndexOutOfBoundsException("Wrong column. Given = " + column + " expected from 1 to 9.");
    	}
    	SudokuField[] tmp = new SudokuField[9];
    	for (int i = 0; i < 9; i++) {
    		tmp[i] = field[i][column];
    	}
    	return new SudokuColumn(tmp);
    }
    
    public SudokuBox getBox(final int row, final int column) throws IndexOutOfBoundsException {
    	if (column < 1 || column > 9 || row < 1 || row > 9) {
    		throw new IndexOutOfBoundsException("Wrong Box. Given row = " + row + " expected from 1 to 9. "
    				+ "Given column = " + column + " expected from 1 to 9.");
    	}
    	int divX = (row - 1) / 3;
    	int divY = (column - 1) / 3;
    	SudokuField[] tmp = new SudokuField[9];
    	int counter = 0;
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			tmp[counter] = field[i + divX * 3][j + divY * 3];
    			counter++;
    		}
    	}
    	return new SudokuBox(tmp);
    }
    
    public void print() {
    	for (SudokuField[] value : field) {
    		for (SudokuField v : value) {
    			System.out.print(v.getValue() + " ");
    		}
    		System.out.println("");
    	}
    }
 }
