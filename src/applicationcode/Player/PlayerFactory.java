package src.applicationcode.Player;

public class PlayerFactory {
    
    public static Player createPlayer(String name) {
        return new Player(name);
    }
    
    public static Player createPlayer(String name, int position) {
        Player player = new Player(name);
        player.setPosition(position);
        player.setStartingPosition(position);
        return player;
    }
    
    public static Player[] createPlayers(int boardSize, String... names) {
        Player[] players = new Player[names.length];
        int spacing = boardSize / names.length;
        for (int i = 0; i < names.length; i++) {
            players[i] = createPlayer(names[i], i * spacing);
        }
        return players;
    }
}
