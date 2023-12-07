package org.example;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Game {
    private List<Possibilities> possibilities;

    public int getNumberOfWinners() {
        return (int) possibilities.stream()
                .filter(Possibilities::getWinner)
                .count();
    }

    public static List<Game> createGamesFromInput(String input, Boolean round2) {
        val games = new ArrayList<Game>();

        val timePattern = Pattern.compile("Time:\\s+((\\d+)\\s+)+");
        val distancePattern = Pattern.compile("Distance:\\s+((\\d+)\\s+)+");

        val timeMatcher = timePattern.matcher(input);
        val distanceMatcher = distancePattern.matcher(input);

        if (timeMatcher.find() && distanceMatcher.find()) {
            val timeValues = timeMatcher.group().trim();
            val distanceValues = distanceMatcher.group().trim();

            var timeArray = Arrays.stream(timeValues.split("\\s+")).filter(s -> !s.contains("Time:")).toArray(String[]::new);
            var distanceArray = Arrays.stream(distanceValues.split("\\s+")).filter(s -> !s.contains("Distance:")).toArray(String[]::new);

            if (round2) {
                for (var i = 1; i < timeArray.length; i++) {
                    timeArray[0] += timeArray[i];
                    distanceArray[0] += distanceArray[i];
                }

                timeArray = new String[] { timeArray[0] };
                distanceArray = new String[] { distanceArray[0] };
            }

            for (var i = 0; i < timeArray.length; i++) {
                val time = Long.parseLong(timeArray[i]);
                val distance = Long.parseLong(distanceArray[i]);
                val possibilities = Possibilities.createPossibilites(time, distance);
                games.add(new Game(possibilities));
            }
        }

        return games;
    }
}
