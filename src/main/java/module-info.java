module ni.edu.uam.eventosnavegacionypasodatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ni.edu.uam.eventosnavegacionypasodatos to javafx.fxml;
    opens ni.edu.uam.eventosnavegacionypasodatos.application to javafx.graphics;
    opens ni.edu.uam.eventosnavegacionypasodatos.controllers to javafx.fxml;

    exports ni.edu.uam.eventosnavegacionypasodatos;
    exports ni.edu.uam.eventosnavegacionypasodatos.application;
    exports ni.edu.uam.eventosnavegacionypasodatos.controllers;
}