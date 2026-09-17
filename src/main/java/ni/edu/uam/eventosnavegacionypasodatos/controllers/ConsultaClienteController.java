package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.model.Cliente;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsultaClienteController {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML private TextField txtBuscar;
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colNombreCompleto;
    @FXML private TableColumn<Cliente, String> colTipoCliente;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML private TableColumn<Cliente, String> colTipoSolicitud;
    @FXML private Button btnCerrar;

    private FilteredList<Cliente> clientesFiltrados;

    @FXML
    public void initialize() {
        colNombreCompleto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreCompleto()));
        colTipoCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoCliente()));
        colCiudad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCiudad()));
        colFechaNacimiento.setCellValueFactory(data -> new SimpleStringProperty(formatearFecha(data.getValue())));
        colTipoSolicitud.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipoSolicitud()));

        clientesFiltrados = new FilteredList<>(Cliente.LISTA_CLIENTES, cliente -> true);
        tblClientes.setItems(clientesFiltrados);

        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            String filtro = newValue == null ? "" : newValue.trim().toLowerCase();
            clientesFiltrados.setPredicate(cliente ->
                    filtro.isEmpty()
                            || contiene(cliente.getNombre(), filtro)
                            || contiene(cliente.getApellido(), filtro)
            );
        });
    }

    @FXML
    void handleClickTabla(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                mostrarDetalle(seleccionado);
            }
        }
    }

    private void mostrarDetalle(Cliente cliente) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Detalle del Cliente");
        dialog.setHeaderText(cliente.getNombreCompleto());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(15));

        grid.add(new Label("Tipo de cliente:"), 0, 0);
        grid.add(new Label(valorOGuion(cliente.getTipoCliente())), 1, 0);

        grid.add(new Label("Ciudad:"), 0, 1);
        grid.add(new Label(valorOGuion(cliente.getCiudad())), 1, 1);

        grid.add(new Label("Fecha de nacimiento:"), 0, 2);
        grid.add(new Label(formatearFecha(cliente)), 1, 2);

        grid.add(new Label("Tipo de solicitud:"), 0, 3);
        grid.add(new Label(valorOGuion(cliente.getTipoSolicitud())), 1, 3);

        grid.add(new Label("Servicios de interés:"), 0, 4);
        grid.add(new Label(serviciosTexto(cliente)), 1, 4);

        if (cliente.getFotoPath() != null) {
            ImageView imageView = new ImageView(new Image(new File(cliente.getFotoPath()).toURI().toString()));
            imageView.setFitWidth(120.0);
            imageView.setFitHeight(120.0);
            imageView.setPreserveRatio(true);
            grid.add(imageView, 0, 5, 2, 1);
        }

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    private String serviciosTexto(Cliente cliente) {
        List<String> servicios = new ArrayList<>();
        if (cliente.isMantenimiento()) servicios.add("Mantenimiento");
        if (cliente.isInstalacion()) servicios.add("Instalación");
        return servicios.isEmpty() ? "Ninguno" : String.join(", ", servicios);
    }

    private String valorOGuion(String valor) {
        return valor == null ? "-" : valor;
    }

    private String formatearFecha(Cliente cliente) {
        return cliente.getFechaNacimiento() == null ? "-" : cliente.getFechaNacimiento().format(FORMATO_FECHA);
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase().contains(filtro);
    }

    @FXML
    void handleCerrar(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}
