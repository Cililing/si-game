//package game;
//
//import engine.ai.BestNumberOfPointsForLineStrategy;
//import engine.ai.BiggestOccurenceInWinningLinesStrategy;
//import engine.ai.MinimalMovesToFinishLineStrategy;
//import engine.core.Game;
//import engine.core.IGameListener;
//import engine.core.board.CellState;
//import shared.IntPair;
//import engine.core.dao.WinningLine;
//import engine.core.gameBuilder.GameBuilder;
//import engine.core.player.Player;
//import engine.core.player.SystemIOMoveStrategy;
//
//import java.awt.*;
//import java.util.Map;
//
//public class Program {
//
//    public static void main(String[] args) {
//
//        SystemIOMoveStrategy manualStrategy = new SystemIOMoveStrategy();
//        BestNumberOfPointsForLineStrategy bestNumberOfPointsStrategy = new BestNumberOfPointsForLineStrategy();
//        BiggestOccurenceInWinningLinesStrategy biggestOccurenceInWinningLinesStrategy = new BiggestOccurenceInWinningLinesStrategy();
//        MinimalMovesToFinishLineStrategy minimalMovesToFinishLineStrategy = new MinimalMovesToFinishLineStrategy();
//
//        BooleanWrapper isGameActive = new BooleanWrapper();
//        isGameActive.value = true;
//
//        Player[] players = new Player[] {
//                new Player(CellState.PLAYER_0, biggestOccurenceInWinningLinesStrategy, Color.RED),
//                new Player(CellState.PLAYER_1, minimalMovesToFinishLineStrategy, Color.BLUE)
//        };
//
//        IGameListener gameListener = new IGameListener() {
//            @Override
//            public void onGameFinished(Map<Player, Integer> points) {
//                System.out.println("Player points: " + points.get(players[0]) + ", " + points.get(players[1]) );
//                isGameActive.value = false;
//            }
//
//            @Override
//            public void onMoveError(Player activePlayer, int row, int column) {
//                System.out.println("Position " + row + " " + column + " occupied");
//            }
//
//            @Override
//            public void onPlayerChanged(Player activePlayer) {
//                System.out.println("Player changed to " + activePlayer.getPlayerState());
//            }
//
//            @Override
//            public void onPlayerResultChanged(Player activePlayer, WinningLine winningLine, int points) {
//                System.out.println("Player " + activePlayer.getPlayerState() + " finished line for " + winningLine.getPoints() + " points");
//            }
//        };
//
//        Game game = GameBuilder.builder()
//                        .setBoardSize(12)
//                        .playersNeeded(players[0], players[1])
//                        .setGameListener(gameListener)
//                        .build();
//
//        while (isGameActive.value) {
//            //System.out.println();
//            //BoardHelper.printBoard(game.getBoard().getBoard());
//
//            IntPair nextMove = game.getActivePlayer().nextMove();
//            game.action(nextMove.getX(), nextMove.getY());
//        }
//    }
//
//}
