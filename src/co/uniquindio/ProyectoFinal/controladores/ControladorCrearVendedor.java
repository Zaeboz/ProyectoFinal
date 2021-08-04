package co.uniquindio.ProyectoFinal.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorCrearVendedor implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField cedulaTextFild;

    @FXML
    private Button crearVendedorButton;

    @FXML
    private Button cancelarButton;

    ControladorPrincipal controladorPrincipal = new ControladorPrincipal();


    @FXML
    void crear(ActionEvent event) {
        Vendedor vendedorNuevo = new Vendedor();
        vendedorNuevo.setCedula(cedulaTextFild.getText());
        vendedorNuevo.setNombreVendedor(nombreTextField.getText());
        controladorPrincipal.crearVendedor(vendedorNuevo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void conectarControlador(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    @FXML
    public void cancelar(ActionEvent actionEvent) {
        controladorPrincipal.cerrarStage();
    }
}
