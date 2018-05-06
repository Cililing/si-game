package engine.core.gameBuilder;

import engine.core.player.Player;

public interface IGamePlayersNeeded {
    IGameListenerNeeded playersNeeded(Player player1, Player player2);
}