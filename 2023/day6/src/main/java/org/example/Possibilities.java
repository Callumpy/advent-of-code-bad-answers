package org.example;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Possibilities {
    private Boolean winner;

    public static List<Possibilities> createPossibilites(long maxRaceTime, long distanceToBeat) {
        val possibilities = new ArrayList<Possibilities>();

        for (var buttonHoldSeconds = 0; buttonHoldSeconds < maxRaceTime; buttonHoldSeconds++) {
            val remainingSeconds = maxRaceTime - buttonHoldSeconds;
            val distanceTravelled = buttonHoldSeconds * remainingSeconds;
            val winner = distanceTravelled > distanceToBeat;

            possibilities.add(new Possibilities(winner));
        }

        return possibilities;
    }
}