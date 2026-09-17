module ni.edu.uam.eventosnavegacionypasodatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ni.edu.uam.eventosnavegacionypasodatos to javafx.fxml;
    exports ni.edu.uam.eventosnavegacionypasodatos;
}