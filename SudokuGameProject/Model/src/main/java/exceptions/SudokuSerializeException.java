package exceptions;

/**
 * Created by Trensik on 5/25/2017.
 */
public class SudokuSerializeException extends SudokuDaoException {

    public SudokuSerializeException() { }

    public SudokuSerializeException(final String message) {
        super(message);
    }
}
