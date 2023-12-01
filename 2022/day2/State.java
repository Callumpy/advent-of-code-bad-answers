import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum State {
    LOSE(0),
    DRAW(3),
    WIN(6);

    private final int points;

    public static State converter(char letter) {
        return switch (letter) {
            case 'X' -> LOSE;
            case 'Y' -> DRAW;
            case 'Z' -> WIN;
            default -> throw new IllegalArgumentException("Invalid letter was provided in the elves plan");
        };
    }
}
