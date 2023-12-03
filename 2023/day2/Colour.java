import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Colour {
    RED(12),
    BLUE(14),
    GREEN(13);

    private final int maxCubes;

    public static Colour fromString(String colour) {
        return switch (colour.toLowerCase()) {
            case "red" -> RED;
            case "blue" -> BLUE;
            case "green" -> GREEN;
            default -> throw new IllegalArgumentException("Invalid colour");
        };
    }
}