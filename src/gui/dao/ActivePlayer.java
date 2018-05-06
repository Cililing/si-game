package gui.dao;

import com.sun.istack.internal.Nullable;
import engine.core.player.Player;
import shared.IntPair;

import java.awt.*;
import java.util.function.Function;

public class ActivePlayer {

    private final String name;
    private final Player player;

    @Nullable private Function<IntPair, Boolean> invokeReference;

    public ActivePlayer(String name, Player player, Function<IntPair, Boolean> invokeReference) {
        this.name = name;
        this.player = player;
        this.invokeReference = invokeReference;
    }

    public Function<IntPair, Boolean> getInvokeReference() {
        return invokeReference;
    }

    public void setInvokeReference(Function<IntPair, Boolean> invokeReference) {
        this.invokeReference = invokeReference;
    }

    public Color getPlayerColor() {
        return player.getPlayerColor();
    }

    public String getName() {
        return name;
    }

    public boolean isTheSamePlayer(Player player) {
        return this.player == player;
    }

}
