import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class Duplicator {
    static List<Round> duplicateRounds(List<Round> rounds) {
        val newRounds = new ArrayList<>(rounds);

        for (int i = 1; i <= rounds.get(rounds.size() - 1).getGameId(); i++) {
            val currentRound = Round.getRoundsById(newRounds, i);

            if (hasMatches(currentRound)) {
                newRounds.addAll(addDuplicateRounds(currentRound, rounds));
            }
        }

        return newRounds;
    }

    private static boolean hasMatches(List<Round> rounds) {
        return rounds.stream().anyMatch(round -> round.getMatches() > 0);
    }

    private static List<Round> addDuplicateRounds(List<Round> roundsWithMatches, List<Round> fullRoundsList) {
        val newRounds = new ArrayList<Round>();

        roundsWithMatches.forEach(round -> {
            for (int j = round.getGameId() + 1; j <= round.getGameId() + round.getMatches(); j++) {
                val newRoundDetails = Round.getRoundsById(fullRoundsList, j).get(0);
                val newRound = createNewRound(j, newRoundDetails);
                newRounds.add(newRound);
            }
        });

        return newRounds;
    }

    private static Round createNewRound(int gameId, Round roundDetails) {
        return new Round(gameId, roundDetails.getWinningNumbers(), roundDetails.getPlayerNumbers());
    }
}