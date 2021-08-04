package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.appliacation.Principal;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;
import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.*;

public class ControladorPrincipal implements Initializable, Serializable {
    private static final long serialVersionUID = 1L;

    @FXML
    private GridPane vendeoresGridPane;

    @FXML
    private Text vendedoresText;

    @FXML
    private Button buscarVendedorButton;

    @FXML
    private TextField nombreBuscarTextArea;

    Principal principal = Principal.getInstance();
    private Stage stage;
    private int contadorVendedores = 0;
    private final static int MAX_VENDEDORES = 10;
    private boolean eliminado = false;

    @FXML
    void buscarVendedor(ActionEvent event) throws IOException {

        String nombre = nombreBuscarTextArea.getText();
        Vendedor vendedorAux = principal.getProyecto().buscarVendedor(nombre);
        if(vendedorAux !=null ){
            lanzarVistaEditarEliminarVendedor(vendedorAux);
        }else{
            if(contadorVendedores < MAX_VENDEDORES){
                lanzarVistaCrearVendedor();
            } else {
                showPopUpMessage(WARNING, "Alerta", "La cantidad maxima de vendedores es 10", "");
            }

        }
    }

    private void lanzarVistaEditarEliminarVendedor(Vendedor vendedor) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaEditarEliminarVendedor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorEditarEliminarVendedor aux = (ControladorEditarEliminarVendedor) loader.getController();
        aux.conectarControlador(this);
        aux.inicializarDatos(vendedor);
        stage.show();

    }

    private void lanzarVistaCrearVendedor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaCrearVendedor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearVendedor aux = (ControladorCrearVendedor) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML
    public void cerrarPrograma(ActionEvent actionEvent) {
        principal.getPrimaryStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatosGridPane();
    }

    private void cargarDatosGridPane() {
        for(String key : Principal.proyecto.getGrafoVendedores().nodosKeys()){
            Vendedor vendedor = Principal.proyecto.buscarVendedor(key);
            crearBoton(vendedor);
        }
    }

    public void cerrarStage(){
        stage.close();
    }


    public void crearVendedor(Vendedor vendedorNuevo) {
        stage.close();
        try {
            Vendedor vendedor = principal.getProyecto().crearVendedores(vendedorNuevo);
            if(vendedor==null) crearBoton(vendedorNuevo);
        } catch (NombreRepetidoException e) {
            System.out.println(e.getMessage());
        }

    }

    private void crearBoton(Vendedor vendedorNuevo){

        Button vendedorButton = new Button();
        vendedorButton.setText(vendedorNuevo.getNombreVendedor());
        vendedorButton.setMinWidth(100);
        vendedorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaVendedor.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String nombre = vendedorButton.getText();
                Vendedor vendedor = principal.getProyecto().buscarVendedor(nombre);
                Scene scene = new Scene(root);
                stage = new Stage();
                stage.setTitle(nombre);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                ControladorVendedor aux = (ControladorVendedor) loader.getController();
                aux.conectarControlador(ControladorPrincipal.this);
                aux.inicializarDatos(vendedor);
                stage.show();
            }
        });

        if(eliminado){
            contadorVendedores++;
            vendeoresGridPane.add(vendedorButton, 0, contadorVendedores);
            contadorVendedores++;
        }else{
            vendeoresGridPane.add(vendedorButton, 0, contadorVendedores);
            contadorVendedores++;
        }
    }

    public void showPopUpMessage(Alert.AlertType type, String title, String header, String message) {
        Alert popUp = new Alert(type);
        popUp.setTitle(title);
        popUp.setHeaderText(header);
        popUp.setContentText(message);
        popUp.initStyle(StageStyle.DECORATED);
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setWidth(300);
        popUp.setHeight(200);
        popUp.setResizable(false);
        popUp.show();
    }

    public void edtitarVendedor(Vendedor vendedorNuevo, String key) {
        stage.close();
        int i = 0;
        for(Object objeto: vendeoresGridPane.getChildren()){
            Button button = (Button) objeto;
            String nombreButton = button.getText();
            if(nombreButton.equals(key)){
                button.setText(vendedorNuevo.getNombreVendedor());
                vendeoresGridPane.setRowIndex(button, i);
            }
            i++;
        }
        principal.getProyecto().editarVendedor(key, vendedorNuevo);
    }

    public void eliminarVendedor(String nombreVendedor) {
        stage.close();
        Object encontrado = null;
        ObservableList observableList = vendeoresGridPane.getChildren();

        for(Object objeto: observableList){
            Button button = (Button) objeto;
            String nombreButton = button.getText();
            if(nombreButton.equals(nombreVendedor)) {
                encontrado = objeto;
            }
        }

        if(encontrado!=null){
            principal.getProyecto().eliminarVendedor(nombreVendedor);
            observableList.remove(encontrado);
            eliminado = true;
            contadorVendedores--;
        }
    }
}
