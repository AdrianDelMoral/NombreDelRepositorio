package calculadoraadrian;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Adrian Del Moral
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField labPantalla;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clic_Siete(ActionEvent event) {
        numPantalla("7");
    }

    @FXML
    private void clic_Ocho(ActionEvent event) {
        numPantalla("8");
    }

    @FXML
    private void clic_Nueve(ActionEvent event) {
        numPantalla("9");
    }

    @FXML
    private void clic_Cuatro(ActionEvent event) {
        numPantalla("4");
    }

    @FXML
    private void clic_Cinco(ActionEvent event) {
        numPantalla("5");
    }

    @FXML
    private void clic_Uno(ActionEvent event) {
        numPantalla("1");
    }

    @FXML
    private void clic_Dos(ActionEvent event) {
        numPantalla("2");
    }

    @FXML
    private void clic_Seis(ActionEvent event) {
        numPantalla("6");
    }

    @FXML
    private void clic_Tres(ActionEvent event) {
        numPantalla("3");
    }

    @FXML
    private void clic_Cero(ActionEvent event) {
        numPantalla("0");
    }

    @FXML
    private void clic_Punto(ActionEvent event) {
        /*  Mira si es la primera vez que se le da al numero y si es el primer 
            elemento que se selecciona*/
        if (!Punto && !nums) {
            /*  Al ser el primero que se selecciona se le añade el 0 a la izquierda 
                y el punto */
            labPantalla.setText("0.");
            nums = true;
        } // Si ya hay numero añade el punto después de los numeros introducidos
        else if (!Punto) {
            String valActual = labPantalla.getText();
            labPantalla.setText(valActual + ".");
        }
        Punto = true;
    }

    private boolean nums = false;
    private boolean Punto = false;
    private int numOperandos = 0;
    private double Operando1, Operando2;
    private char Operador = ' ';

    
    
// Sirve para mostrar los numeros en la pantalla
    private void numPantalla(String numero) {
        if (!nums && numero.equals("0")) {
            return;
        }

        if (!nums) {
            labPantalla.setText("");
            Punto = false;
        }

        String valActual = labPantalla.getText();
        labPantalla.setText(valActual + numero);
        nums = true;
    }

    @FXML
    private void clic_Valor(ActionEvent event) {
        if (nums)
            labPantalla.setText("-" + labPantalla.getText());        
    }

    @FXML
    private void clic_Borrar(ActionEvent event) {
        nums = false;
        Punto = false;
        numOperandos = 0;
        Operando1 = 0;
        Operando2 = 0;
        Operador = ' ';
        labPantalla.setText("0");
    }

    @FXML
    private void clic_Porcentaje(ActionEvent event) {
        if (numOperandos == 0) 
        {
            labPantalla.setText("0");
            return;
        }
        double valor = Double.parseDouble(labPantalla.getText());
        double porcentaje = (Operando1 * valor) / 100;
        labPantalla.setText(String.valueOf(porcentaje));
        nums = true;
        Punto = true;
    }

    @FXML
    private void clic_Dividir(ActionEvent event) {
        evalOperador("/");
    }

    @FXML
    private void clic_Multiplicar(ActionEvent event) {
        evalOperador("*");
    }

    @FXML
    private void clic_Restar(ActionEvent event) {
        evalOperador("-");
    }

    @FXML
    private void clic_Sumar(ActionEvent event) {
        evalOperador("+");
    }

    @FXML
    private void clic_Igual(ActionEvent event) {
        evalOperador("=");
    }

    // Se encarga de evaluar el operador y se encarga de realizar operaciones
    private void evalOperador(String Operador) {
        if (nums)
            numOperandos++;
        
        if (numOperandos == 1) 
            Operando1 = Double.parseDouble(labPantalla.getText());

        if (numOperandos == 2) 
        {
            Operando2 = Double.parseDouble(labPantalla.getText());
            switch (this.Operador) 
            {
                case '+':
                    Operando1 = Operando1 + Operando2;
                    break;
                case '-':
                    Operando1 = Operando1 - Operando2;
                    break;
                case '/':
                    Operando1 = Operando1 / Operando2;
                    break;
                case '*':
                    Operando1 = Operando1 * Operando2;
                    break;
                case '=':
                    Operando1 = Operando2;
                    break;
                }
                labPantalla.setText(String.valueOf(Operando1));
                numOperandos = 1;
                Punto = false;
            }
            nums = false;
            this.Operador = Operador.charAt(0);
    }
}

