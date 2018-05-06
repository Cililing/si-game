package gui;

import gui.elements.GameBoard;
import gui.elements.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private static final int DEFAULT_SIZE = 800;

    private int boardSize;

    // Components - package private
    GameBoard gameBoard;
    ScorePanel scorePanel;
    // End of Components

    public MainWindow(int boardSize) {
        this.boardSize = boardSize;
        initComponent();
    }

    private void initComponent() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));

        // Set Layout
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        // Add GameBoard
        gameBoard = new GameBoard(boardSize);
        this.add(gameBoard, BorderLayout.CENTER);

        // Add ScorePanel
        scorePanel = new ScorePanel();
        this.add(scorePanel, BorderLayout.SOUTH);

        // TODO initial text
        scorePanel.setLine1Text("Player 0: " + 0);
        scorePanel.setLine2Text("Player 1: " + 0);

        this.pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(4);
            mainWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        });
    }
}
