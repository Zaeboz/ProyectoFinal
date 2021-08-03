package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.appliacation.Principal;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.modelo.Producto;
import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import co.uniquindio.ProyectoFinal.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ControladorCrearPublicacion {

    @FXML
    private TextArea descripcionTextArea;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> nombreColumn;

    @FXML
    private Button crearButton;

    @FXML
    private Button cancelarButton;

    ControladorPerfilVendedor controladorPerfilVendedor;
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    private Vendedor vendedor;

    @FXML
    void cancelar(ActionEvent event) {
        controladorPerfilVendedor.cerrarStage();
    }

    @FXML
    void crear(ActionEvent event) {
        String descripcion = descripcionTextArea.getText();
        Producto productoSeleccionado = getTablaVendedorSeleccionado();
        controladorPerfilVendedor.crearPublicacion(descripcion, productoSeleccionado);
    }

    public Producto getTablaVendedorSeleccionado() {
        if (tablaProductos != null) {
            List<Producto> tabla = tablaProductos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Producto competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public void conectarControlador(ControladorPerfilVendedor controladorPerfilVendedor) {
        this.controladorPerfilVendedor = controladorPerfilVendedor;
    }

    private final ListChangeListener<Producto> selectorTablaProducto = new ListChangeListener<Producto>() {

        public void onChanged(ListChangeListener.Change<? extends Producto> c) {
        }
    };

    public void inicializarDatos(Vendedor vendedor) {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        tablaProductos.setItems(listaProductos);
        final ObservableList<Producto> tablaProductoSel = tablaProductos.getSelectionModel().getSelectedItems();
        this.vendedor = vendedor;
        cargarProductos();
        tablaProductoSel.addListener(selectorTablaProducto);
    }

    private void cargarProductos() {
        listaProductos.clear();
        ListaSimple<Producto> productos = new ListaSimple<>();
        for(String key : Principal.proyecto.getGrafoVendedores().nodosKeys()) {
            Vendedor vendedor = Principal.proyecto.buscarVendedor(key);
            if(vendedor == this.vendedor){
                productos = vendedor.getArbolProductos().inorden();
            }
        }
        for(Producto producto : productos){
            listaProductos.add(producto);
        }
    }
}
