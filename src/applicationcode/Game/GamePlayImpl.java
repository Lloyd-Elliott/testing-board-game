package src.applicationcode.Game;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Rules.RulesStrategy;
import src.applicationcode.Rules.MoveResult;
import java.util.ArrayList;
import java.util.List;

public class GamePlayImpl implements GamePlay {
    
    private final Game game;
    private Player currentPlayer;
    private int currentPlayerIndex = 0;
    private final List<GamePlayObserver> observers = new ArrayList<>();
    
    public GamePlayImpl(Game game) {
        this.game = game;
        this.currentPlayer = game.getPlayers()[0];
    }
    
    public void addObserver(GamePlayObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(GamePlayObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition) {
        // Notify observers about the move
        for (GamePlayObserver observer : observers) {
            observer.onPlayerMoved(player, oldPosition, newPosition);
        }
    }
    
    public void executeMove(int diceRoll) {
        if (hasWinner()) {
            return; // Game is over
        }
        
        // Calculate the move using rules
        MoveResult moveResult = game.getRules().calculateMove(currentPlayer, diceRoll, game.getBoard());
        
        // Update player state
        currentPlayer.moveTo(moveResult.getNewPosition());
        
        if (moveResult.isEnteredTail()) {
            currentPlayer.setInTail(true);
        }
        
        if (currentPlayer.isInTail()) {
            currentPlayer.setTailPosition(moveResult.getTailPosition());
        }
        
        // Notify observers
        onPlayerMoved(currentPlayer, moveResult.getOldPosition(), moveResult.getNewPosition());
        
        // Check for winner
        if (moveResult.isCompletedGame()) {
            notifyGameOver(currentPlayer);
            return;
        }
        
        // Switch to next player
        nextTurn();
    }
    
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().length;
        currentPlayer = game.getPlayers()[currentPlayerIndex];
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean hasWinner() {
        for (Player player : game.getPlayers()) {
            if (game.getRules().hasWinner(player, game.getBoard())) {
                return true;
            }
        }
        return false;
    }
    
    public Player getWinner() {
        for (Player player : game.getPlayers()) {
            if (game.getRules().hasWinner(player, game.getBoard())) {
                return player;
            }
        }
        return null;
    }
    
    private void notifyGameOver(Player winner) {
        for (GamePlayObserver observer : observers) {
            observer.onGameOver(winner);
        }
    }
    
    public void playUntilWinner() {
        while (!hasWinner()) {
            int diceRoll = game.getDice().roll();
            System.out.println("\n" + currentPlayer.getName() + "'s turn - Rolled: " + diceRoll);
            executeMove(diceRoll);
        }
        
        Player winner = getWinner();
        System.out.println("\n=== Game Complete ===");
        System.out.println("Winner: " + winner.getName());
    }
    
    public interface GamePlayObserver {
        void onPlayerMoved(Player player, int oldPosition, int newPosition);
        void onGameOver(Player winner);
    }
}
