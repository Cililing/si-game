package engine.core.dao;

import shared.IntPair;

import java.util.ArrayList;
import java.util.List;

public class WinningLine {
    private final int points;

    private final List<IntPair> startingCells;
    private final List<IntPair> cells;

    public WinningLine(List<IntPair> startingCells) {
        this.points = startingCells.size();
        this.startingCells = startingCells;
        cells = new ArrayList<>(startingCells); // this make deep copy
    }

    public boolean removeCell(int row, int column) {
        return cells.remove(new IntPair(row, column));
    }

    public int getNumberOfRemainingCells() {
        return cells.size();
    }

    public int getPoints() {
        return points;
    }

    public List<IntPair> getCells() {
        return cells;
    }
}
