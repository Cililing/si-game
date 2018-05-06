package engine.core.board;

public class Board implements IBoard {

    private final int size;
    private final CellState[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new CellState[size][size];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] =  CellState.NOT_MARKED;
            }
        }
    }

    @Override
    public boolean markCell(CellState state, int row, int column) {
        if (canMark(row, column)) {
            board[row][column] = state;
            return true;
        }

        return false;
    }

    @Override
    public boolean canMark(int row, int column) {
        if (row < 0 || column < 0 || row >= board.length || column >= board.length) {
            return false;
        }

        return board[row][column] == CellState.NOT_MARKED;
    }

    public int getSize() {
        return size;
    }

    public CellState[][] getBoard() {
        return board;
    }

}
