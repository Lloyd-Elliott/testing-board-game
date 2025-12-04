public class Board {
    private BoardStrategy boardStrategy;

    public Board(BoardStrategy boardStrategy){
        this.boardStrategy = boardStrategy;
    }

    public BoardStrategy getBoardStrategy() {
        return boardStrategy;
    }
    



    public void setupBoard(){
        boardStrategy.setupBoard();
    }

    public void movePlayer(int steps, RulesStrategy rulesStrategy) {
        boardStrategy.movePlayer(steps, rulesStrategy);
    }

    public boolean hasWinner(){
        return boardStrategy.hasWinner();
    }

    public int getCurrentIndex1() { return boardStrategy.getCurrentIndex1(); }
    public int getCurrentIndex2() { return boardStrategy.getCurrentIndex2(); }
    public int getEndIndex1() { return boardStrategy.getEndIndex1(); }
    public int getEndIndex2() { return boardStrategy.getEndIndex2(); }
    public int getSpacesTraveled1() { return boardStrategy.getSpacesTraveled1(); }
    public int getSpacesTraveled2() { return boardStrategy.getSpacesTraveled2(); }

    public int getTileValue(int index) { return boardStrategy.getTileValue(index); }

    // ⭐ THIS WAS MISSING
    public void setGameOver(boolean value) {
        boardStrategy.setGameOver(value);
    }

    public void addObserver(BoardObserver obs) {
        boardStrategy.addObserver(obs);
    }

    public void notifyObservers() {
        boardStrategy.notifyObservers();
    }
}
