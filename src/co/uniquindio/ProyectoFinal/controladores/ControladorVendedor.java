package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorVendedor {

    ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
    Vendedor vendedorVista;

    @FXML
    private Button perfilButton;

    @FXML
    private Button productosButton;

    @FXML
    private Button mensajesButton;

    @FXML
    private BorderPane borderPaneVendedor;

    @FXML
    private Button cerrarButton;

    @FXML
    void cerrarPerfil(ActionEvent event) {
        controladorPrincipal.cerrarStage();
    }

    @FXML
    void lanzarVistaMensajes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaMensajesVendedor.fxml"));
        Parent root = loader.load();
        AnchorPane view = (AnchorPane) root;
        ControladorMensajesVendedor aux = (ControladorMensajesVendedor) loader.getController();
        aux.conectarControlador(this);
        aux.inicializarDatos(vendedorVista);
        borderPaneVendedor.setCenter(view);
    }

    @FXML
    void lanzarVistaPerfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaPerfilVendedor.fxml"));
        Parent root = loader.load();
        AnchorPane view = (AnchorPane) root;
        ControladorPerfilVendedor aux = (ControladorPerfilVendedor) loader.getController();
        aux.conectarControlador(this);
        aux.enviarVendedor(vendedorVista);
        borderPaneVendedor.setCenter(view);
    }

    @FXML
    void lanzarVistaProductos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaProductosVendedor.fxml"));
        Parent root = loader.load();
        AnchorPane view = (AnchorPane) root;
        ControladorProductosVendedor aux = (ControladorProductosVendedor) loader.getController();
        aux.conectarControlador(this);
        borderPaneVendedor.setCenter(view);
    }


    public void conectarControlador(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    public void inicializarDatos(Vendedor vendedor){
        vendedorVista = vendedor;
    }
}
