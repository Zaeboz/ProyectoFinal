package co.uniquindio.ProyectoFinal.appliacation;

import co.uniquindio.ProyectoFinal.controladores.ControladorPrincipal;
import co.uniquindio.ProyectoFinal.modelo.Proyecto;
import co.uniquindio.ProyectoFinal.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

    private static Principal instance;
    private static Stage primaryStage;
    public static Proyecto proyecto = Persistencia.cargarRecursoProyectoXML();

    public static void main(String[] args) {
        launch(args);
    }

    public static Principal getInstance() {

        if (instance == null) {
            instance = new Principal();
        }
        return instance;

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Principal.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/ProyectoFinal/vistas/VistaPrincipal.fxml"));
        Parent root = loader.load();
        loader.getController();
        Scene scene = new Scene(root);
        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Proyecto getProyecto() {
        return proyecto;
    }
}
