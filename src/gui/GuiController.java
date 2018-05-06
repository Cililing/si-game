package gui;

import gui.dao.ActivePlayer;
import shared.IntPair;

import java.awt.*;

public class GuiController implements IGuiController {

    private final MainWindow mainWindow;
    private final IGuiListener listener;

    private ActivePlayer activePlayer;

    public GuiController(MainWindow mainWindow, IGuiListener listener) {
        this.mainWindow = mainWindow;
        this.listener = listener;

        initController();
    }

    private void initController() {
        mainWindow.gameBoard.setCellsListener((position, cell) -> listener.buttonClicked(position, cell, activePlayer));
    }

    @Override
    public void setScore(String line1, String line2) {
        mainWindow.scorePanel.setLine1Text(line1);
        mainWindow.scorePanel.setLine2Text(line2);
    }

    @Override
    public void setActivePlayer(ActivePlayer activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void setCellMarked(int row, int column, Color playerColor) {
        mainWindow.gameBoard.getCellAtPosition(new IntPair(row, column)).setColor(playerColor);
    }
}
