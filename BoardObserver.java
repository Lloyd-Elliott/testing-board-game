public class BoardObserver {
    private final Board board;
    private final Rules rules;

    public BoardObserver(Board board, Rules rules) {
        this.board = board;
        this.rules = rules;
    }

    public void onBoardChanged() {
            rules.endOfGame();
        }

}
