package src.infrastructurecode;

public interface PlayerMovementObserver {
    void onPlayerMoved(String playerName, int oldPosition, int newPosition);
}
