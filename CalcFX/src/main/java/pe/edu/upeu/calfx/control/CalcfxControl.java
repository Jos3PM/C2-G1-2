package pe.edu.upeu.calfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;


@Controller
public class CalcfxControl {

    @FXML
    private TextField txtResultado;

    public void escribirNumero(String numero) {
        txtResultado.appendText(numero);
    }

    private void agregarOperador(String operador) {
        txtResultado.appendText(" " + operador + " ");
    }

    private void calcularResultado() {
        try {
            String[] tokens = txtResultado.getText().split(" ");
            if (tokens.length < 3) {
                return;
            }
            double num1 = Double.parseDouble(tokens[0]);
            String operador = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);
            double resultado = 0;
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        txtResultado.setText("Error: Div/0");
                        return;
                    }
                    break;
            }
            txtResultado.setText(String.valueOf(resultado));
        } catch (Exception e) {
            txtResultado.setText("Error");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void controlClick(ActionEvent event) {
        Button boton = (Button) event.getSource();
        switch (boton.getId()) {
            case "btn0", "btn1", "btn2", "btn3", "btn4", "btn5", "btn6", "btn7", "btn8", "btn9": {escribirNumero(boton.getText());}break;
            case "btndiv","btnmul","btnres","btnsum":{agregarOperador(boton.getText());}break;
            case "btnborrar":{txtResultado.setText("");}break;
            case "btnigual":{ calcularResultado();}break;
            default: {} break;
        }
    }
}
