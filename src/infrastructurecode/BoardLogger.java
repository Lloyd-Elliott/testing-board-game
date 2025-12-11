package src.infrastructurecode;

public class BoardLogger implements BoardObserver {
    
    @Override
    public void onBoardCreated(String boardType , String NumberOfPlayers) {
        System.out.println("Board created: " + boardType + " with " + NumberOfPlayers + " players.");
        // Additional logging logic can go here
    }
}
