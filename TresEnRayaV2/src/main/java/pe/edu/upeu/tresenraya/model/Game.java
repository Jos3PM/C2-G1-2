package pe.edu.upeu.tresenraya.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game {
    private String id;
    private String player1;
    private String player2;
    private String winner;
    private int score;
    private String state;
    private LocalDateTime timestamp;

    public Game(String player1, String player2) {
        this.id = generateGameId();
        this.player1 = player1;
        this.player2 = player2;
        this.winner = "";
        this.score = 0;
        this.state = "Jugando";
        this.timestamp = LocalDateTime.now();
    }

    private String generateGameId() {
        return "Partida " + (int)(Math.random() * 1000);
    }

    // Getters
    public String getId() { return id; }
    public String getPlayer1() { return player1; }
    public String getPlayer2() { return player2; }
    public String getWinner() { return winner; }
    public int getScore() { return score; }
    public String getState() { return state; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Setters
    public void setWinner(String winner) { this.winner = winner; }
    public void setScore(int score) { this.score = score; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return id + " - " + player1 + " vs " + player2 +
                " - Ganador: " + winner +
                " - Puntuación: " + score +
                " - Estado: " + state;
    }
}