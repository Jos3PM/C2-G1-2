package pe.edu.upeu.tresenraya.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import pe.edu.upeu.tresenraya.model.Game;
import pe.edu.upeu.tresenraya.model.GameDatabase;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class controller implements Initializable {

    @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    @FXML private Button btnIniciar, btnAnular;
    @FXML private TextField txtPlayer1, txtPlayer2;
    @FXML private Text txtTurno, txtPuntajeJugador1, txtPuntajeJugador2;
    @FXML private TableView<Game> tableScores;
    @FXML private TableColumn<Game, String> colPartida, colJugador1, colJugador2, colGanador, colEstado;
    @FXML private TableColumn<Game, Integer> colPuntuacion;
    @FXML private GridPane gameBoard;

    private ArrayList<Button> buttons;
    private String currentPlayer = "X";
    private GameDatabase gameDb;
    private Game currentGame;
    private int player1Score = 0;
    private int player2Score = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            button.setOnAction(e -> handleButtonClick(button));
            button.setDisable(true);
        });

        gameDb = GameDatabase.getInstance();
        setupTableColumns();
        loadGamesTable();
        btnAnular.setDisable(true);
        updateScores();
    }

    private void setupTableColumns() {
        colPartida.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJugador1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        colJugador2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        colGanador.setCellValueFactory(new PropertyValueFactory<>("winner"));
        colPuntuacion.setCellValueFactory(new PropertyValueFactory<>("score"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

    private void loadGamesTable() {
        ObservableList<Game> gamesList = FXCollections.observableArrayList(gameDb.getAllGames());
        tableScores.setItems(gamesList);
    }

    @FXML
    void onIniciarClick() {
        if (txtPlayer1.getText().trim().isEmpty() || txtPlayer2.getText().trim().isEmpty()) {
            showAlert("Error", "Por favor ingrese los nombres de ambos jugadores");
            return;
        }

        resetBoard();
        currentPlayer = "X";
        btnIniciar.setDisable(true);
        btnAnular.setDisable(false);
        updateTurnText();

        currentGame = new Game(txtPlayer1.getText(), txtPlayer2.getText());
        gameDb.addGame(currentGame);
        loadGamesTable();
    }

    @FXML
    void onAnularClick() {
        if (currentGame != null) {
            gameDb.updateCurrentGame("Anulado", 0, "Anulado");
            loadGamesTable();
        }
        resetBoard();
        btnIniciar.setDisable(false);
        btnAnular.setDisable(true);
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);

            if (checkWinner()) {
                handleWin();
            } else if (isBoardFull()) {
                handleDraw();
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                updateTurnText();
            }
        }
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons.get(index++).getText();
            }
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
                    checkLine(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }

        // Check diagonals
        return checkLine(board[0][0], board[1][1], board[2][2]) ||
                checkLine(board[0][2], board[1][1], board[2][0]);
    }

    private boolean checkLine(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && a.equals(c);
    }

    private boolean isBoardFull() {
        return buttons.stream().noneMatch(button -> button.getText().isEmpty());
    }

    private void handleWin() {
        String winner = currentPlayer.equals("X") ? txtPlayer1.getText() : txtPlayer2.getText();
        if (currentPlayer.equals("X")) {
            player1Score++;
        } else {
            player2Score++;
        }
        updateScores();
        gameDb.updateCurrentGame(winner + " Ganador", 1, "Terminado");
        endGame(winner + " ha ganado!");
    }

    private void handleDraw() {
        gameDb.updateCurrentGame("Empate", 0, "Terminado");
        endGame("¡Empate!");
    }

    private void endGame(String message) {
        buttons.forEach(button -> button.setDisable(true));
        btnIniciar.setDisable(false);
        btnAnular.setDisable(true);
        loadGamesTable();
        showAlert("Fin del Juego", message);
    }

    private void resetBoard() {
        buttons.forEach(button -> {
            button.setText("");
            button.setDisable(false);
        });
        updateTurnText();
    }

    private void updateTurnText() {
        String currentPlayerName = currentPlayer.equals("X") ?
                txtPlayer1.getText() : txtPlayer2.getText();
        txtTurno.setText("Turno: " + currentPlayerName + " (" + currentPlayer + ")");
    }

    private void updateScores() {
        txtPuntajeJugador1.setText(String.valueOf(player1Score));
        txtPuntajeJugador2.setText(String.valueOf(player2Score));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}