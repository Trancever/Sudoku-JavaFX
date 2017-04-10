package sudokupack;

public class SudokuBoardDaoFactory {

    private static SudokuBoardDaoFactory instance = new SudokuBoardDaoFactory();

    private SudokuBoardDaoFactory() { }

    public static SudokuBoardDaoFactory getInstance() {
        return instance;
    }

    public Dao<SudokuBoard> getFileDao(final String filename) {
        return new FileSudokuBoardDao(filename);
    }
}
