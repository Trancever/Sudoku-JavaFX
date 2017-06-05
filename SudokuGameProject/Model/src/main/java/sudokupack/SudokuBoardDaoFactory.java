package sudokupack;

/**
 * SudokuBoardDaoFactory is a singleton factory of Dao objects
 */
public class SudokuBoardDaoFactory {

    private static SudokuBoardDaoFactory instance = new SudokuBoardDaoFactory();

    private SudokuBoardDaoFactory() { }

    public static SudokuBoardDaoFactory getInstance() {
        return instance;
    }

    public Dao<SudokuBoard> getFileDao() {
        return new FileSudokuBoardDao();
    }

    public Dao<SudokuBoard> getJDBCDao() {
        return new JDBCSudokuBoardDao(PersistanceUnit.NAME);
    }
}
