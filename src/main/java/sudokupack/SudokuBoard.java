package sudokupack;

public class SudokuBoard {

    private int[][] field = new int[9][9];

    public SudokuBoard() {
        initBoard();
    }

    private void initBoard() {
        int[] tmp = new int[3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = 0;
            }
        }
        do {
            tmp[0] = generateNumber(1, 9);
            tmp[1] = generateNumber(1, 9);
            tmp[2] = generateNumber(1, 9);
        } while ((tmp[0] == tmp[1]) || (tmp[1] == tmp[2]) || (tmp[0] == tmp[2]));
        for (int i = 0; i < 3; i++) {
            field[0][i] = tmp[i];
        }
    }

    public int[][] getAll() {
        int[][] tmp = field;
        return tmp;
    }
    
    public int getValue(final int row, final int col) {
        int value = field[row][col];
        return value;
    }
    
    public void setValue(final int row, final int col, final int value) {
        this.field[row][col] = value;
    }

    private int generateNumber(final int lowest, final int highest) {
        return lowest + (int) (Math.random() * highest);
    }
}
