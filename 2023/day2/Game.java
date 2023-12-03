import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Getter
@Setter
public class Game {
    private Integer id;
    private List<Round> rounds;

    private boolean tooManyCubes(Colour colour) {
        return getHighestCube(colour) > colour.getMaxCubes();
    }

    public Integer getHighestCube(Colour colour) {
        return rounds.stream()
                .flatMap(round -> round.getCubes().stream())
                .filter(cube -> cube.getColour() == colour)
                .map(Cube::getCount)
                .max(Integer::compare)
                .orElse(0);
    }

    Integer getPower() {
        return Arrays.stream(Colour.values())
                .mapToInt(this::getHighestCube)
                .reduce(1, (a, b) -> a * b);
    }

    boolean validGame() {
        return Arrays.stream(Colour.values())
                .noneMatch(this::tooManyCubes);
    }

    static List<Game> createGameObjects(String gameList) {
        return Arrays.stream(gameList.lines().toArray(String[]::new))
                .map(line -> {
                    val lineDetails = line.split(":");
                    val gameId = lineDetails[0].split(" ");
                    val gameRounds = Round.createRoundsFromString(lineDetails[1].split(";"));

                    return new Game(parseInt(gameId[1]), gameRounds);
                })
                .toList();
    }
}