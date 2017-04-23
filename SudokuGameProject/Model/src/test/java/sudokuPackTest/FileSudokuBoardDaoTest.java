package sudokuPackTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sudokupack.BackTrackingSudokuSolver;
import sudokupack.FileSudokuBoardDao;
import sudokupack.SudokuBoard;
import sudokupack.SudokuSolver;

import java.io.File;

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
        File file = new File(FILENAME);
        Assert.assertTrue(file.exists() && !file.isDirectory());
    }

    @Test
    public void readObjectFromFile() {
        SudokuBoard board = fileSudokuBoardDao.read();
        Assert.assertTrue(board != null);
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
