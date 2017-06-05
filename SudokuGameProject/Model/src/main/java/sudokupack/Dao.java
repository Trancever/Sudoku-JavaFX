package sudokupack;

public interface Dao<T> {

    T read(final String name);

    // Deserialize object of type T.
    void write(final T obj, final String name);
}
