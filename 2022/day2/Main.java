import lombok.val;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final String input = """
            C Y
            C Y
            B Y
            A Z
            B Z
            A X
            """;

    public static void main(String[] args) {
        val elvesPlan = input.lines().mapToInt(Main::guessElvesPlan_1Star).sum();
        System.out.println("The elves made you score: " + elvesPlan);

        val elvesActualPlan = input.lines().mapToInt(Main::actualElvesPlan_2Star).sum();
        System.out.println("The elves wanted you to score: " + elvesActualPlan);
    }

    private static Integer guessElvesPlan_1Star(String line) {
        // The elves didn't give details of the full plan
        val opponentsMove = Hand.converter(line.charAt(0));
        val ourMove = Hand.converter(line.charAt(2));

        val pointsFromPlaying = playGame(ourMove, opponentsMove).getPoints();
        val pointsForHandUsed = ourMove.getPoints();

        return pointsFromPlaying + pointsForHandUsed;
    }

    private static Integer actualElvesPlan_2Star(String line) {
        // The elves told us the second letter should be if we want to win or not
        val points = new AtomicInteger();
        val opponentsMove = Hand.converter(line.charAt(0));
        val whatWeShouldDo = State.converter(line.charAt(2));

        Arrays.stream(Hand.values()).forEach(
                ourMove -> {
                    val state = playGame(ourMove, opponentsMove);
                    if (state == whatWeShouldDo) {
                        points.addAndGet(ourMove.getPoints());
                        points.addAndGet(state.getPoints());
                    }
                }
        );

        return points.get();
    }


    private static State playGame(Hand ourHand, Hand opponentsHand) {
        if (ourHand.beats(opponentsHand)) {
            return State.WIN;
        } else if (opponentsHand.beats(ourHand)) {
            return State.LOSE;
        } else {
            return State.DRAW;
        }
    }
}
