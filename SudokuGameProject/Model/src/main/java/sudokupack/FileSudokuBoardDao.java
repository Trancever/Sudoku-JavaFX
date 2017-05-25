package sudokupack;

import exceptions.SudokuDaoException;
import exceptions.SudokuDeserializeException;
import exceptions.SudokuSerializeException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    public FileSudokuBoardDao(final String filename) {
        this.filename = filename;
    }

    public SudokuBoard read() throws SudokuDeserializeException {
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            SudokuBoard board = (SudokuBoard) objectInputStream.readObject();
            return board;
        } catch (FileNotFoundException e) {
            throw new SudokuDeserializeException("File " + filename + " not found.");
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

    public void write(final SudokuBoard obj) throws SudokuSerializeException {
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
        } catch (FileNotFoundException ex) {
            throw new SudokuSerializeException("File " + filename + " not found.");
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
