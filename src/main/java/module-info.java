module ni.edu.uam.eventosnavegacionypasodatos {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.eventosnavegacionypasodatos to javafx.fxml;
    exports ni.edu.uam.eventosnavegacionypasodatos;
}