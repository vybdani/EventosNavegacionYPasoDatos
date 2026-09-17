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

public class ConsultaClienteController {

    @FXML private TextField txtBuscar;
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colApellido;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private Button btnCerrar;

    private FilteredList<Cliente> clientesFiltrados;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));
        colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCorreo()));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));

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

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase().contains(filtro);
    }

    @FXML
    void handleCerrar(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}
