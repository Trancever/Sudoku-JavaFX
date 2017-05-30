package exceptions;

/**
 * Created by Trensik on 5/25/2017.
 */
public class SudokuDaoException extends RuntimeException {

    public SudokuDaoException() { }

    public SudokuDaoException(final String message) {
        super(message);
    }
}
