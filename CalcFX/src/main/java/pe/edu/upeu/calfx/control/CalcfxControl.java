package pe.edu.upeu.calfx.control;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.calfx.modelo.CalcTO;
import pe.edu.upeu.calfx.servicio.CalcServicioI;


@Controller
public class CalcfxControl {

    @Autowired
    CalcServicioI servicioI;

    @FXML
    TableView<CalcTO> tableView;
    private ObservableList<CalcTO> datos;

    @FXML
    TableColumn<CalcTO, String> num1x;
    @FXML
    TableColumn<CalcTO, String> num2x;
    @FXML
    TableColumn<CalcTO, String> operx;
    @FXML
    TableColumn<CalcTO, String> resultx;


    @FXML
    private TextField txtResultado;

    public void escribirNumero(String numero) {
        txtResultado.appendText(numero);
    }

    private void agregarOperador(String operador) {
        if(!txtResultado.getText().isEmpty() && txtResultado.getText().length()>=4){
            char op = txtResultado.getText().charAt(txtResultado.getText().length()-2);
            if(op!='+' && op!='-' && op!='/'&& op!='*'){
                txtResultado.appendText(" " + operador + " ");
            }
        }else{
            txtResultado.appendText(" " + operador + " ");
        }
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
            CalcTO to = new CalcTO();
            to.setNum1(String.valueOf(num1));
            to.setNum2(String.valueOf(num2));
            to.setOperador(operador.charAt(0));
            to.setResultado(String.valueOf(resultado));
            servicioI.save(to);

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
