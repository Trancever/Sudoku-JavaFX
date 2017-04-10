package sudokuPackTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sudokupack.BackTrackingSudokuSolver;
import sudokupack.FileSudokuBoardDao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

public class FileSudokuBoardDaoTest {

    private static final String FILENAME  = "Resources/testSerializedSudokuBoard.data";

    private FileSudokuBoardDao fileSudokuBoardDao;

    @Before
    public void init() {
        fileSudokuBoardDao = new FileSudokuBoardDao(FILENAME);
    }

    @Test
    public void writeObjectToFile() {
        SudokuBoard board = new SudokuBoard();
        fileSudokuBoardDao.write(board);
        System.out.println("Done hehe");
    }

    @Test
    public void readObjectFromFile() {
        SudokuBoard board = fileSudokuBoardDao.read();
        System.out.println("Wczytany");
    }

    @Test
    public void test() {
        SudokuSolver solver = new BackTrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard();
        solver.solve(board);
        fileSudokuBoardDao.write(board);
        SudokuBoard board2 = fileSudokuBoardDao.read();

        Assert.assertTrue(board.equals(board2));
    }
}
