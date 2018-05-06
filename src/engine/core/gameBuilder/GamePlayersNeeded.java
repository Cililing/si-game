package engine.core.gameBuilder;

import engine.core.player.Player;

class GamePlayersNeeded implements IGamePlayersNeeded {
    private GameBuilder parent;

    public GamePlayersNeeded(GameBuilder parent) {
        this.parent = parent;
    }

    @Override
    public IGameListenerNeeded playersNeeded(Player player1, Player player2) {
        parent.players.add(player1);
        parent.players.add(player2);

        return new GameListenerNeeded(parent);
    }
}
