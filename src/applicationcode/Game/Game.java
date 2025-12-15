package src.applicationcode.Game;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Board.BoardStrategy;
import src.applicationcode.Rules.RulesStrategy;
import src.applicationcode.Player.Player;

public class Game {
    private final Dice dice;
    private final BoardStrategy board;
    private final RulesStrategy rules;
    private final Player[] players;
    
    public Game(BoardStrategy board, Player[] players, RulesStrategy rules) {
        this.board = board;
        this.dice = board.getDice();
        this.players = players;
        this.rules = rules;
        
        int boardSize = board.getBoardSize();
        for (int i = 0; i < players.length; i++) {
            int startPosition = (i * boardSize / players.length);
            players[i].setPosition(startPosition);
            players[i].setStartingPosition(startPosition);
        }
    }
    
    public Dice getDice() {
        return dice;
    }
    
    public BoardStrategy getBoard() {
        return board;
    }
    
    public RulesStrategy getRules() {
        return rules;
    }
    
    public Player[] getPlayers() {
        return players;
    }
}
