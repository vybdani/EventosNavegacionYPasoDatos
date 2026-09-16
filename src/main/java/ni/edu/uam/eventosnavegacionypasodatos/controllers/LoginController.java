package ni.edu.uam.eventosnavegacionypasodatos.controllers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Optional;


public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSalir;

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
            }
            // 2. Validar que las credenciales sean correctas ("admin" y "admin")
            else if (usuario.equals("admin") && password.equals("admin")) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Acceso concedido", "¡Bienvenido al sistema de solicitudes!");

                System.out.println("Credenciales válidas. Abriendo la Ventana Principal...");

                // 3. Abrir la ventana principal mediante el utilitario de navegación
                // Descomenta la siguiente línea cuando tengas tu SceneManager listo
                // SceneManager.getInstance().switchScene("menu-principal.fxml");
            }
            // Manejo de credenciales incorrectas
            else {
                mostrarAlerta(Alert.AlertType.ERROR, "Credenciales incorrectas", "El usuario o la contraseña son inválidos. Intente nuevamente.");
            }

        } catch (Exception e) {
            // Protección contra errores en tiempo de ejecución (Ej. error al cargar el FXML)
            mostrarAlerta(Alert.AlertType.ERROR, "Error crítico", "Ocurrió un error al intentar iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void confirmarSalida() {
        try {
            // 4. Solicitar confirmación mediante un Alert antes de cerrar (Requerimiento)
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Salida");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro que deseas salir del sistema de solicitudes?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit(); // Cierra la aplicación de JavaFX limpiamente
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Método Auxiliar ---
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}