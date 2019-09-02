/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Proyectos
 */
public class SceneCURPController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEntidad;

    @FXML
    private TextField txtFechaDeNacimiento;

    @FXML
    private TextField txtGenero;

    @FXML
    private Button btnObtener;

    @FXML
    private TextField txtCURP;

    @FXML
    private Button btnBorrar;

    @FXML
    void btnBorrar_ActionPerformed(ActionEvent event) {

        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtNombre.setText("");
        txtGenero.setText("");
        txtCURP.setText("");
        txtFechaDeNacimiento.setText("");
        txtEntidad.setText("");

    }

    @FXML
    void btnObtener_ActionPerformed(ActionEvent event) {

        if (validarCampos()) {
            StringBuilder sbCURP = new StringBuilder();
            sbCURP.append(txtApellidoPaterno.getText().substring(0, 2));
            sbCURP.append(txtApellidoMaterno.getText().substring(0, 1));

            //1
            //        String nombreValidado = validarNombre(txtNombre.getText());
            //        sbCURP.append(nombreValidado.substring(0, 1));
            //2
            sbCURP.append(validarNombre(txtNombre.getText()).substring(0, 1));

            sbCURP.append(txtFechaDeNacimiento.getText().substring(2, 8));
            sbCURP.append(txtGenero.getText());
            sbCURP.append(txtEntidad.getText().substring(0, 1));

            txtCURP.setText(sbCURP.toString());
        } else {
            JOptionPane.showMessageDialog(null, "FAVOR DE LLENAR TODOS LOS CAMPOS DE TEXTO");
        }
        //TODO: 1. Quitar "José y María"
        //      2. Obtener primera letra (cualquiera) y la primera vocal (omitiendo primera letra)
        //      3. Validar formatos de fecha
        //      4. Validar que solo sea un caracter para género
        //
        //        Primera consonante interna () del primer apellido.
        //        Primera consonante interna () del segundo apellido.
        //        Primera consonante interna () del nombre.
        //        Dígito del 0-9 para fechas de nacimiento hasta el año 1999 y A-Z para fechas de nacimiento a partir del 2000.
        //        Dígito, para evitar duplicaciones.
        //        Validar casos especiales. (Se pone X)
    }

    @FXML
    void initialize() {
        assert txtApellidoPaterno != null : "fx:id=\"txtApellidoPaterno\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtApellidoMaterno != null : "fx:id=\"txtApellidoMaterno\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtEntidad != null : "fx:id=\"txtEntidad\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtFechaDeNacimiento != null : "fx:id=\"txtFechaDeNacimiento\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtGenero != null : "fx:id=\"txtGenero\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert btnObtener != null : "fx:id=\"btnObtener\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert txtCURP != null : "fx:id=\"txtCURP\" was not injected: check your FXML file 'sceneCURP.fxml'.";
        assert btnBorrar != null : "fx:id=\"btnBorrar\" was not injected: check your FXML file 'sceneCURP.fxml'.";

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO:
        // REGEX QUE CONVIERTA A MAYUSCULAS TODO LO INGRESADO
        // REGEX QUE SOLO PERMITA UN CARACTER EN GÉNERO
        // REGEX PARA QUE FORMATO DE FECHA SEA SOLO NUMERICO

        txtFechaDeNacimiento.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //EL VALOR TIENE QUE SER NUMERAL, SI NO NO SE ESCRIBE
                if (!newValue.matches("\\d*$")) {
                    txtFechaDeNacimiento.setText(oldValue);
                }
            }
        });

    }

    private String validarNombre(String nombreDeOrigen) {

        if (nombreDeOrigen.contains("MARÍA ") && nombreDeOrigen.length() > 6) {
            nombreDeOrigen.replace("MARÍA ", "");
        }

        if (nombreDeOrigen.contains("JOSÉ ") && nombreDeOrigen.length() > 5) {
            nombreDeOrigen.replace("JOSÉ ", "");
        }

        return nombreDeOrigen;
    }

    private boolean validarCampos() {
        boolean validado = true;

        if (txtApellidoPaterno.getText().isEmpty()) {
            validado = false;
        }
        if (txtApellidoMaterno.getText().isEmpty()) {
            validado = false;
        }
        if (txtNombre.getText().isEmpty()) {
            validado = false;
        }
        if (txtGenero.getText().isEmpty()) {
            validado = false;
        }

        if (txtFechaDeNacimiento.getText().isEmpty()) {
            validado = false;
        }
        if (txtEntidad.getText().isEmpty()) {
            validado = false;
        }
        return validado;
    }

}
