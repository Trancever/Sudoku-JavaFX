package sudokupack;

public interface Dao<T> {

    // Serialize object of type T.
    T read(final String name);

    // Deserialize object of type T.
    void write(final T obj, final String name);
}
