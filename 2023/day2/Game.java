import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Getter
@Setter
public class Game {
    private Integer id;
    private Integer redCubes;
    private Integer blueCubes;
    private Integer greenCubes;

    private static final Integer MAX_RED_CUBES = 12;
    private static final Integer MAX_BLUE_CUBES = 14;
    private static final Integer MAX_GREEN_CUBES = 13;

    public Game(Integer id) {
        this.id = id;
        this.redCubes = 0;
        this.blueCubes = 0;
        this.greenCubes = 0;
    }

    private boolean redTooMany() {
        return redCubes > MAX_RED_CUBES;
    }

    private boolean blueTooMany() {
        return blueCubes > MAX_BLUE_CUBES;
    }

    private boolean greenTooMany() {
        return greenCubes > MAX_GREEN_CUBES;
    }

    Integer getPower() {
        return redCubes * greenCubes * blueCubes;
    }

    boolean validGame() {
        return !redTooMany() && !blueTooMany() && !greenTooMany();
    }

    static List<Game> createGameObjects(String gameList) {
        val games = new ArrayList<Game>();

        gameList.lines().forEach(line -> {
            val lineDetails = line.split(":");
            val gameId = lineDetails[0].split(" ");
            val gameRounds = lineDetails[1].split(";");

            val gameObj = new Game(parseInt(gameId[1]));
            games.add(gameObj);

            for (val round : gameRounds) {
                val roundInfo = round.split(",");

                for (val cube : roundInfo) {
                    val cubeInfo = cube.trim().split(" ");
                    val g = new Cube(Colour.fromString(cubeInfo[1]), parseInt(cubeInfo[0]));

                    switch (g.getColour()) {
                        case RED:
                            val redCubes = gameObj.getRedCubes();
                            if (g.getCount() > redCubes || redCubes == 0) {
                                gameObj.setRedCubes(parseInt(cubeInfo[0]));
                            }
                            continue;
                        case BLUE:
                            val blueCubes = gameObj.getBlueCubes();
                            if (g.getCount() > blueCubes || blueCubes == 0) {
                                gameObj.setBlueCubes(parseInt(cubeInfo[0]));
                            }
                            continue;
                        case GREEN:
                            val greenCubes = gameObj.getGreenCubes();
                            if (g.getCount() > greenCubes || greenCubes == 0)
                                gameObj.setGreenCubes(parseInt(cubeInfo[0]));
                            }
                    }
                }

            System.out.println("Game " + gameObj.getId() + ":");
            System.out.println("Original line: '" + line + "'");
            System.out.println("Red cubes: " + gameObj.getRedCubes() + (gameObj.redTooMany() ? " (too many)" : ""));
            System.out.println("Blue cubes: " + gameObj.getBlueCubes() + (gameObj.blueTooMany() ? " (too many)" : ""));
            System.out.println("Green cubes: " + gameObj.getGreenCubes() + (gameObj.greenTooMany() ? " (too many)" : ""));
            System.out.println("Total power: " + gameObj.getPower());
            System.out.println("Game " + gameObj.getId() + " is " + (gameObj.validGame() ? "valid" : "invalid") + "\n");
        });

        return games;
    }
}
