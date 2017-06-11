package sample.helpers;

/**
 * GameLevel enum represents SudokuGame level
 */
public enum GameLevel {

    EASY(10),
    MEDIUM(40),
    HARD(50);

    /**
     * number of fields to delete
     */
    private int numberOfFieldsToDelete;

    private GameLevel(final int numberOfFieldsToDelete) {
        this.numberOfFieldsToDelete = numberOfFieldsToDelete;
    }

    /**
     * getNumberOfFieldsToDelete
     * @return number of fields that have to be deleted on this level
     */
    public int getNumberOfFieldsToDelete() {
        return this.numberOfFieldsToDelete;
    }
}
