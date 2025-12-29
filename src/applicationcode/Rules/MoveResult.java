package src.applicationcode.Rules;

public class MoveResult {
    private final int oldPosition;
    private final int newPosition;
    private final boolean enteredTail;
    private final int tailPosition;
    private final boolean completedGame;
    private final boolean overshot;
    private final boolean collision;
    private final int blockedPosition;
    
    public MoveResult(int oldPosition, int newPosition, boolean enteredTail, int tailPosition, boolean completedGame, boolean overshot, boolean collision, int blockedPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.enteredTail = enteredTail;
        this.tailPosition = tailPosition;
        this.completedGame = completedGame;
        this.overshot = overshot;
        this.collision = collision;
        this.blockedPosition = blockedPosition;
    }
    
    public int getOldPosition() {
        return oldPosition;
    }
    
    public int getNewPosition() {
        return newPosition;
    }
    
    public boolean isEnteredTail() {
        return enteredTail;
    }
    
    public int getTailPosition() {
        return tailPosition;
    }
    
    public boolean isCompletedGame() {
        return completedGame;
    }
    
    public boolean isOvershot() {
        return overshot;
    }
    
    public boolean isCollision() {
        return collision;
    }
    
    public int getBlockedPosition() {
        return blockedPosition;
    }
}
