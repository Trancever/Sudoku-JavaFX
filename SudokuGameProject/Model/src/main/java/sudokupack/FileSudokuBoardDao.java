package sudokupack;


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

    public SudokuBoard read() {
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            SudokuBoard board = (SudokuBoard) objectInputStream.readObject();
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(final SudokuBoard obj) {
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);

            System.out.println("Object " + obj.getClass() + " serialized succesfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void finalize() {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
