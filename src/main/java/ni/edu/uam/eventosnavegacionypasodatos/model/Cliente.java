package ni.edu.uam.eventosnavegacionypasodatos.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Cliente {

    public static final ObservableList<Cliente> LISTA_CLIENTES = FXCollections.observableArrayList();

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

    private String tipoCliente;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String tipoSolicitud;
    private boolean mantenimiento;
    private boolean instalacion;
    private String fotoPath;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String apellido, String tipoCliente, String ciudad,
                   LocalDate fechaNacimiento, String tipoSolicitud,
                   boolean mantenimiento, boolean instalacion, String fotoPath) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCliente = tipoCliente;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSolicitud = tipoSolicitud;
        this.mantenimiento = mantenimiento;
        this.instalacion = instalacion;
        this.fotoPath = fotoPath;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public boolean isInstalacion() {
        return instalacion;
    }

    public void setInstalacion(boolean instalacion) {
        this.instalacion = instalacion;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }
}
