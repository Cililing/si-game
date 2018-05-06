package engine.core.gameBuilder;

class BoardSizeNeeded implements IBoardSizeNeeded {
    private GameBuilder parent;

    public BoardSizeNeeded(GameBuilder parent) {
        this.parent = parent;
    }

    @Override
    public IGamePlayersNeeded setBoardSize(int boardSize) {
        if (boardSize < 2) {
            throw new IllegalArgumentException("Board size lesser than 2");
        }

        parent.boardSize = boardSize;
        return new GamePlayersNeeded(parent);
    }
}
