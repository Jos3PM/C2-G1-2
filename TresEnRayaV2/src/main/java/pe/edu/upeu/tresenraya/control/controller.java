package pe.edu.upeu.tresenraya.control;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    private int playerTurn = 0;
    private GameDatabase gameDb;
    private Game currentGame;
    private ObservableList<Game> gamesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setDisable(true);
        });

        gameDb = GameDatabase.getInstance();

        colPartida.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJugador1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        colJugador2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        colGanador.setCellValueFactory(new PropertyValueFactory<>("winner"));
        colPuntuacion.setCellValueFactory(new PropertyValueFactory<>("score"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("state"));

        loadGamesTable();

        btnAnular.setDisable(true);

        txtPuntajeJugador1.setText("0");
        txtPuntajeJugador2.setText("0");

        updateTurnDisplay();
    }

    private void loadGamesTable() {
        gamesList = FXCollections.observableArrayList(gameDb.getAllGames());
        tableScores.setItems(gamesList);
    }

    private void updateTurnDisplay() {
        String player1 = txtPlayer1.getText().isEmpty() ? "Jugador 1" : txtPlayer1.getText();
        String player2 = txtPlayer2.getText().isEmpty() ? "Jugador 2" : txtPlayer2.getText();

        if (playerTurn == 0) {
            txtTurno.setText("Turno: " + player1 + " (X)");
        } else {
            txtTurno.setText("Turno: " + player2 + " (O)");
        }
    }

    @FXML
    void onIniciarClick() {
        buttons.forEach(button -> {
            button.setDisable(false);
            button.setText("");
            button.setStyle("-fx-background-color: #f0f0f0;");
        });

        btnIniciar.setDisable(true);
        btnAnular.setDisable(false);

        playerTurn = 0;
        updateTurnDisplay();

        txtPuntajeJugador1.setText("0");
        txtPuntajeJugador2.setText("0");

        String player1 = txtPlayer1.getText().isEmpty() ? "Jugador 1" : txtPlayer1.getText();
        String player2 = txtPlayer2.getText().isEmpty() ? "Jugador 2" : txtPlayer2.getText();
        currentGame = new Game(player1, player2);
        gameDb.addGame(currentGame);

        loadGamesTable();
        tableScores.scrollTo(tableScores.getItems().size() - 1);
    }

    @FXML
    void onAnularClick() {
        buttons.forEach(button -> {
            button.setDisable(true);
        });

        btnIniciar.setDisable(false);
        btnAnular.setDisable(true);

        gameDb.updateCurrentGame("Anulado", 0, "Anulado");

        loadGamesTable();
        tableScores.scrollTo(tableScores.getItems().size() - 1);
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);

            FadeTransition ft = new FadeTransition(Duration.millis(300), button);
            ft.setFromValue(0.7);
            ft.setToValue(1.0);
            ft.play();

            checkIfGameIsOver();
        });
    }

    private void setPlayerSymbol(Button button) {
        if (playerTurn == 0) {
            button.setText("X");
            button.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold; -fx-font-size: 24;");
            playerTurn = 1;
        } else {
            button.setText("O");
            button.setStyle("-fx-text-fill: #0000ff; -fx-font-weight: bold; -fx-font-size: 24;");
            playerTurn = 0;
        }
        updateTurnDisplay();
    }

    private void checkIfGameIsOver() {
        boolean gameOver = false;
        String winner = "";
        int score = 0;

        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            // X
            if (line.equals("XXX")) {
                gameOver = true;
                winner = txtPlayer1.getText() + " Ganador";
                score = 1;
                highlightWinningLine(a);
                break;
            }
            // O
            else if (line.equals("OOO")) {
                gameOver = true;
                winner = txtPlayer2.getText() + " Ganador";
                score = 1;
                highlightWinningLine(a);
                break;
            }
        }

        boolean allButtonsDisabled = true;
        for (Button button : buttons) {
            if (!button.isDisabled() && button.getText().isEmpty()) {
                allButtonsDisabled = false;
                break;
            }
        }

        if (allButtonsDisabled && !gameOver) {
            gameOver = true;
            winner = "Empate";
            score = 0;
        }

        // Update game state if game is over
        if (gameOver) {
            // Update scores
            if (winner.contains(txtPlayer1.getText())) {
                txtPuntajeJugador1.setText("1");
                txtPuntajeJugador2.setText("0");
            } else if (winner.contains(txtPlayer2.getText())) {
                txtPuntajeJugador1.setText("0");
                txtPuntajeJugador2.setText("1");
            } else {
                txtPuntajeJugador1.setText("0");
                txtPuntajeJugador2.setText("0");
            }

            // Disable remaining buttons
            buttons.forEach(button -> {
                if (button.getText().isEmpty()) {
                    button.setDisable(true);
                }
            });

            gameDb.updateCurrentGame(winner, score, "Terminado");

            loadGamesTable();
            tableScores.scrollTo(tableScores.getItems().size() - 1);

            btnIniciar.setDisable(false);
        }
    }

    private void highlightWinningLine(int line) {
        Color winColor = Color.rgb(50, 205, 50, 0.3);
        String highlightStyle = String.format("-fx-background-color: #%02X%02X%02X%02X;",
                (int)(winColor.getRed() * 255),
                (int)(winColor.getGreen() * 255),
                (int)(winColor.getBlue() * 255),
                (int)(winColor.getOpacity() * 255));

        switch (line) {
            case 0 -> {
                button1.setStyle(button1.getStyle() + highlightStyle);
                button2.setStyle(button2.getStyle() + highlightStyle);
                button3.setStyle(button3.getStyle() + highlightStyle);
            }
            case 1 -> {
                button4.setStyle(button4.getStyle() + highlightStyle);
                button5.setStyle(button5.getStyle() + highlightStyle);
                button6.setStyle(button6.getStyle() + highlightStyle);
            }
            case 2 -> {
                button7.setStyle(button7.getStyle() + highlightStyle);
                button8.setStyle(button8.getStyle() + highlightStyle);
                button9.setStyle(button9.getStyle() + highlightStyle);
            }
            case 3 -> {
                button1.setStyle(button1.getStyle() + highlightStyle);
                button5.setStyle(button5.getStyle() + highlightStyle);
                button9.setStyle(button9.getStyle() + highlightStyle);
            }
            case 4 -> {
                button3.setStyle(button3.getStyle() + highlightStyle);
                button5.setStyle(button5.getStyle() + highlightStyle);
                button7.setStyle(button7.getStyle() + highlightStyle);
            }
            case 5 -> {
                button1.setStyle(button1.getStyle() + highlightStyle);
                button4.setStyle(button4.getStyle() + highlightStyle);
                button7.setStyle(button7.getStyle() + highlightStyle);
            }
            case 6 -> {
                button2.setStyle(button2.getStyle() + highlightStyle);
                button5.setStyle(button5.getStyle() + highlightStyle);
                button8.setStyle(button8.getStyle() + highlightStyle);
            }
            case 7 -> {
                button3.setStyle(button3.getStyle() + highlightStyle);
                button6.setStyle(button6.getStyle() + highlightStyle);
                button9.setStyle(button9.getStyle() + highlightStyle);
            }
        }
    }

    @FXML
    void restartGame() {
        buttons.forEach(button -> {
            button.setDisable(true);
            button.setText("");
            button.setStyle("-fx-background-color: #f0f0f0;");
        });

        playerTurn = 0;
        updateTurnDisplay();

        btnIniciar.setDisable(false);
        btnAnular.setDisable(true);
    }
}