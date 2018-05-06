package engine.core.player;

import shared.IntPair;
import engine.shared.INextMoveStrategy;

import java.util.function.Function;

public class GuiIOStrategy implements INextMoveStrategy {

    @Override
    public IntPair nextMove() {
        return getNextInput();
    }

    volatile private boolean waitingForNextInput = false;
    volatile private IntPair nextMove = null;

    public IntPair getNextInput() {
        waitingForNextInput = true;
        while (waitingForNextInput) {
            try {
                Thread.sleep(200);
            } catch(InterruptedException ignored) {
            }
        }
        if (nextMove == null) {
            throw new IllegalArgumentException("Input is null!");
        }
        return nextMove;
    }

    public boolean setNextInput(IntPair nextMove) {
        if (!waitingForNextInput) {
            throw new IllegalArgumentException("Controller is not waiting for next movement!");
        }

        this.nextMove = nextMove;
        waitingForNextInput = false;
        return true;
    }

}
