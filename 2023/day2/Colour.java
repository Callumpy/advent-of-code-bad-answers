public enum Colour {
    RED, BLUE, GREEN;

    public static Colour fromString(String colour) {
        return switch (colour) {
            case "red" -> RED;
            case "blue" -> BLUE;
            case "green" -> GREEN;
            default -> throw new IllegalArgumentException("Invalid colour");
        };
    }
}
