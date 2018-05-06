package gui.elements;

import shared.IntPair;

import javax.swing.*;
import java.awt.*;

public class SingleCell extends JButton {

    private static final int SIZE = 80;
    private final IntPair position;
    private SingleCellActionListener actionListener;

    public interface SingleCellActionListener {
        void buttonClicked(IntPair position, SingleCell cell);
    }

    public SingleCell(IntPair position, SingleCellActionListener actionListener) {
        initComponent();
        this.position = position;
        this.actionListener = actionListener;
    }

    private void initComponent() {
        this.setPreferredSize(new Dimension(SIZE, SIZE));

        this.addActionListener(e -> {
            System.out.println("Button on position " + position + " clicked");
            if (actionListener == null) {
                System.out.println("Action listener is not set!");
            } else {
                actionListener.buttonClicked(position, this);
            }
        });
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public void setActionListener(SingleCellActionListener listener) {
        this.actionListener = listener;
    }

    public IntPair getPosition() {
        return position;
    }
}
