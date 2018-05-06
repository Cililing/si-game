package engine.core.gameBuilder;

import engine.core.IGameListener;
import engine.core.player.Player;

import java.util.ArrayList;

public class GameBuilder {

    int boardSize = 0;
    ArrayList<Player> players = new ArrayList<>();
    IGameListener gameListener = null;

    private GameBuilder() {
    }

    public static IBoardSizeNeeded builder() {
        GameBuilder builder = new GameBuilder();
        return new BoardSizeNeeded(builder);
    }

}


