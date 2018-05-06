package engine.ai;

import engine.core.Game;
import shared.IntPair;
import engine.core.dao.WinningLine;
import engine.shared.INextMoveStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestNumberOfPointsForLineStrategy implements INextMoveStrategy, IGameReferenceNeeded {

    private Game game;

    public BestNumberOfPointsForLineStrategy() {
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public IntPair nextMove() {
        List<WinningLine> winningLines = game.getWinningLines();
        Map<IntPair, Integer> points = new HashMap<>();

        for (WinningLine winningLine: winningLines) {
            for (IntPair cords : winningLine.getCells()) {
                points.put(cords, points.getOrDefault(cords, 0) + winningLine.getPoints());
            }
        }

        List<Map.Entry<IntPair, Integer>> pointsForMoves =
                points.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        return pointsForMoves.get(pointsForMoves.size() - 1).getKey();
    }
}
