package src.applicationcode.Player;

public interface PlayerMovementObserver {
    void onPlayerMoved(String playerName, int oldPosition, int newPosition);
}
