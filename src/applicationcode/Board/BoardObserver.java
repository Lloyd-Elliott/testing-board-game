package src.applicationcode.Board;

public interface BoardObserver {
    void onBoardCreated(String boardType, String NumberOfPlayers);
}
