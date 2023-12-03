import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Cube {
    private Colour colour;
    private Integer count;

    public static List<Cube> createCubesForRound(String[] roundInfo) {
        return Arrays.stream(roundInfo)
                .map(item -> item.trim().split(" "))
                .map(cubeItems -> {
                    val cubeColour = Colour.fromString(cubeItems[1]);
                    val cubeCount = Integer.parseInt(cubeItems[0]);
                    return new Cube(cubeColour, cubeCount);
                })
                .collect(Collectors.toList());
    }
}