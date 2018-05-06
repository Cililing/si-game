package gui.elements;

import javafx.util.Pair;
import shared.IntPair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    private final int matrixSize;

    private GridLayout mainLayout;
    private java.util.List<SingleCell> cells;

    // JPanel contains
    public GameBoard(int matrixSize) {
        this.matrixSize = matrixSize;
        initComponent();
    }

    public SingleCell getCellAtPosition(IntPair position) {
          return cells.get(position.getX() * matrixSize + position.getY());
    }

    private void initComponent() {
        mainLayout = new GridLayout(matrixSize, matrixSize);
        this.setLayout(mainLayout);
        cells = new ArrayList<>(matrixSize * matrixSize);
        drawMatrix();
    }

    private void drawMatrix() {
        for (int i = 0; i < matrixSize * matrixSize; i++) {
            int x = i / matrixSize;
            int y = i % matrixSize;

            SingleCell singleCell = new SingleCell(new IntPair(x, y), null);
            this.cells.add(singleCell);
            this.add("Button " + i, singleCell);
        }
    }

    public void setCellsListener(SingleCell.SingleCellActionListener singleCellActionListener) {
        cells.forEach(e -> e.setActionListener(singleCellActionListener));
    }
}
