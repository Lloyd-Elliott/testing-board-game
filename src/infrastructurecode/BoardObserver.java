package src.infrastructurecode;

public interface BoardObserver {
    void onBoardCreated(String boardType, String NumberOfPlayers);
}
