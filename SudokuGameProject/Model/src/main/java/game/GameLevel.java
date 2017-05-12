package game;

public enum GameLevel {

    EASY(30),
    MEDIUM(40),
    HARD(50);

    private int numberOfFieldsToDelete;

    private GameLevel(final int numberOfFieldsToDelete) {
        this.numberOfFieldsToDelete = numberOfFieldsToDelete;
    }

    public int getNumberOfFieldsToDelete() {
        return this.numberOfFieldsToDelete;
    }
}
