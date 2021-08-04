package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ControladorProductosVendedor {

    @FXML
    private MenuItem crearProductoMenuItem;

    @FXML
    private MenuItem eliminarProductoMenuItem;

    @FXML
    private MenuItem editarProductoMenuItem;

    @FXML
    private TableView<Producto> productosTableView;

    @FXML
    private TableView<String> categoria;

    @FXML
    private Button crearCategoriaButton;

    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    ControladorVendedor controladorVendedor;
    private Stage stage;

    @FXML
    void lanzarVistaCrearProducto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaCrearProducto.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearProducto aux = (ControladorCrearProducto) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML
    void lanzarVistaEditarProducto(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void lanzarVistaCrearCategoria(ActionEvent event) {

    }

    public Producto getTablaProductoSeleccionado() {
        if (productosTableView != null) {
            List<Producto> tabla = productosTableView.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Producto competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private final ListChangeListener<Producto> selectorTablaProducto = new ListChangeListener<Producto>() {

        public void onChanged(ListChangeListener.Change<? extends Producto> c) {
        }
    };

    public void conectarControlador(ControladorVendedor controladorVendedor) {
        this.controladorVendedor = controladorVendedor;
    }
}
