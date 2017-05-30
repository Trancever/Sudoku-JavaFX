package exceptions;

/**
 * Created by Trensik on 5/25/2017.
 */
public class SudokuDeserializeException extends SudokuDaoException {

    public SudokuDeserializeException() { }

    public SudokuDeserializeException(final String message) {
        super(message);
    }
}
