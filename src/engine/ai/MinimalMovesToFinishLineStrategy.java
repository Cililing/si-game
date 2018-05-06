package engine.ai;

import engine.core.Game;
import shared.IntPair;
import engine.core.dao.WinningLine;
import engine.shared.INextMoveStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimalMovesToFinishLineStrategy implements INextMoveStrategy, IGameReferenceNeeded {

    private Game game;

    public MinimalMovesToFinishLineStrategy() {
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public IntPair nextMove() {
        List<WinningLine> winningLines = game.getWinningLines();

        List<WinningLine> sortedWinningLines = new ArrayList<>(winningLines); // Enough for deep copy
        sortedWinningLines.sort(Comparator.comparingInt(o -> o.getCells().size())); // Smallest number of cells to finish

        return sortedWinningLines.get(0).getCells().get(0);
    }
}
