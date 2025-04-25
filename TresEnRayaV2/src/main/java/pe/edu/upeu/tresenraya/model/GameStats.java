package pe.edu.upeu.tresenraya.model;

public class GameStats {
    private int totalGames;
    private int player1Wins;
    private int player2Wins;
    private int draws;

    public GameStats() {
        this.totalGames = 0;
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.draws = 0;
    }

    public void updateStats(Game game) {
        totalGames++;
        if (game.getWinner().contains(game.getPlayer1())) {
            player1Wins++;
        } else if (game.getWinner().contains(game.getPlayer2())) {
            player2Wins++;
        } else if (game.getWinner().equals("Empate")) {
            draws++;
        }
    }

    public int getTotalGames() { return totalGames; }
    public int getPlayer1Wins() { return player1Wins; }
    public int getPlayer2Wins() { return player2Wins; }
    public int getDraws() { return draws; }
}