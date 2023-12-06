import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
public class Round {
    private Integer gameId;
    private Set<Integer> winningNumbers;
    private Set<Integer> playerNumbers;
    private Integer matches;
    private Integer score;

    public Round(Integer gameId, Set<Integer> winningNumbers, Set<Integer> playerNumbers) {
        this.gameId = gameId;
        this.winningNumbers = winningNumbers;
        this.playerNumbers = playerNumbers;
        this.matches = Math.toIntExact(winningNumbers.stream()
                .filter(playerNumbers::contains)
                .count());
        this.score = getScore(matches);
    }

    static List<Round> getRoundsById(List<Round> rounds, Integer gameId) {
        return rounds.stream()
                .filter(round -> Objects.equals(round.getGameId(), gameId))
                .toList();
    }

    private static int getScore(int matches) {
        return (int) Math.pow(2, matches - 1);
    }

    static List<Round> createRoundsFromInput(String input) {
        val inputLines = input.split("\n");
        val gameId = Arrays.stream(inputLines).map(Round::parseGameId).toList();

        val afterColonParts = Arrays.stream(inputLines)
                .map(line -> line.split(": ")[1].split("\\|"))
                .toList();

        val winningNumbersList = afterColonParts.stream()
                .map(parts -> parseNumberSet(parts[0]))
                .toList();

        val playerNumbersList = afterColonParts.stream()
                .map(parts -> parseNumberSet(parts[1]))
                .toList();

        return IntStream.range(0, winningNumbersList.size())
                .mapToObj(i -> new Round(gameId.get(i), winningNumbersList.get(i), playerNumbersList.get(i)))
                .toList();
    }

    private static int parseGameId(String line) {
        return line.split(": ")[0].chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .reduce(0, (a, b) -> a * 10 + b);
    }

    private static Set<Integer> parseNumberSet(String numbers) {
        return Arrays.stream(numbers.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}