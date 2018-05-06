package engine.core.gameBuilder;

import engine.ai.IGameReferenceNeeded;
import engine.core.Game;
import engine.core.player.Player;

public class GameBuildable implements IGameBuildable {
    private GameBuilder parent;

    public GameBuildable(GameBuilder parent) {
        this.parent = parent;
    }

    @Override
    public Game build() {
        Player[] players = parent.players.toArray(new Player[]{});
        Game game = new Game(parent.boardSize, players, parent.gameListener);

        for (Player player : players) {
            setGameToStrategyIfNeeded(player, game);
        }

        return game;
    }

    private void setGameToStrategyIfNeeded(Player player, Game game) {
        if (player.getMoveStrategy() instanceof IGameReferenceNeeded) {
            ((IGameReferenceNeeded) player.getMoveStrategy()).setGame(game);
        }
    }
}
