package sudokupack;

public interface Dao<T> {

    // Serialize object of type T.
    T read();

    // Deserialize object of type T.
    void write(final T obj);
}
