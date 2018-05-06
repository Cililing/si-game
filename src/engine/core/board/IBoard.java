package engine.core.board;

public interface IBoard {
    // True if marked, false is already occupied
    boolean markCell(CellState state, int row, int column);
    boolean canMark(int row, int column);
}
