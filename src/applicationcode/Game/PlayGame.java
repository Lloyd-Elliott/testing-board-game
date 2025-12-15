package src.applicationcode.Game;

import src.applicationcode.Player.Player;
import src.applicationcode.Rules.MoveResult;
import src.applicationcode.Rules.RulesStrategy;
import src.applicationcode.Board.BoardStrategy;
import java.util.ArrayList;
import java.util.List;

public class PlayGame implements GamePlay {
    
    private final Game game;
    private final RulesStrategy rules;
    private final BoardStrategy board;
    private Player currentPlayer;
    private int currentPlayerIndex = 0;
    private final List<GamePlayObserver> observers = new ArrayList<>();
    
    public PlayGame(Game game, GamePlayObserver observer) {
        this.game = game;
        this.rules = game.getRules();
        this.board = game.getBoard();
        this.currentPlayer = game.getPlayers()[0];
        
        // Register the injected observer
        if (observer != null) {
            addObserver(observer);
        }
    }
    
    public void addObserver(GamePlayObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(GamePlayObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition) {
        for (GamePlayObserver observer : observers) {
            observer.onPlayerMoved(player, oldPosition, newPosition);
        }
    }
    
    public void executeMove(int diceRoll) {
        if (hasWinner()) {
            return;
        }
        
        MoveResult moveResult = rules.calculateMove(currentPlayer, diceRoll, board, game.getPlayers());
        
        currentPlayer.moveTo(moveResult.getNewPosition());
        
        if (moveResult.isEnteredTail()) {
            currentPlayer.setInTail(true);
        }
        
        if (currentPlayer.isInTail()) {
            currentPlayer.setTailPosition(moveResult.getTailPosition());
        }
        
        onPlayerMoved(currentPlayer, moveResult.getOldPosition(), moveResult.getNewPosition());
        
        if (moveResult.isCollision()) {
            notifyCollision(currentPlayer, moveResult.getBlockedPosition());
        }
        
        if (moveResult.isOvershot()) {
            notifyOvershot(currentPlayer);
        }
        
        if (moveResult.isCompletedGame()) {
            notifyGameOver(currentPlayer);
            return;
        }
        
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
            if (rules.hasWinner(player, board)) {
                return true;
            }
        }
        return false;
    }
    
    public Player getWinner() {
        for (Player player : game.getPlayers()) {
            if (rules.hasWinner(player, board)) {
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
    
    private void notifyOvershot(Player player) {
        for (GamePlayObserver observer : observers) {
            observer.onPlayerOvershot(player);
        }
    }
    
    private void notifyCollision(Player player, int blockedPosition) {
        for (GamePlayObserver observer : observers) {
            observer.onPlayerCollision(player, blockedPosition);
        }
    }
    
    public void playUntilWinner() {
        while (!hasWinner()) {
            int diceRoll = game.getDice().roll();
            notifyTurnStarted(currentPlayer, diceRoll);
            executeMove(diceRoll);
        }
        
        Player winner = getWinner();
        notifyGameComplete(winner);
    }
    
    private void notifyTurnStarted(Player player, int diceRoll) {
        for (GamePlayObserver observer : observers) {
            observer.onTurnStarted(player, diceRoll);
        }
    }
    
    private void notifyGameComplete(Player winner) {
        for (GamePlayObserver observer : observers) {
            observer.onGameComplete(winner);
        }
    }
}
