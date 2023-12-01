import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Hand {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int points;

    public static Hand converter(char letter) {
        return switch (letter) {
            case 'A', 'X' -> ROCK;
            case 'B', 'Y' -> PAPER;
            case 'C', 'Z' -> SCISSORS;
            default -> throw new IllegalArgumentException("Invalid letter was provided in the elves plan");
        };
    }
    public boolean beats(Hand other) {
        return switch (this) {
            case ROCK -> other == SCISSORS;
            case PAPER -> other == ROCK;
            case SCISSORS -> other == PAPER;
        };
    }
}
