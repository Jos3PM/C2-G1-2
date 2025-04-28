package pe.edu.upeu.tresenraya.model;

import java.util.ArrayList;
import java.util.List;

public class GameDatabase {
    private static GameDatabase instance;
    private ArrayList<Game> games;

    private GameDatabase() {
        games = new ArrayList<>();
    }

    public static GameDatabase getInstance() {
        if (instance == null) {
            instance = new GameDatabase();
        }
        return instance;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public List<Game> getAllGames() {
        return games;
    }

    public Game getCurrentGame() {
        if (games.isEmpty()) {
            return null;
        }
        return games.get(games.size() - 1);
    }

    public void updateCurrentGame(String winner, int score, String state) {
        Game currentGame = getCurrentGame();
        if (currentGame != null) {
            currentGame.setWinner(winner);
            currentGame.setScore(score);
            currentGame.setState(state);
        }
    }
}