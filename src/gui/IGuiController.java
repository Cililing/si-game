package gui;

import gui.dao.ActivePlayer;

import java.awt.*;

public interface IGuiController {
    void setScore(String line1, String line2);
    void setActivePlayer(ActivePlayer player);
}
