package engine.core;

import engine.core.dao.WinningLine;
import engine.core.player.Player;

import java.util.Map;

public interface IGameListener {

    void onGameFinished(Map<Player, Integer> points);
    void onMoveError(Player activePlayer, int row, int column);
    void onPlayerChanged(Player activePlayer);
    void onMoveFinished(Player activePlayer, int row, int column);
    void onPlayerResultChanged(Player activePlayer, WinningLine winningLine, Map<Player, Integer> points);

}
