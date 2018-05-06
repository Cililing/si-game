package engine.core.gameBuilder;

import engine.core.IGameListener;

public class GameListenerNeeded implements IGameListenerNeeded {
    private GameBuilder parent;

    public GameListenerNeeded(GameBuilder parent) {
        this.parent = parent;
    }

    @Override
    public IGameBuildable setGameListener(IGameListener listener) {
        parent.gameListener = listener;
        return new GameBuildable(parent);
    }

}
