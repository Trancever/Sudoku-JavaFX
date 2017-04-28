package game;

public enum GameLevel {

    EASY(20),
    MEDIUM(40),
    HARD(60);

    private int numberOfFieldsToDelete;

    private GameLevel(final int numberOfFieldsToDelete) {
        this.numberOfFieldsToDelete = numberOfFieldsToDelete;
    }

    public int getNumberOfFieldsToDelete() {
        return this.numberOfFieldsToDelete;
    }
}
