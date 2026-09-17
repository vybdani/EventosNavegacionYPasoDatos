package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.Launcher;
import ni.edu.uam.eventosnavegacionypasodatos.model.Cliente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    void handleExportarRespaldo(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar carpeta de respaldo");
        File carpeta = directoryChooser.showDialog(lblTotalClientes.getScene().getWindow());

        if (carpeta == null) {
            return;
        }

        File archivo = new File(carpeta, "clientes_respaldo.csv");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("Nombre,Apellido,TipoCliente,Ciudad,FechaNacimiento,TipoSolicitud");
            for (Cliente cliente : Cliente.LISTA_CLIENTES) {
                writer.printf("%s,%s,%s,%s,%s,%s%n",
                        cliente.getNombre(), cliente.getApellido(), cliente.getTipoCliente(),
                        cliente.getCiudad(), cliente.getFechaNacimiento(), cliente.getTipoSolicitud());
            }
            new Alert(Alert.AlertType.INFORMATION, "Respaldo guardado en:\n" + archivo.getAbsolutePath()).showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "No se pudo guardar el respaldo: " + e.getMessage()).showAndWait();
        }
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
            stage.showAndWait();
            actualizarConteo();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana: " + e.getMessage());
            alert.showAndWait();
        }
    }
}