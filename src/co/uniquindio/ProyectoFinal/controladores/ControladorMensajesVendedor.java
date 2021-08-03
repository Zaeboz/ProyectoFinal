package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.appliacation.Principal;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.modelo.Mensaje;
import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.List;

public class ControladorMensajesVendedor {

    @FXML
    private TableView<Vendedor> listaAmigosTableView = new TableView<>();

    @FXML
    private TableColumn<Vendedor, String> amigosColumn = new TableColumn<>("Amigos");

    @FXML
    private TextArea conversacionTextArea;

    @FXML
    private Button iniciarChatButton;

    @FXML
    private Button enviarMensajeButton;

    @FXML
    private TextArea mensajeTextArea;

    ControladorVendedor controladorVendedor;
    Vendedor vendedorPrincipal;
    ObservableList<Vendedor> listaVendedores = FXCollections.observableArrayList();
    Principal principal = Principal.getInstance();

    @FXML
    void enviarMensaje(ActionEvent event) {
        Vendedor vendedorSeleccionado = getTablaVendedorSeleccionado();
        Mensaje mensaje = new Mensaje(vendedorSeleccionado, mensajeTextArea.getText());
        principal.getProyecto().enviarMensaje(vendedorPrincipal, mensaje);
    }

    @FXML
    void iniciarChat(ActionEvent event) {
        Vendedor vendedorSeleccionado = getTablaVendedorSeleccionado();
        ListaSimple<Mensaje> mensajes= principal.getProyecto().obtenerMensajes(vendedorPrincipal, vendedorSeleccionado);
        for(Mensaje mensaje : mensajes){
            String cadena = mensaje.getVendedorFinal().getNombreVendedor()+mensaje.getFechaString();
            cadena += "\n"+mensaje.getMensaje();
            conversacionTextArea.appendText(cadena);
        }
        enviarMensajeButton.setDisable(false);
        mensajeTextArea.setDisable(false);
    }


    public void conectarControlador(ControladorVendedor controladorVendedor) {
        this.controladorVendedor = controladorVendedor;
    }

    public Vendedor getTablaVendedorSeleccionado() {
        if (listaAmigosTableView != null) {
            List<Vendedor> tabla = listaAmigosTableView.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Vendedor competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public void inicializarDatos(Vendedor vendedorVista) {
        vendedorPrincipal = vendedorVista;
        amigosColumn.setCellValueFactory(new PropertyValueFactory<Vendedor, String>("nombreVendedor"));
        listaAmigosTableView.setItems(listaVendedores);
    }
}
