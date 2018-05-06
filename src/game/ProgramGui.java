package game;

import engine.ai.BiggestOccurenceInWinningLinesStrategy;
import engine.ai.MinimalMovesToFinishLineStrategy;
import engine.core.Game;
import engine.core.IGameListener;
import engine.core.board.CellState;
import engine.core.dao.WinningLine;
import engine.core.gameBuilder.GameBuilder;
import engine.core.player.GuiIOStrategy;
import engine.core.player.Player;
import engine.core.player.SystemIOMoveStrategy;
import gui.GuiController;
import gui.MainWindow;
import gui.dao.ActivePlayer;
import shared.IntPair;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class ProgramGui {

    public static void main(String[] args) {

        int boardSize = 7;

        SystemIOMoveStrategy manualStrategy = new SystemIOMoveStrategy();
        GuiIOStrategy guiIOStrategy = new GuiIOStrategy();
        BiggestOccurenceInWinningLinesStrategy biggestOccurenceInWinningLinesStrategy = new BiggestOccurenceInWinningLinesStrategy();
        MinimalMovesToFinishLineStrategy minimalMovesToFinishLineStrategy = new MinimalMovesToFinishLineStrategy();


        BooleanWrapper isGameActive = new BooleanWrapper();
        isGameActive.value = true;

        Player player0 = new Player(CellState.PLAYER_0, guiIOStrategy, Color.BLUE);
        Player player1 = new Player(CellState.PLAYER_1, guiIOStrategy, Color.RED);

        Function<IntPair, Boolean> guiIOStrategyP0 = null;
        if (player0.getMoveStrategy() instanceof GuiIOStrategy) {
            guiIOStrategyP0 = ((GuiIOStrategy) player0.getMoveStrategy())::setNextInput;
        }
        Function<IntPair, Boolean> guiIOStrategyP1 = null;
        if (player1.getMoveStrategy() instanceof GuiIOStrategy) {
            guiIOStrategyP1 = ((GuiIOStrategy) player1.getMoveStrategy())::setNextInput;
        }

//        Map<Player, Function<IntPair, Boolean>> guiIOStrategyMap = new TreeMap<>();
//        guiIOStrategyMap.put(player0, guiIOStrategyP0);
//        guiIOStrategyMap.put(player1, guiIOStrategyP1);

        ActivePlayer activePlayerP0 = new ActivePlayer("Player0 ", player0, guiIOStrategyP0);
        ActivePlayer activePlayerP1 = new ActivePlayer("Player1 ", player1, guiIOStrategyP1);

        Map<Player, ActivePlayer> activePlayerMap = new TreeMap<>();
        activePlayerMap.put(player0, activePlayerP0);
        activePlayerMap.put(player1, activePlayerP1);

        MainWindow mainWindow = new MainWindow(boardSize);
        GuiController guiController = new GuiController(
                mainWindow,
                (position, cell, activePlayer) -> {
                    System.out.println("Button clicked on position " + position + ". Active player: " + activePlayer.getPlayerColor());
                    if (activePlayer.getInvokeReference() != null) {
                        activePlayer.getInvokeReference().apply(position);
                    }
                });

        guiController.setActivePlayer(activePlayerMap.get(player0));

        EventQueue.invokeLater(() -> {
            mainWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            mainWindow.setVisible(true);
        });

        IGameListener gameListener = new IGameListener() {
            @Override
            public void onGameFinished(Map<Player, Integer> points) {
                System.out.println("Player points: " +
                        points.get(player0) + ", " + points.get(player1) );
                isGameActive.value = false;
            }

            @Override
            public void onMoveError(Player activePlayer, int row, int column) {
                System.out.println("Position " + row + " " + column + " occupied");
            }

            @Override
            public void onPlayerChanged(Player activePlayer) {
                System.out.println("Player changed to player: " + activePlayer.getPlayerColor());
                guiController.setActivePlayer(activePlayerMap.get(activePlayer));
            }

            @Override
            public void onMoveFinished(Player activePlayer, int row, int column) {
                guiController.setCellMarked(row, column, activePlayerMap.get(activePlayer).getPlayerColor());
            }

            @Override
            public void onPlayerResultChanged(Player activePlayer, WinningLine winningLine, Map<Player, Integer> points) {

                guiController.setScore("Player 0 " + points.get(player0), " |||  Player 1 " + points.get(player1));
            }
        };

        Game game = GameBuilder.builder()
                .setBoardSize(boardSize)
                .playersNeeded(player0, player1)
                .setGameListener(gameListener)
                .build();

        while (isGameActive.value) {
            //System.out.println();
            //BoardHelper.printBoard(game.getBoard().getBoard());

            IntPair nextMove = game.getActivePlayer().nextMove();
            game.action(nextMove.getX(), nextMove.getY());

            boolean waitForMovement = true;
            if (waitForMovement) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
