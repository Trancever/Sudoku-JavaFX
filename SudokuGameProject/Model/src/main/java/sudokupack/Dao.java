package sudokupack;

public interface Dao<T> {

    // Serialize object of type T.
    public T read();

    // Deserialize object of type T.
    public void write(final T obj);
}
