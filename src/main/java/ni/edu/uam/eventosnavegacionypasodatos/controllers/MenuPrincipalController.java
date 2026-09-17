package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.Launcher;
import ni.edu.uam.eventosnavegacionypasodatos.model.Cliente;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML private Label lblTotalClientes;

    @FXML
    public void initialize() {
        actualizarConteo();
    }

    @FXML
    void handleAbrirRegistro(ActionEvent event) {
        abrirVentana("registro-cliente-view.fxml", "Registro de Cliente");
    }

    @FXML
    void handleAbrirConsulta(ActionEvent event) {
        abrirVentana("consulta-cliente-view.fxml", "Consulta de Clientes");
    }

    @FXML
    void handleActualizarConteo(ActionEvent event) {
        actualizarConteo();
    }

    @FXML
    void handleSalir(ActionEvent event) {
        Stage stage = (Stage) lblTotalClientes.getScene().getWindow();
        stage.close();
    }

    private void actualizarConteo() {
        lblTotalClientes.setText("Clientes registrados: " + Cliente.LISTA_CLIENTES.size());
    }

    private void abrirVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initOwner(lblTotalClientes.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
