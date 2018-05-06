package engine.core.board;

import shared.IntPair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardHelper {
    private static List<List<IntPair>> getRowsAndColumns(Object[][] matrix) {
        List<List<IntPair>> rowsAndColums = new ArrayList<>(2 * matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            List<IntPair> row = new ArrayList<>(matrix.length);
            List<IntPair> column = new ArrayList<>(matrix.length);
            for (int j = 0; j < matrix.length; j++) {
                row.add(new IntPair(i, j));
                column.add(new IntPair(j, i));
            }
            rowsAndColums.add(row);
            rowsAndColums.add(column);
        }

        return rowsAndColums;
    }

    private static List<List<IntPair>> diagonalizeLeft(Object[][] matrix){
        List<List<IntPair>> diagonals = new ArrayList<>();
        //print first half
        int row = 0;
        int col;

        while(row < matrix.length){
            col =0;
            int rowTemp = row;
            List<IntPair> singleDiagonal = new ArrayList<>();
            while (rowTemp >= 0){
                singleDiagonal.add(new IntPair(rowTemp, col));
                rowTemp--;
                col++;
            }
            diagonals.add(singleDiagonal);
            row++;
        }

        //print second half
        col = 1;

        while (col < matrix.length){
            int colTemp = col;
            row = matrix.length - 1;
            List<IntPair> singleDiagonal = new ArrayList<>();
            while (colTemp <= matrix.length - 1){
                singleDiagonal.add(new IntPair(row, colTemp));
                row--;
                colTemp++;
            }
            diagonals.add(singleDiagonal);
            col++;
        }
        return diagonals;
    }

    private static List<List<IntPair>> diagonalizeRight(Object[][] matrix) {
        List<List<IntPair>> diagonals = new ArrayList<>();
        int max = matrix.length;
        for (int i = -max + 1; Math.abs(i) < max; i++) {
            List<IntPair> singleDiagonal = new ArrayList<>();
            for (int j = 0; j <= max - Math.abs(i) - 1; j++) {
                int row = i < 0 ? j : i + j;
                int col = i > 0 ? j : (Math.abs(i) + j);
                singleDiagonal.add(new IntPair(row, col));
            }
            diagonals.add(singleDiagonal);
        }
        return diagonals;
    }

    public static void printBoard(Object[][] matrix) {
        for (Object[] objects : matrix) {
            for (Object object : objects) {
                System.out.print(object);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static List<List<IntPair>> getWinningLines(Object[][] board) {
        ArrayList<List<IntPair>> allLines = new ArrayList<>();
        allLines.addAll(diagonalizeLeft(board));
        allLines.addAll(diagonalizeRight(board));
        allLines.addAll(getRowsAndColumns(board));

        return allLines.stream().filter(intPairs -> intPairs.size() > 1).collect(Collectors.toList());
    }
}
