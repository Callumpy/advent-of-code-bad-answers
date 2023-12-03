import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Round {
    List<Cube> cubes;

    public static List<Round> createRoundsFromString(String[] roundInfo) {
        return Arrays.stream(roundInfo)
                .map(item -> new Round(Cube.createCubesForRound(item.trim().split(","))))
                .collect(Collectors.toList());
    }
}