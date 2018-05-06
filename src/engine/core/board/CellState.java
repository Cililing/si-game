package engine.core.board;

public enum  CellState {
    PLAYER_0(-1),
    PLAYER_1(1),
    NOT_MARKED(0);

    final int value;

    CellState(int value) {
        this.value = value;
    }
}
