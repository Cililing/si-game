package engine.core.player;

import engine.core.board.CellState;
import engine.shared.INextMoveStrategy;
import shared.IntPair;

import java.awt.*;

public class Player implements IPlayer, Comparable<Player> {

    private final CellState playerState;
    private final INextMoveStrategy moveStrategy;
    private final Color playerColor;

    public Player(CellState playerState, INextMoveStrategy moveStrategy) {
        this.playerState = playerState;
        this.moveStrategy = moveStrategy;
        this.playerColor = Color.white;
    }

    public Player(CellState playerState, INextMoveStrategy moveStrategy, Color playerColor) {
        this.playerState = playerState;
        this.moveStrategy = moveStrategy;
        this.playerColor = playerColor;
    }


    @Override
    public IntPair nextMove() {
        return moveStrategy.nextMove();
    }

    public INextMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public CellState getPlayerState() {
        return playerState;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    @Override
    public int compareTo(Player o) {
        return playerColor.hashCode() - o.playerColor.hashCode();
    }
}
