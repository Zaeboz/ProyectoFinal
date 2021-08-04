package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.modelo.Categoria;
import co.uniquindio.ProyectoFinal.modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class ControladorCrearProducto {

    @FXML
    private TableView<Categoria> categoriasTableView;

    @FXML
    private TableColumn<Categoria, String> nombreCategoriaColumn;

    @FXML
    private Button crearButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField nombreProductoTextFild;

    ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();
    ControladorProductosVendedor controladorProductosVendedor;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void crear(ActionEvent event) {

    }

    public void conectarControlador(ControladorProductosVendedor controladorProductosVendedor) {
        this.controladorProductosVendedor = controladorProductosVendedor;
    }

    public Categoria getTablaCategoriaSeleccionado() {
        if (categoriasTableView != null) {
            List<Categoria> tabla = categoriasTableView.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Categoria competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private final ListChangeListener<Categoria> selectorTablaProducto = new ListChangeListener<Categoria>() {
        public void onChanged(ListChangeListener.Change<? extends Categoria> c) {
        }
    };

    public void inicializar(ListaSimple<Categoria> categorias){

    }
}
