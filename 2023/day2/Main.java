import lombok.val;

public class Main {
    public static void main(String[] args) {
        val games = Game.createGameObjects(Input.gameList);

        val sumOfValidGameIds = games.stream()
                .filter(Game::validGame)
                .mapToInt(Game::getId)
                .sum();

        val totalPower = games.stream()
                .mapToInt(Game::getPower)
                .sum();

        System.out.println("The total sum of all valid game IDs: " + sumOfValidGameIds);
        System.out.println("The total power of all games: " + totalPower);
    }
}