package src.applicationcode.Game;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Board.Board;
import src.applicationcode.Rules.Rules;
import src.applicationcode.Player.Player;

public class Game {
    private final Dice dice;
    private final Board board;
    private final Rules rules;
    private final Player[] players;
    
    public Game(Board board, Player[] players, Rules rules) {
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
    
    public Board getBoard() {
        return board;
    }
    
    public Rules getRules() {
        return rules;
    }
    
    public Player[] getPlayers() {
        return players;
    }
}
