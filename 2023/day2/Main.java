import lombok.val;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        val games = Game.createGameObjects(Input.gameList);
        var sum = new AtomicInteger();
        AtomicInteger power = new AtomicInteger();

        games.forEach(game -> {
            if (game.validGame()) {
                sum.addAndGet(game.getId());
            }

            power.addAndGet(game.getPower());
        });

        System.out.println("The total sum of all valid game IDs: " + sum);
        System.out.println("The total power of all games: " + power);
    }
}
