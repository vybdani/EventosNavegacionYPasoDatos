package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.model.Cliente;

import java.io.File;

public class RegistroClienteController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;

    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbCiudad;
    @FXML private DatePicker dpFechaNacimiento;


    @FXML private RadioButton rbSoporte;
    @FXML private RadioButton rbVentas;
    @FXML private ToggleGroup tgTipoSolicitud;
    @FXML private CheckBox chkMantenimiento;
    @FXML private CheckBox chkInstalacion;


    @FXML private ImageView imgFotografia;

    @FXML private Button btnSeleccionarFoto;
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCancelar;

    private String fotoSeleccionada;

    @FXML
    public void initialize() {

        cmbTipoCliente.setItems(FXCollections.observableArrayList("Persona Natural", "Empresa"));
        cmbCiudad.setItems(FXCollections.observableArrayList("Managua", "León", "Granada", "Masaya"));

        tgTipoSolicitud = new ToggleGroup();
        rbSoporte.setToggleGroup(tgTipoSolicitud);
        rbVentas.setToggleGroup(tgTipoSolicitud);
    }

    @FXML
    void handleSeleccionarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Fotografía del Cliente");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) btnSeleccionarFoto.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imgFotografia.setImage(image);
            fotoSeleccionada = file.getAbsolutePath();
        }
    }

    @FXML
    void handleGuardar(ActionEvent event) {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellido.getText().trim().isEmpty() ||
                cmbTipoCliente.getValue() == null ||
                cmbCiudad.getValue() == null ||
                dpFechaNacimiento.getValue() == null ||
                tgTipoSolicitud.getSelectedToggle() == null) {

            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor completa todos los campos obligatorios del formulario.");
            return;
        }

        String tipoSolicitud = tgTipoSolicitud.getSelectedToggle() == rbSoporte ? "Soporte" : "Ventas";

        Cliente cliente = new Cliente(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                dpFechaNacimiento.getValue(),
                tipoSolicitud,
                chkMantenimiento.isSelected(),
                chkInstalacion.isSelected(),
                fotoSeleccionada
        );
        Cliente.LISTA_CLIENTES.add(cliente);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente registrado correctamente.");

        limpiarFormulario();
    }

    @FXML
    void handleLimpiar(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    void handleCancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        cmbTipoCliente.setValue(null);
        cmbCiudad.setValue(null);
        dpFechaNacimiento.setValue(null);
        tgTipoSolicitud.selectToggle(null);
        chkMantenimiento.setSelected(false);
        chkInstalacion.setSelected(false);
        imgFotografia.setImage(null);
        fotoSeleccionada = null;
    }
    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
