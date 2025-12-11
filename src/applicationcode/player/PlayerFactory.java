package src.applicationcode.player;
public class PlayerFactory {
    
    public static Player createPlayer(String name) {
        return new Player(name);
    }
    
    public static Player createPlayer(String name, int startingPosition) {
        Player player = new Player(name);
        player.setStartingPosition(startingPosition);
        return player;
    }
    
    public static Player[] createPlayers(String... names) {
        Player[] players = new Player[names.length];
        for (int i = 0; i < names.length; i++) {
            players[i] = createPlayer(names[i]);
        }
        return players;
    }
}
