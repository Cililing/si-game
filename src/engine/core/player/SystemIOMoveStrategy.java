package engine.core.player;

import engine.shared.INextMoveStrategy;
import shared.IntPair;

import java.util.Scanner;

public class SystemIOMoveStrategy implements INextMoveStrategy {

    @Override
    public IntPair nextMove() {
        return new IntPair(
                tryGetNumber("row", "Illegal expression."),
                tryGetNumber("column", "Illegal expression.")
        );
    }

    private Integer tryGetNumber(String question, String exception) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);
        Integer number = null;

        while (number == null) {

            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println(exception + " | " + question);
                scanner.reset();
            }
        }
        return number;
    }

}
