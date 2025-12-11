package src.applicationcode.board;

import src.applicationcode.rules.Rules;

public class BoardObserver {
    private final Rules rules;

    public BoardObserver(Board board, Rules rules) {
        this.rules = rules;
    }

    public void onBoardChanged() {
        rules.endOfGame();
    }
}
