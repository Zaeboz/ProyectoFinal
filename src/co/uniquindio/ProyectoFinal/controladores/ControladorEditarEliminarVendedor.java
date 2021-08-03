package co.uniquindio.ProyectoFinal.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorEditarEliminarVendedor {

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField cedulaTextFild;

    @FXML
    private Button editarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button cancelarButton;

    String key;
    ControladorPrincipal controladorPrincipal = new ControladorPrincipal();

    @FXML
    void cancelar(ActionEvent event) {
        controladorPrincipal.cerrarStage();
    }

    @FXML
    void editar(ActionEvent event) {
        Vendedor vendedor = new Vendedor();

        vendedor.setNombreVendedor(nombreTextField.getText());
        vendedor.setCedula(cedulaTextFild.getText());
        controladorPrincipal.edtitarVendedor(vendedor, key);
    }

    @FXML
    void eliminar(ActionEvent event) {
        controladorPrincipal.eliminarVendedor(nombreTextField.getText());
    }

    public void conectarControlador(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    public void inicializarDatos(Vendedor vendedor){
        nombreTextField.setText(vendedor.getNombreVendedor());
        cedulaTextFild.setText(vendedor.getCedula());
        key = nombreTextField.getText();
    }
}
