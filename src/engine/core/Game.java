package engine.core;

import engine.core.board.Board;
import engine.core.board.BoardHelper;
import shared.IntPair;
import engine.core.dao.WinningLine;
import engine.core.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private int activePlayer = 0;
    private final Player[] players;
    private final Map<Player, Integer> points;

    private final Board board;
    private final List<WinningLine> winningLines;

    private final IGameListener gameListener;

    public Game(int boardSize, Player[] players, IGameListener gameListener) {
        this.board = new Board(boardSize);
        this.players = players;
        this.points = new HashMap<>();
        this.gameListener = gameListener;

        for (Player player : players) {
            points.put(player, 0);
        }

        List<List<IntPair>> winningLines = BoardHelper.getWinningLines(board.getBoard());
        this.winningLines = winningLines.stream().map(WinningLine::new).collect(Collectors.toList());
    }

    private void nextPlayer() {
        if (++activePlayer == players.length) {
            activePlayer = 0;
        }
        gameListener.onPlayerChanged(players[activePlayer]);
    }

    public Player getActivePlayer() {
        return players[activePlayer];
    }

    public void action(int row, int column) {
        if (board.markCell(players[activePlayer].getPlayerState(), row, column)) {
            Iterator<WinningLine> iterator = winningLines.iterator();
            while (iterator.hasNext()) {
                WinningLine winningLine = iterator.next();
                if (winningLine.removeCell(row, column)) {
                    if (winningLine.getNumberOfRemainingCells() == 0) {
                        // Line finished, give points to player
                        points.put(players[activePlayer], points.get(players[activePlayer]) + winningLine.getPoints());
                        iterator.remove();
                        gameListener.onPlayerResultChanged(players[activePlayer], winningLine, points);
                    }
                }
            }

            if (winningLines.size() == 0) {
                // All points finished
                gameListener.onGameFinished(points);
            }

            gameListener.onMoveFinished(players[activePlayer], row, column);
            // Change player
            nextPlayer();
        } else {
            gameListener.onMoveError(players[activePlayer], row, column);
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<WinningLine> getWinningLines() {
        return winningLines;
    }
}
