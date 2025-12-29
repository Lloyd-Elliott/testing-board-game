package src.infrastructurecode;

import src.applicationcode.Board.BoardObserver;

public class BoardLogger implements BoardObserver {
    
    @Override
    public void onBoardCreated(String boardType , String NumberOfPlayers) {
        System.out.println("=== Board Game Starting ===\n");
        System.out.println("Board created: " + boardType + " with " + NumberOfPlayers + " players.");
    }
}
