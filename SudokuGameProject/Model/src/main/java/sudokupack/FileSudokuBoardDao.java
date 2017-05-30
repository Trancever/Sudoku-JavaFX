package sudokupack;

import exceptions.SudokuDaoException;
import exceptions.SudokuDeserializeException;
import exceptions.SudokuSerializeException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    public FileSudokuBoardDao() { }

    public SudokuBoard read(final String name) throws SudokuDeserializeException {
        try {
            fileInputStream = new FileInputStream(name);
            objectInputStream = new ObjectInputStream(fileInputStream);
            SudokuBoard board = (SudokuBoard) objectInputStream.readObject();
            return board;
        } catch (FileNotFoundException e) {
            throw new SudokuDeserializeException("File " + name + " not found.");
        } catch (IOException e) {
            throw new SudokuDeserializeException("Exception while instantiating ObjectInputStream.");
        } catch (ClassNotFoundException e) {
            throw new SudokuDeserializeException("Exception while reading object from file.");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new SudokuDeserializeException("Exception while closing file stream.");
                }
            }

            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new SudokuDeserializeException("Exception while closing object stream.");
                }
            }
        }
    }

    public void write(final SudokuBoard obj, final String name) throws SudokuSerializeException {
        try {
            fileOutputStream = new FileOutputStream(name);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (FileNotFoundException ex) {
            throw new SudokuSerializeException("File " + name + " not found.");
        } catch (IOException ex) {
            throw new SudokuSerializeException("Exception while writing object to file.");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new SudokuSerializeException("Exception while closing file stream.");
                }
            }

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new SudokuSerializeException("Exception while closing object stream.");
                }
            }
        }
    }

    @Override
    public void finalize() throws SudokuDaoException {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new SudokuDaoException("Exception while closing file output stream");
            }
        }

        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                throw new SudokuDaoException("Exception while closing object output stream");
            }
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new SudokuDaoException("Exception while closing file input stream");
            }
        }

        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                throw new SudokuDaoException("Exception while closing object input stream");
            }
        }
    }
}
