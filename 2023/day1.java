import lombok.val;

import lombok.val;
import java.util.Map;

public class AOCDayOne2 {
    private static final String input = """
                                        two1nine
                                        eightwothree
                                        abcone2threexyz
                                        xtwone3four
                                        4nineeightseven2
                                        zoneight234
                                        7pqrstsixteen
                                        """;

    private static final Map<String, String> conversion = Map.of(
            "one", "o1e",
            "two", "t2o",
            "three", "t3e",
            "four", "f4r",
            "five", "f5e",
            "six", "s6x",
            "seven", "s7n",
            "eight", "e8t",
            "nine", "n9e"
    );

    public static void main(String[] args) {
        val inputLines = input.split("\n");
        var total = 0;

        for (val line : inputLines) {
            total += getNumberList(convert(line));
        }

        System.out.println("Your answer is: " + total);
    }

    private static String convert(String input) {
        for (val entry : conversion.entrySet()) {
            input = input.replaceAll(entry.getKey(), entry.getValue());
        }

        return input;
    }

    private static Integer getNumberList(String input) {
        val numbers = input.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::toString)
                .toList();

        val firstNumber = numbers.get(0);
        val lastNumber = numbers.get(numbers.size() - 1);

        return Integer.parseInt(firstNumber + lastNumber);
    }
}
