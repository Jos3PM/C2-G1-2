package pe.edu.upeu.tresenraya.util;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GameUtils {
    public static String getWinningLine(Button... buttons) {
        if (buttons.length != 9) return "";

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().isEmpty() &&
                    buttons[i].getText().equals(buttons[i+1].getText()) &&
                    buttons[i].getText().equals(buttons[i+2].getText())) {
                return buttons[i].getText();
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() &&
                    buttons[i].getText().equals(buttons[i+3].getText()) &&
                    buttons[i].getText().equals(buttons[i+6].getText())) {
                return buttons[i].getText();
            }
        }

        // Check diagonals
        if (!buttons[0].getText().isEmpty() &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText())) {
            return buttons[0].getText();
        }

        if (!buttons[2].getText().isEmpty() &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText())) {
            return buttons[2].getText();
        }

        return "";
    }

    public static void highlightButtons(Color color, Button... buttons) {
        String style = String.format("-fx-background-color: #%02X%02X%02X%02X;",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255),
                (int)(color.getOpacity() * 255));

        for (Button button : buttons) {
            button.setStyle(button.getStyle() + style);
        }
    }

    public static boolean isBoardFull(Button... buttons) {
        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}