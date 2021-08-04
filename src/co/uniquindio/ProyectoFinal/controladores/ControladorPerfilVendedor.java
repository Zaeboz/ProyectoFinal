package co.uniquindio.ProyectoFinal.controladores;

import co.uniquindio.ProyectoFinal.appliacation.Principal;
import co.uniquindio.ProyectoFinal.modelo.Producto;
import co.uniquindio.ProyectoFinal.modelo.Vendedor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorPerfilVendedor {

    @FXML
    private Button crearPublicacionButton;

    @FXML
    private Button verAmigoButton;

    @FXML
    private ScrollPane publicacionesScrollPane;

    @FXML
    private GridPane gridPanePublicaciones;

    ControladorVendedor controladorVendedor;
    Vendedor vendedor;
    int contador = 0;
    private Stage stage;

    @FXML
    void lanzarVistaCrearPublicacion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaCrearPublicacion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearPublicacion aux = (ControladorCrearPublicacion) loader.getController();
        aux.conectarControlador(this);
        aux.inicializarDatos(vendedor);
        stage.show();
    }

    @FXML
    void lanzarVistaVerAmigos(ActionEvent event) {

    }

    public void cerrarStage(){
        stage.close();
    }

    public void conectarControlador(ControladorVendedor controladorVendedor) {
        this.controladorVendedor = controladorVendedor;
    }

    public void enviarVendedor(Vendedor vendedorVista) {
        vendedor = vendedorVista;
    }

    public void crearPublicacion(String descripcion, Producto productoSeleccionado) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinWidth(586.0);
        anchorPane.setMinHeight(91.0);

        Button megustaButton = new Button();
        megustaButton.setText("Me gusta");
        AnchorPane.setBottomAnchor(megustaButton,10.0);
        AnchorPane.setLeftAnchor(megustaButton, 10.0);
        megustaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                productoSeleccionado.getListaMeGusta();
                Principal.proyecto.generarMegusta(productoSeleccionado);
            }
        });

        Button comentarButton = new Button();
        comentarButton.setText("Comentar");
        AnchorPane.setBottomAnchor(comentarButton, 10.0);
        AnchorPane.setRightAnchor(comentarButton, 10.0);
        comentarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

            }
        });

        TextArea comentario = new TextArea();
        AnchorPane.setRightAnchor(comentario,20.0);
        AnchorPane.setLeftAnchor(comentario, 20.0);
        AnchorPane.setBottomAnchor(comentario, 10.0);

        Text publicacion = new Text();
        String contenido = "Fecha de publicacion: "+productoSeleccionado.getFechaString();
        contenido += "\nNombre del producto: "+productoSeleccionado.getNombre();
        contenido += "\nDescripcion: "+descripcion;
        publicacion.setText(contenido);
        AnchorPane.setBottomAnchor(publicacion, 20.0);
        AnchorPane.setRightAnchor(publicacion, 10.0);
        AnchorPane.setLeftAnchor(publicacion, 10.0);
        AnchorPane.setTopAnchor(publicacion, 10.0);

        anchorPane.getChildren().addAll(megustaButton, comentarButton, comentario, publicacion);
        gridPanePublicaciones.add(anchorPane, 0, contador);
        contador++;
    }
}
