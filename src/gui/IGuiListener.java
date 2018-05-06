package gui;

import shared.IntPair;
import gui.dao.ActivePlayer;
import gui.elements.SingleCell;

public interface IGuiListener {
    void buttonClicked(IntPair position, SingleCell cell, ActivePlayer activePlayer);
}
