import lombok.val;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private final static Input input = new Input();


    public static void main(String[] args) {
        System.out.println("Starting building the objects, it takes a moment as there'll be millions of them...");

        val rounds = Round.createRoundsFromInput(input.gameInput);
        val roundsWithDuplicates = Duplicator.duplicateRounds(rounds);

        logging(rounds, roundsWithDuplicates);
    }

    private static void logging(List<Round> rounds, List<Round> newRounds) {
        val total = new AtomicInteger();

        rounds.forEach(round -> {
            val score = round.getScore();
            total.addAndGet(score);
        });

        System.out.println("Total score: " + total);
        System.out.println();
        System.out.println("Total after duplicate rounds: " + newRounds.size());
    }
}