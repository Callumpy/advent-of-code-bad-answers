package org.example;

import lombok.val;

public class App
{
    public static void main( String[] args ) {
        val input = Input.getInput();
        val games = Game.createGamesFromInput(input, false);
        val round2Games = Game.createGamesFromInput(input, true);

        val winningSums = games.stream()
                .map(Game::getNumberOfWinners)
                .toList();

        val round2WinningSums = round2Games.stream()
                .map(Game::getNumberOfWinners)
                .toList();

        val product = winningSums.stream()
                .reduce(1, (a, b) -> a * b);
        val round2Product = round2WinningSums.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println(product);
        System.out.println(round2Product);
    }
}
