package ni.edu.uam.eventosnavegacionypasodatos.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolicitudesApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ni.edu.uam.eventosnavegacionypasodatos.Launcher.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }
}