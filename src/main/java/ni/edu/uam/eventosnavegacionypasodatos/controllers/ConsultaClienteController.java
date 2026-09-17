package ni.edu.uam.eventosnavegacionypasodatos.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ni.edu.uam.eventosnavegacionypasodatos.model.Cliente;

import java.time.format.DateTimeFormatter;

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

    private String formatearFecha(Cliente cliente) {
        return cliente.getFechaNacimiento() == null ? "" : cliente.getFechaNacimiento().format(FORMATO_FECHA);
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
