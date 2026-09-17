package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.Launcher;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Button btnSalir;

    @FXML
    public void handleLogin(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    public void handleSalir(ActionEvent event) {
        confirmarSalida();
    }

    @FXML
    public void handleKeyEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            iniciarSesion();
            event.consume();
        } else if (event.getCode() == KeyCode.ESCAPE) {
            confirmarSalida();
        }
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        try {
            if (usuario.isEmpty() || password.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "El usuario y la contraseña no pueden estar vacíos. Por favor, complete ambos campos.");
            } else if (usuario.equals("admin") && password.equals("admin")) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Acceso concedido", "¡Bienvenido al sistema de solicitudes!");
                abrirVentanaPrincipal();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Credenciales incorrectas", "El usuario o la contraseña son inválidos. Intente nuevamente.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error crítico", "Ocurrió un error al intentar iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void abrirVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("menu-principal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            if (stage != null) {
                stage.setScene(new Scene(root));
                stage.setTitle("Menú Principal");
            }
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana principal: " + e.getMessage());
        }
    }

    private void confirmarSalida() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Salida");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro que deseas salir del sistema de solicitudes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}