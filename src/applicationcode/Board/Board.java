package src.applicationcode.Board;

import src.applicationcode.Dice.Dice;

public class Board implements BoardStrategy {
    private final BoardStrategy boardStrategy;

    public Board(BoardStrategy boardStrategy) {
        this.boardStrategy = boardStrategy;
    }
    
    @Override
    public void notifyObservers() {
        boardStrategy.notifyObservers();
    }
    
    @Override
    public void addObserver(BoardObserver observer) {
        boardStrategy.addObserver(observer);
    }
    
    @Override
    public void removeObserver(BoardObserver observer) {
        boardStrategy.removeObserver(observer);
    }
    
    @Override
    public int getBoardSize() {
        return boardStrategy.getBoardSize();
    }
    
    @Override
    public int getTailSize() {
        return boardStrategy.getTailSize();
    }
    
    @Override
    public int[] getBoard() {
        return boardStrategy.getBoard();
    }
    
    @Override
    public int[] getTail() {
        return boardStrategy.getTail();
    }
    
    @Override
    public Dice getDice() {
        return boardStrategy.getDice();
    }
}
