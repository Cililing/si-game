package gui.elements;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 40;
    private static final int DEFAULT_GAP = 8;

    private GridLayout mainLayout;
    private JLabel line1;
    private JLabel line2;

    public ScorePanel() {
        initComponent();
    }

    private void initComponent() {
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        // Set layout
        mainLayout = new GridLayout(1, 0, DEFAULT_GAP, DEFAULT_GAP);

        line1 = new JLabel();
        line2 = new JLabel();

        this.add(line1);
        this.add(line2);
    }

    public void setLine1Text(String text) {
        this.line1.setText(text);
    }

    public void setLine2Text(String text) {
        this.line2.setText(text);
    }
}
